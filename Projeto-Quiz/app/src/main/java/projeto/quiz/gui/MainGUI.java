package projeto.quiz.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import projeto.quiz.service.PerguntaManager;
import projeto.quiz.Refatorado.Exception.ListaVaziaException;
import projeto.quiz.repository.PerguntaRepository;

public class MainGUI extends JFrame {

    private PerguntaManager perguntaManager = new PerguntaManager(PerguntaRepository.getInstance());

    public MainGUI() {
        setTitle("QUIZ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(0x4682B4));

        setSize(350, 250);
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
                try {
                    perguntaManager.editarPergunta();
                } catch (ListaVaziaException e1) {
                    e1.printStackTrace();
                }
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
                dispose();
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
