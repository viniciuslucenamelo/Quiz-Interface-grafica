package projeto.quiz.gui;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import projeto.quiz.service.PerguntaManager;
import projeto.quiz.repository.PerguntaRepository;

public class MainGUI extends Application {

    private PerguntaManager perguntaManager = new PerguntaManager(PerguntaRepository.getInstance());

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Menu");

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);

        Button adicionarButton = new Button("Adicionar Pergunta");
        adicionarButton.setOnAction(e -> perguntaManager.adicionarPergunta());

        Button removerButton = new Button("Remover Pergunta");
        removerButton.setOnAction(e -> perguntaManager.removerPergunta());

        Button editarButton = new Button("Editar Pergunta");
        editarButton.setOnAction(e -> perguntaManager.editarPergunta());

        Button listarButton = new Button("Listar Perguntas");
        listarButton.setOnAction(e -> perguntaManager.listarPerguntas());

        Button jogarButton = new Button("Jogar");
        // Adicione a lógica do jogo para o botão Jogar aqui

        Button sairButton = new Button("Sair");
        sairButton.setOnAction(e -> primaryStage.close());

        vbox.getChildren().addAll(adicionarButton, removerButton, editarButton, listarButton, jogarButton, sairButton);

        Scene scene = new Scene(vbox, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
