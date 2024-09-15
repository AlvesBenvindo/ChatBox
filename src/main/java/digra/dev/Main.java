package digra.dev;

import digra.dev.bind_IA.servico_IA.Service_Gemini;

public class Main {

    private static Service_Gemini serviceGemini;

    public static void main(String[] args) {

        new ChatBox(serviceGemini);
    }
}