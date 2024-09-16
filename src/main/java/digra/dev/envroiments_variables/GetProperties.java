package digra.dev.envroiments_variables;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class GetProperties {

    private static String filePath = "C:\\Users\\SCURINATTI\\ChatBox\\src\\main\\resources\\app.properties";
    private static Properties properties = new Properties();

    public static String getValue (String property){
        try{
            properties.load(new FileInputStream(filePath));
            String value = properties.getProperty(property);
            System.out.println("valor de propriedade achado: " + value);
            return value;
        }catch(IOException ioExc){
            return null;
        }catch(Exception exc){
            return null;
        }
    }
}

