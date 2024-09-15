package digra.dev.bind_IA;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ConversorJSON {

    private static final Gson gson = new Gson();

    public static String makeJSON(String texto){

        JsonObject data = new JsonObject();
        JsonArray contentsArray = new JsonArray();
        JsonObject contentObject = new JsonObject();
        JsonArray partsArray = new JsonArray();
        JsonObject partObject = new JsonObject();
        partObject.addProperty("text", texto);
        partsArray.add(partObject);
        contentObject.add("parts", partsArray);
        contentsArray.add(contentObject);
        data.add("contents", contentsArray);

        return createJSON(data);
    }

    private static String createJSON(JsonObject data){
        return gson.toJson(data);
    }

    public static String unMakeJSON(String strJSON){
        return convertToObject(strJSON);
    }

    private static String convertToObject (String resposta) {
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
