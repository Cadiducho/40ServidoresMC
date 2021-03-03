package com.cadiducho.cservidoresmc;

import com.cadiducho.cservidoresmc.model.ServerStats;
import com.cadiducho.cservidoresmc.model.VoteResponse;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CompletableFuture;

public class ApiClient {

    private final String API_URL = "https://40servidoresmc.es/api2.php?clave=";
    private final Gson gson;

    public ApiClient(Gson gson) {
        this.gson = gson;
    }

    public String apiKey() {
        return ""; //fixme: config
    }

    public int timeOut() {
        return 30000; //fixme: config
    }

    public CompletableFuture<VoteResponse> validateVote(String player) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return fetchData("&nombre=" + player, "GET", VoteResponse.class);
            } catch (IOException e) {
                throw new IllegalStateException("Cannot execute API call", e);
            }
        });
    }

    public CompletableFuture<ServerStats> fetchServerStats() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return fetchData("&estadisticas=1", "GET", ServerStats.class);
            } catch (IOException e) {
                throw new IllegalStateException("Cannot execute API call", e);
            }
        });
    }

    /**
     * Obtener datos de la API, según unos parámetros dados, y parsearlo a un objeto
     * @param params Parámetros HTTP de la petición
     * @param method Método HTTP
     * @param type Clase a la que convertir los datos recibidos
     * @param <T> Tipo que retornará
     * @return El objeto con los datos solicitados a la API
     * @throws IOException Si falla al parsear o al conectarse a la API
     */
    private <T> T fetchData(String params, String method, Class<T> type) throws IOException {
        URL url = new URL(API_URL + apiKey() + params);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(method);
        connection.setReadTimeout(timeOut());

        try (Reader reader = new InputStreamReader(connection.getInputStream())) {
            return gson.fromJson(reader, type);
        }
    }
}
