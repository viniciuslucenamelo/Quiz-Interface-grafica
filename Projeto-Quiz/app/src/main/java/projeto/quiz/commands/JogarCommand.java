package projeto.quiz.commands;

import projeto.quiz.Refatorado.Exception.ListaVaziaException;
import projeto.quiz.Refatorado.Exception.RespostaNaoEncontradaException;
import projeto.quiz.domain.Alternativa;
import projeto.quiz.domain.Pergunta;
import projeto.quiz.domain.RandomNum;
import projeto.quiz.repository.InMemoryDataService;
import projeto.quiz.repository.PerguntaRepository;
import projeto.quiz.service.PerguntaService;

import java.util.List;
import java.util.Scanner;

public class JogarCommand implements Commands {
    @Override
    public void execute() {
        try {
            jogar();
        } catch (ListaVaziaException e) {
            System.out.println(e.getMessage());
        }
    }

    private void jogar() throws ListaVaziaException {
        InMemoryDataService dataService = new InMemoryDataService(); // Adicione essa linha
        PerguntaRepository perguntaRepository = new PerguntaRepository(dataService); 
        PerguntaService perguntaService = new PerguntaService(perguntaRepository);
        
        List<Pergunta> perguntas = perguntaService.getPerguntas();

        if (perguntas.isEmpty()) {
            throw new ListaVaziaException("Não há perguntas disponíveis para jogar.");
        }

        System.out.println("Iniciando o jogo!");
        System.out.println();

        int pontuacao = 0;

        while (!perguntas.isEmpty()) {
            Pergunta pergunta = RandomNum.selecionarPerguntaAleatoria(perguntas);
            exibirPergunta(pergunta);

            Scanner scanner = new Scanner(System.in);
            System.out.print("Sua resposta: ");
            String respostaUsuario = scanner.nextLine();

            if (pergunta.verificarResposta(respostaUsuario)) {
                System.out.println("Resposta correta! Pontuação +1");
                pontuacao++;
            } try {
                    System.out.println("Resposta incorreta. A resposta correta era: " + pergunta.obterRespostaCorreta());
                    } catch (RespostaNaoEncontradaException ex) {
                    System.out.println("Erro ao obter a resposta correta: " + ex.getMessage());
                    }


            System.out.println();
        }

        System.out.println("Fim do jogo! Pontuação final: " + pontuacao);
    }

    private void exibirPergunta(Pergunta pergunta) {
        System.out.println("Pergunta: " + pergunta.getTitulo());
        System.out.println("Área do Conhecimento: " + pergunta.getAreaDoConhecimento());
        System.out.println("Escolha a opção correta:");

        List<Alternativa> alternativas = pergunta.getAlternativas();
        for (Alternativa alternativa : alternativas) {
            System.out.println(alternativa.getOpcao() + ". " + alternativa.getAfirmativa());
        }
    }
}
