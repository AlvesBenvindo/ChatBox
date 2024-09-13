package digra.dev;

import digra.dev.bind_IA.servico_IA.Service_Gemini;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatBox extends JFrame {
    private JTextArea dialogo;
    private JTextField questao;
    private JButton btnEnviar;
    private JButton btnApagar;
    private JButton btnLimpar;

    public ChatBox(Service_Gemini serviceGemini) {
        super("Chat Box - Desafio");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLayout(new BorderLayout());

        // Área de texto para as mensagens
        dialogo = new JTextArea();
        dialogo.setEditable(false);
        btnLimpar = new JButton("Limpar Diálogo");
        JScrollPane scrollPane = new JScrollPane(dialogo);
        add(btnLimpar, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);

        // Area lateral para o campo de texto e os botões
        JPanel sideArea = new JPanel();
        questao = new JTextField(30);
        btnEnviar = new JButton("Enviar");
        btnApagar = new JButton("Apagar");
        sideArea.add(questao);
        sideArea.add(btnEnviar);
        sideArea.add(btnApagar);
        add(sideArea, BorderLayout.AFTER_LINE_ENDS);

        // pegando o clique para enviar
        btnEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mensagem = questao.getText();
                String respostaIA = "null-null";
                respostaIA = Service_Gemini.enviarQuestao(mensagem);
                dialogo.append("Você: " + mensagem + "\n");
                dialogo.append("IA: " + respostaIA + "\n");
                questao.setText("");
            }
        });

        // pegando o clique para apagar o texto que seria enviado
        btnLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogo.setText("");
            }
        });

        // pegando o evento de clique para eliminar as respostas da IA
        btnApagar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                questao.setText("");
            }
        });

        setVisible(true);
    }

}