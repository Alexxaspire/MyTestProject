package GetHttpData;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Alex on 16.05.2017.
 */
public class GetHttpData {

    public void ConnectResult() {

        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost request = new HttpPost("http://IP_ADRESS");
            StringEntity entity = new StringEntity("{\"login\" : \"LOGIN)INFO\", \"password\" : \"PASS_INFO\", \"ip\" : \"127.0.0.1\"}");

            request.setEntity(entity);

            HttpResponse response = client.execute(request);
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String result = "";
            while ((result = reader.readLine()) != null) {
                System.out.println(result);
            }

        } catch (IOException ex) {
        }

    }

    public static void main(String[] args) {
        GetHttpData get = new GetHttpData();

        get.ConnectResult();
    }
}
