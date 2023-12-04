package projeto.quiz.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame {

    private PerguntaManager perguntaManager = new PerguntaManager(PerguntaRepository.getInstance());

    public MainGUI() {
        setTitle("Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 250);
        setLayout(new FlowLayout());
        setResizable(false);

        JButton adicionarButton = new JButton("Adicionar Pergunta");
        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                perguntaManager.adicionarPergunta();
            }
        });

        JButton removerButton = new JButton("Remover Pergunta");
        removerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                perguntaManager.removerPergunta();
            }
        });

        JButton editarButton = new JButton("Editar Pergunta");
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                perguntaManager.editarPergunta();
            }
        });

        JButton listarButton = new JButton("Listar Perguntas");
        listarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                perguntaManager.listarPerguntas();
            }
        });

        JButton jogarButton = new JButton("Jogar");
        // Adicione a lógica do jogo para o botão Jogar aqui

        JButton sairButton = new JButton("Sair");
        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a janela ao clicar em "Sair"
            }
        });

        add(adicionarButton);
        add(removerButton);
        add(editarButton);
        add(listarButton);
        add(jogarButton);
        add(sairButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainGUI().setVisible(true);
            }
        });
    }
}
