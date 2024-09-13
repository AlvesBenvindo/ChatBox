package digra.dev.bind_IA.servico_IA;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Service_Gemini {
    private static final String apiKey = "AIzaSyC1Ak8D0vr-6g2aBknxtEqkvss6ntv9fCs";

    public static String enviarQuestao(String questao) {

        try {
            final String serviceIAURL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=" + URLEncoder.encode(apiKey, "UTF-8");
            URL url = new URL(serviceIAURL);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("POST");
            conexao.setRequestProperty("Content-Type", "application/json");

            JsonObject data = new JsonObject();
            JsonArray contentsArray = new JsonArray();
            JsonObject contentObject = new JsonObject();
            JsonArray partsArray = new JsonArray();
            JsonObject partObject = new JsonObject();
            partObject.addProperty("text", questao);
            partsArray.add(partObject);
            contentObject.add("parts", partsArray);
            contentsArray.add(contentObject);
            data.add("contents", contentsArray);


            Gson gson = new Gson();
            String body = gson.toJson(data);

            conexao.setDoOutput(true);
            DataOutputStream writer = new DataOutputStream(conexao.getOutputStream());
            System.out.println(body);
            writer.writeBytes(body);
            writer.flush();
            writer.close();

            int responseCode = conexao.getResponseCode();
            System.out.println("POST Response Code :: " + responseCode);


            if (responseCode != HttpURLConnection.HTTP_OK)
                return "HTTP codigo de erro : " + responseCode;

            BufferedReader reader = new BufferedReader(new InputStreamReader((conexao.getInputStream())));

            String inputLine;
            StringBuffer resposta = new StringBuffer();

            while ((inputLine = reader.readLine()) != null) {
                resposta.append(inputLine);
            }
            reader.close();

            String strResposta =  pegaTexto(resposta.toString()).split(":")[1];

            return strResposta;
        } catch (Exception e) {
            return "NO OK!!!\nERRO: " + e;
        }
    }

    public static String pegaTexto (String resposta) {

        String subString = "";
        for (int i = 0; i < resposta.length(); i++) {
            if(resposta.charAt(i)=='{'){
                for (int j = i; j < resposta.length(); j++) {
                    if(resposta.charAt(j)=='[') {
                        for (int k = j; k < resposta.length(); k++) {
                            if (resposta.charAt(k) == '{'){
                                for (int l = k; l < resposta.length(); l++) {
                                    if (resposta.charAt(l) == '{'){
                                        for (int m = k; m < resposta.length(); m++) {
                                            if (resposta.charAt(m) == '['){
                                                for (int n = m; n < resposta.length(); n++) {
                                                    if (resposta.charAt(n) == '{'){
                                                        for (int p = 0, o = n; o < resposta.length(); o++, p++) {
                                                            if (resposta.charAt(o) == '}') return subString;
                                                            subString += resposta.charAt(o);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return "";
    }
}
