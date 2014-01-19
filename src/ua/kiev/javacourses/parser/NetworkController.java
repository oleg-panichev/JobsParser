package ua.kiev.javacourses.parser;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created with IntelliJ IDEA.
 * User: Oleg
 * Date: 22.12.13
 * Time: 11:58
 * To change this template use File | Settings | File Templates.
 */
public class NetworkController {
    public static String getDocument(URL url) throws IOException {
        String inputLine ="";
        StringBuilder result = new StringBuilder();
        URLConnection uc=url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
        while((inputLine=in.readLine())!=null) {
            result.append(inputLine);
        }
        in.close();
        return result.toString();
    }

    public static void storeVacancies() {

    }
}
