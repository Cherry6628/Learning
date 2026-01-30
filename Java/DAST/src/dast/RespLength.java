package dast;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.CookieManager;
import java.util.regex.*;

public class RespLength {

    static HttpClient client = HttpClient.newBuilder()
            .cookieHandler(new CookieManager())
            .build();
    static HttpRequest req=null;
    static HttpResponse<String> res = null;
    

    public static void main(String[] args) {

        req = HttpRequest.newBuilder()
                .uri(URI.create("http://127.0.0.1:9090/login.php"))
                .GET()
                
                .build();


        String csrf = "";

        try {
            res = client.send(req, HttpResponse.BodyHandlers.ofString());

            Pattern p = Pattern.compile("(?<=value=')[0-9A-Za-z]+");
            Matcher m = p.matcher(res.body());
            if (m.find()) csrf = m.group();
            System.out.println("Token = " + csrf);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        String form = "username=admin&password=password&Login=Login&user_token=" + csrf;

        req= HttpRequest.newBuilder()
                .uri(URI.create("http://127.0.0.1:9090/login.php"))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(form))
                .build();
        
        String phpsessid="";
        try {
            res = client.send(req, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status = " + res.statusCode());
            System.out.println("Body = \n" + res.body());
            System.out.println(res.headers().firstValue("set-cookie").orElse(""));
            String raw = res.headers().firstValue("set-cookie").orElse("");
            phpsessid = raw.split(";")[0];

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        HttpRequest getIndex = HttpRequest.newBuilder()
                .uri(URI.create("http://127.0.0.1:9090/index.php"))
                .header("Cookie", phpsessid+";security=low")
                .GET()
                .build();
        try {
        	res= client.send(getIndex, HttpResponse.BodyHandlers.ofString());
        	System.out.println("Status : "+res.statusCode());
        	System.out.println("Body: "+res.body());
        }catch(Exception e){}
    }
}
