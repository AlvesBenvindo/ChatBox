package digra.dev.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ConectorREST {

    public final int OK = HttpURLConnection.HTTP_OK;
    private URL url;
    private String httpMethod;
    private String contentType;
    private int statusCode;
    private HttpURLConnection connection;

    public String getUrl() {
        return url.toString();
    }

    public void setUrl(String url) {
        try{
            this.url = new URL(url);
        }catch (MalformedURLException mExc) {
            System.out.println("URL mal estruturada!!!");
        }catch (Exception exc) {
            System.out.println("Ocorreu algum erro!!!");
        }finally {
            System.out.println("Tente reescrever o URL!!!");
        }
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public HttpURLConnection getConnection() {
        return connection;
    }

    public void setConnection(HttpURLConnection connection) {
        this.connection = connection;
    }

    public ConectorREST() {
        this.contentType = "application/json";
        this.httpMethod = "GET";
    }

    public ConectorREST(String url) {
        this.contentType = "application/json";
        try{
            this.url = new URL(url);
            System.out.println("URL :: " + this.url);
        }catch (MalformedURLException mExc) {
            System.out.println("URL mal estruturada!!!");
        }catch (Exception exc) {
            System.out.println("Ocorreu algum erro!!!");
        }
    }

    public void create() throws MalformedURLException {
        if(this.url==null) {
            throw new MalformedURLException("URL mal formada");
        }
        try{
            this.connection = (HttpURLConnection) this.url.openConnection();
            this.connection.setRequestMethod(this.httpMethod);
            this.connection.setRequestProperty("Content-Type", this.contentType);
            System.out.println("http-method :: " + this.httpMethod);
            System.out.println("Content-Type :: " + this.contentType);
        }catch (IOException ioExc){
            System.out.println("Erro na Conexão");
            System.out.println("Algum erro no método http ou no content-type");
        }catch (Exception exc){
            System.out.println("Algum erro na Conexão");
            System.out.println("Algum erro no método http ou no content-type");
        }
    }
    
    public void sendRequest(String body) {
        try{
            this.connection.setDoOutput(true);
            DataOutputStream writer = new DataOutputStream(this.connection.getOutputStream());
            writer.writeBytes(body);
            writer.flush();
            writer.close();
            this.statusCode = this.connection.getResponseCode();
            System.out.println("StatusCode :: " + this.statusCode);
        }catch (IOException ioExc){
            System.out.println("Erro no envio dos dados");
        }catch (Exception exc){
            System.out.println("Algum erro nos envio de dados ou nos próprios dados dados");
        }
    }

    public String getResponse(){
        try{

            BufferedReader reader = new BufferedReader(new InputStreamReader((this.connection.getInputStream())));

            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }
            reader.close();

            return response.toString();
        }catch (IOException ioExc){
            return "Erro na resposta";
        }catch (Exception Exc){
            return "Ocorreu algum erro na resposta";
        }
    }

}
