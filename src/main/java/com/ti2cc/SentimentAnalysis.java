package com.ti2cc;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import com.google.gson.Gson;

public class SentimentAnalysis {

    private static String subscriptionKey = "YOUR_API_KEY";  // Substitua com a chave da sua API
    private static String endpoint = "YOUR_ENDPOINT_URL";    // Substitua com o endpoint da API

    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        
        // Exemplo de texto a ser analisado
        String text = "I love programming with Java!";
        
        // Criando o JSON do corpo da requisição
        HashMap<String, Object> requestBody = new HashMap<>();
        HashMap<String, String> document = new HashMap<>();
        document.put("id", "1");
        document.put("language", "en");
        document.put("text", text);
        requestBody.put("documents", new Object[]{document});
        
        // Convertendo o corpo para JSON
        Gson gson = new Gson();
        String json = gson.toJson(requestBody);
        
        // Criando a requisição HTTP
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint + "/text/analytics/v3.0/sentiment"))
                .header("Ocp-Apim-Subscription-Key", subscriptionKey)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        // Enviando a requisição e capturando a resposta
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Mostrando o resultado da API
        System.out.println("Response: " + response.body());
    }
}
