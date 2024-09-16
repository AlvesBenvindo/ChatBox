package digra.dev.bind_IA.servico_IA;

import digra.dev.bind_IA.ConversorJSON;
import digra.dev.http.ConectorREST;
import digra.dev.envroiments_variables.GetProperties;

public class Service_Gemini {
    private static final String apiKey = GetProperties.getValue("ia.gemini.api.key");
    private static final String apiURL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=";

    public static String sendText(String texto) {

        try {
            ConectorREST conexao = new ConectorREST(apiURL + apiKey);
            conexao.setHttpMethod("POST");
            conexao.create();
            String body = ConversorJSON.makeJSON(texto);
            conexao.sendRequest(body);

            if (conexao.getStatusCode() != conexao.OK)
                return "MENSAGEM NÃO PROCESSADA COM SUCESSO!!!";

            String response = conexao.getResponse();
            return ConversorJSON.unMakeJSON(response).split(":")[1];
        } catch (Exception e) {
            return "HTTP-ERROR : problemas na conexão com a internet.";
        }
    }
}
