package projeto.quiz.Menu;

import projeto.quiz.Refatorado.Exception.ListaVaziaException;
import projeto.quiz.commands.CommandExecutor;
import projeto.quiz.commands.JogarCommand;
import projeto.quiz.domain.Pergunta;
import projeto.quiz.repository.PerguntaRepository;
import projeto.quiz.service.PerguntaManager;
import projeto.quiz.service.PerguntaService;

import java.util.List;
import java.util.Scanner;

public class Menu {

    public static void main(String[] args) throws ListaVaziaException {
        PerguntaRepository perguntaRepository = PerguntaRepository.getInstance();
        PerguntaManager perguntaManager = new PerguntaManager(perguntaRepository);

        Scanner scanner = new Scanner(System.in);
        CommandExecutor commandExecutor = new CommandExecutor();

        while (true) {
            System.out.println();
            System.out.println("---------------- MENU ----------------");
            System.out.println();
            System.out.println("1- Adicionar Pergunta");
            System.out.println("2- Remover Pergunta");
            System.out.println("3- Editar Pergunta");
            System.out.println("4- Listar Perguntas");
            System.out.println("5- Jogar");
            System.out.println("6- Sair");
            System.out.println();

            try {
                int escolha = scanner.nextInt();
                scanner.nextLine();

                switch (escolha) {
                    case 1:
                        perguntaManager.adicionarPergunta();
                        break;
                    case 2:
                        perguntaManager.removerPergunta();
                        break;
                    case 3:
                        perguntaManager.editarPergunta();
                        break;
                    case 4:
                        perguntaManager.listarPerguntas();
                        break;
                    case 5:
                        JogarCommand jogarCommand = new JogarCommand();
                        commandExecutor.executeCommand(jogarCommand);
                        break;
                    case 6:
                        System.out.println("Encerrando o programa.");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Opção inválida. Tente novamente.");
                scanner.nextLine();
            }
        }
    }
}
