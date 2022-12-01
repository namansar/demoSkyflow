package org.example;

import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.gson.Gson;
//import com.google.api.client.json.JsonParser;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.Map;
import java.util.Scanner;

public class HttpResponseHandler{
	
	 public HttpResponseHandler() {}
    
    
    public JsonArray SendPost (String s) throws Exception {
    	String token1 = "eyJraWQiOiJtdkduRWFCLUxWazBRblFydzhqbzJvNmhYaXpMTktMWXpULXFvejVOSnhrIiwiYWxnIjoiUlMyNTYifQ.eyJ2ZXIiOjEsImp0aSI6IkFULjRaVzdPd2pjN0JUdkR0dFU5TlZoQk1RVjczdHFGX1ZRYlN1dUtkaE5XZDgiLCJpc3MiOiJodHRwczovL2F1dGguc2t5Zmxvdy5kZXYvb2F1dGgyL2RlZmF1bHQiLCJhdWQiOiJhcGk6Ly9kZWZhdWx0IiwiaWF0IjoxNjY5OTExMzE2LCJleHAiOjE2Njk5MTQ5MTYsImNpZCI6IjBvYTUxYmYza0JqOWh1TUxhNHg2IiwidWlkIjoiMDB1NzYzYm4wN0NHWHdSYVI0eDciLCJzY3AiOlsiZW1haWwiLCJwcm9maWxlIiwib3BlbmlkIl0sImF1dGhfdGltZSI6MTY2OTkxMTMxMSwic3ViIjoia2lzaGFuLnNpbGF3YXRAc2t5Zmxvdy5jb20ifQ.eUi9wjf0zjbp3F2f5PsS7CuedYMm1bJKYox5U1tTZ48DZmdgLH-BT1b830CqZCy-ULt_KcUys4IQsUz4geRwvXbVXdnZMy3Ed-o8HDeRe-mX_95Rm9cHCXqoUo4vIK2tZUJluHVX_o1hmupvdvW-Y1iPEss9gn3ujBLRkY35VhKFxD9D76MCgslaKbbrThXzqRboKXlsdNKPH3OBZvnvRFQ6yP5LNzpfEywlRkwR1BebZd5qm2FLjpI9oZHTxWNI0_lHo5-Kr093-B31Dq6IXnKmj3rZfJ8UwARD7Yaz1fkxekBvg5O66WqFU61rJ2hrg0BMu6nxRgwk29JL6jitOg";
        //URL url = new URL("https://sb.area51.vault.skyflowapis.dev/v1/vaults/h54b9fa800cc4916974fdc7407463783/query");
        URL url = new URL("https://sb.area51.vault.skyflowapis.dev/v1/vaults/u4882705de68469d92b5aa1d9ada9740/query");

        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("POST");
        //String authString = "Bearer " + Base64.getEncoder().withoutPadding().encodeToString(token.getBytes("utf-8"));
        String authString = "Bearer " + token1;
        conn.setRequestProperty("Authorization", authString);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

		/*
		 * Scanner sc = new Scanner(System.in); String str = sc.nextLine();
		 */
        String strFinal = "{ \"query\": \"" + s + "\"}";
        //System.out.println(strFinal);
        //String jsonInputString = "{ \"query\": \"select * from medicare;\"}";
        //String jsonInputString = "{ \"query\": \"select * from template;\"}";
        Gson g = new Gson();  
        String s1 = g.toJson(s);  
        		
        try(OutputStream os = conn.getOutputStream()) {
            byte[] input = strFinal.getBytes("utf-8");
            os.write(input, 0, input.length);
        }
//        //conn.getOutputStream().write(postDataBytes);
        int responseCode = conn.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);
        Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (int c; (c = in.read()) >= 0;)
            sb.append((char)c);
        String responsePost = sb.toString();
       
        JsonParser parser = new JsonParser();

        // Post request to json tree
        JsonElement postTree = parser.parse(responsePost.toString());

       // System.out.println(postTree);
        JsonArray postData = null;

        if(postTree.isJsonObject()) {
            JsonElement postRows = postTree.getAsJsonObject().get("records");
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("Rows ---> " + postRows);


            if(postRows.isJsonArray()){
                //System.out.println("PostRows is a json array ..................");
               postData = postRows.getAsJsonArray();
                //System.out.println("&&&&&&&&&&&&&&&&&&&  "+postData.size()+" *************************************%%%%%%%%%%%%%%%%%%%%%%%%%%");
                //System.out.println(postData.get(0));
                //System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                //System.out.println(postData.get(1));

               // System.out.println(postData.getAsJsonObject());
            }
        
    }
		return postData;
    }
    
    

	/*
	 * public HttpResponseHandler(String BASE_URL) {
	 * 
	 * this.BASE_URL = BASE_URL; this.requestFactory = new
	 * NetHttpTransport().createRequestFactory();
	 * 
	 * try {
	 * 
	 * NetHttpTransport.Builder builder = new NetHttpTransport.Builder();
	 * builder.doNotValidateCertificate();
	 * 
	 * this.requestFactory = builder.build().createRequestFactory(); } catch
	 * (GeneralSecurityException e) { throw new
	 * RuntimeException("Got General Security exception: " + e.getMessage()); } }
	 * 
	 * private void setHeaders (HttpRequest request, Map<String, String> headers) {
	 * if (headers != null) {
	 * 
	 * HttpHeaders httpHeaders = request.getHeaders();
	 * 
	 * for (String key : headers.keySet()) { httpHeaders.set(key, headers.get(key));
	 * } } }
	 * 
	 * private void setAuth (HttpRequest request) {
	 * 
	 * // No auth required }
	 * 
	 * public HttpRequest buildGetRequest (String api, Map<String, String> headers)
	 * throws IOException {
	 * 
	 * HttpRequest request = requestFactory.buildGetRequest( new GenericUrl(BASE_URL
	 * + api));
	 * 
	 * setHeaders(request, headers);
	 * 
	 * setAuth(request);
	 * 
	 * return request; }
	 */
}
