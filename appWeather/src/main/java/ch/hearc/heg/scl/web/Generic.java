package ch.hearc.heg.scl.web;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

/**
 * Classe générique pour la récupération de données depuis une API
 */
public class Generic {
    /**
     * Récupération de la réponse depuis l'API
     * @param service
     * @return
     */
    public static String getWebResponse(String service) {
        String response = "";

        try {
            URL url = new URL(service);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            connection.connect();

            BufferedReader br;
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                response = br.lines().collect(Collectors.joining());
            }
        }catch (Exception e) {
            System.out.println("WEB/GENERIC/GETWEBRESPONSE2 : Erreur lors de la recuperation de la reponse du service web");
            e.printStackTrace();
        }finally {
            return response;
        }
    }
}
