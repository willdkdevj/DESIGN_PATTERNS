package br.com.alura.loja.adapters.http;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

public class JavaHttpClient implements HttpAdapter {
    @Override
    public void post(String url, Map<String, Object> dados) {
        try {
            URL urlApi = new URL(url);
            URLConnection connection = urlApi.openConnection();
            connection.connect();;
        } catch (IOException e) {
            throw new RuntimeException("Erro ao enviar requisição ao webservice parceiro!");
        }
    }
}
