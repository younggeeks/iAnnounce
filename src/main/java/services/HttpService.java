package services;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Gaffi on 10/19/15.
 */
public class HttpService {

    private final String baseUrl="http://localhost:8888/kiuMessagesApi/public/api/";

    public HttpService() {
    }

    public String serviceGet(String url){

        String response=null;

        try {
            HttpClient httpClient=new DefaultHttpClient();
            HttpGet httpGet=new HttpGet(baseUrl+url);
            httpGet.setHeader(new BasicHeader("Prama","no-cache"));
            httpGet.setHeader(new BasicHeader("Cache-Control","no-cache"));
            HttpResponse httpResponse=httpClient.execute(httpGet);
            HttpEntity entity=httpResponse.getEntity();
            response= EntityUtils.toString(entity);



        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;

    }
}
