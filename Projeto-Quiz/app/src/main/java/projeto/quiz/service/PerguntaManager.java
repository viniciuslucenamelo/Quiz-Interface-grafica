package projeto.quiz.service;
package projeto.quiz.Refatorado.Exception.ListaVaziaException;

import java.util.List;
import java.util.Scanner;

import projeto.quiz.domain.Pergunta;
import projeto.quiz.domain.Alternativa;
import projeto.quiz.repository.PerguntaRepository;

public class PerguntaManager {
    private final PerguntaRepository repository;

    public PerguntaManager(PerguntaRepository repository) {
        this.repository = repository;
    }

    public void adicionarPergunta() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Insira o título da pergunta: ");
        String titulo = scanner.nextLine();

        System.out.println("Área do conhecimento da pergunta: ");
        String areaDoConhecimento = scanner.nextLine();

        Pergunta pergunta = new Pergunta(titulo, areaDoConhecimento);

        // Adiciona a pergunta ao repositório
        repository.add(pergunta);

        System.out.println("Pergunta adicionada com sucesso!");
    }

    public void listarPerguntas() {
        List<Pergunta> perguntas = repository.getAll();

        if (perguntas.isEmpty()) {
            System.out.println("Não há perguntas disponíveis.");
        } else {
            System.out.println("Lista de Perguntas:");
            for (int i = 0; i < perguntas.size(); i++) {
                Pergunta pergunta = perguntas.get(i);
                System.out.println(i + ". Título: " + pergunta.getTitulo());
                System.out.println("   Área do Conhecimento: " + pergunta.getAreaDoConhecimento());
                // Pode adicionar lógica para listar alternativas aqui, se necessário
            }
        }
    }

    public void removerPergunta() {
        listarPerguntas();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o número da pergunta que deseja remover: ");
        int escolha = scanner.nextInt();

        List<Pergunta> perguntas = repository.getAll();

        if (escolha >= 0 && escolha < perguntas.size()) {
            Pergunta perguntaRemovida = perguntas.get(escolha);
            repository.remove(perguntaRemovida);
            System.out.println("Pergunta removida com sucesso!");
        } else {
            System.out.println("Escolha inválida. A pergunta não foi removida.");
        }
    }

    private void editarPergunta() throws ListaVaziaException {

    PerguntaService perguntaService = new PerguntaService(PerguntaRepository.getInstance());
    List<Pergunta> perguntas = perguntaService.getPerguntas();

    if (perguntas.isEmpty()) {
        throw new ListaVaziaException("Não há perguntas para editar.");
    }

    Scanner sc = new Scanner(System.in);

    System.out.println(); // ajudar formatação

    // Listar os títulos das perguntas disponíveis
    for (int i = 0; i < perguntas.size(); i++) {
        System.out.println(i + ". Título: " + perguntas.get(i).getTitulo());
    }

    System.out.println(); // ajudar formatação
    System.out.print("Digite o número da pergunta que deseja editar: ");
    int escolhaEdit = sc.nextInt();
    sc.nextLine();

    // Verificar se a escolha do usuário está dentro dos limites
    if (escolhaEdit >= 0 && escolhaEdit < perguntas.size()) {
        System.out.println(); // ajudar formatação
        System.out.print("Digite o novo título (ou Enter para manter o atual): ");
        String novoTitulo = sc.nextLine();

        if (!novoTitulo.trim().isEmpty()) {
            perguntas.get(escolhaEdit).setTitulo(novoTitulo);
        }

        System.out.println(); // ajudar formatação
        System.out.print("Digite a nova área do conhecimento (ou Enter para manter a atual): ");
        String novaAreaDoConhecimento = sc.nextLine();

        if (!novaAreaDoConhecimento.trim().isEmpty()) {
            perguntas.get(escolhaEdit).setAreaDoConhecimento(novaAreaDoConhecimento);
        }

        // Editar as alternativas
        List<Alternativa> alternativas = perguntas.get(escolhaEdit).getAlternativas();

        for (int i = 0; i < alternativas.size(); i++) {
            System.out.println(); // ajudar formatação
            Alternativa alternativa = alternativas.get(i);
            System.out.println("Editar Alternativa " + (i + 1));

            // Editar a opção
            System.out.print("Nova opção (ou Enter para manter a atual): ");
            String novaOpcao = sc.nextLine();
            if (!novaOpcao.trim().isEmpty()) {
                alternativa.setOpcao(novaOpcao);
            }

            // Editar a afirmativa
            System.out.print("Nova afirmativa (ou Enter para manter a atual): ");
            String novaAfirmativa = sc.nextLine();
            if (!novaAfirmativa.trim().isEmpty()) {
                alternativa.setAfirmativa(novaAfirmativa);
            }

            // Editar se é a opção correta
            System.out.print("É a opção correta? (true/false) (ou Enter para manter a atual): ");
            String respostaCorreta = sc.nextLine();
            if (!respostaCorreta.trim().isEmpty()) {
                boolean novaOpcaoCorreta = Boolean.parseBoolean(respostaCorreta);
                alternativa.setOpcaoCorreta(novaOpcaoCorreta);
            }
        }

        System.out.println("Pergunta editada com sucesso.");
    } else {
        System.out.println("Escolha inválida. A pergunta não foi editada.");
    }
}

