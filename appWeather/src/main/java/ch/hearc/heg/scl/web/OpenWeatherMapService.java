package ch.hearc.heg.scl.web;

import ch.hearc.heg.scl.business.Mesure;
import ch.hearc.heg.scl.business.Pays;
import ch.hearc.heg.scl.business.Vent;
import ch.hearc.heg.scl.business.Ville;
import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Service pour la récupération des données depuis l'API
 */
public class OpenWeatherMapService {
    private static final String API_KEY = "--API_KEY--";
    private static final String UNITS = "metric";
    private static final String LANG = "fr";

    private static final String BASE_URL = "--URL--";

    /**
     * Récupération du JSON depuis l'API
     * @param ville
     * @return
     */
    private static String getJSON(Ville ville) {
        String service = BASE_URL + "?lat=" + ville.getLatitude() + "&lon=" + ville.getLongitude() + "&units=" + UNITS + "&lang=" + LANG + "&appid=" + API_KEY;
        return Generic.getWebResponse(service);
    }

    /**
     * Désérialisation du JSON pour la ville et la mesure
     * @param ville
     * @return
     */
    public static Ville getData(Ville ville){
        String webResponse = getJSON(ville);
        Ville villeReponse = new Ville();

        villeReponse = getDataVille(webResponse);

        if (villeReponse.getOpenWeatherMapId() == 0){
            return null;
        } else {
            Mesure mesure = getDataMesure(webResponse);
            villeReponse.addMesure(mesure);
        }

        return villeReponse;
    }

    /**
     * Désérialisation du JSON pour la ville
     * @param webResponse
     * @return
     */
    public static Ville getDataVille(String webResponse){
        Ville villeReponse = new Ville();

        try {
            JsonObject deserialize = (JsonObject) Jsoner.deserialize(webResponse);

            BigDecimal openWeatherMapID = (BigDecimal) deserialize.get("id");
            villeReponse.setOpenWeatherMapId(openWeatherMapID.intValue());

            String nom = (String) deserialize.get("name");
            villeReponse.setNom(nom);

            JsonObject sys = (JsonObject) deserialize.get("sys");
            String codePays = (String) sys.get("country");
            Pays pays = CountryService.getData(codePays);
            villeReponse.setPays(pays);

            JsonObject coord = (JsonObject) deserialize.get("coord");
            BigDecimal latitude = (BigDecimal) coord.get("lat");
            villeReponse.setLatitude(latitude.doubleValue());
            BigDecimal longitude = (BigDecimal) coord.get("lon");
            villeReponse.setLongitude(longitude.doubleValue());

            BigDecimal timezone = (BigDecimal) deserialize.get("timezone");
            villeReponse.setTimezone(timezone.intValue());
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println("WEB/OWM/GETDATAVILLE : Erreur de deserialisation de la ville : " + ex.getMessage());
        }finally {
            return villeReponse;
        }
    }

    /**
     * Désérialisation du JSON pour la mesure
     * @param webResponse
     * @return
     */
    public static Mesure getDataMesure(String webResponse){
        Mesure mesureReponse = new Mesure();

        try{
            JsonObject deserialize = (JsonObject) Jsoner.deserialize(webResponse);

            BigDecimal dt = (BigDecimal) deserialize.get("dt");
            mesureReponse.setDate(new Date(dt.longValue() * 1000));

            JsonArray weather = (JsonArray) deserialize.get("weather");
            String description = (String) ((JsonObject) weather.get(0)).get("description");
            mesureReponse.setDescription(description);

            JsonObject main = (JsonObject) deserialize.get("main");

            BigDecimal temperature = (BigDecimal) main.get("temp");
            mesureReponse.setTemperature(temperature.doubleValue());

            BigDecimal temperature_ressentie = (BigDecimal) main.get("feels_like");
            mesureReponse.setTemperature_ressentie(temperature_ressentie.doubleValue());

            BigDecimal pression = (BigDecimal) main.get("pressure");
            mesureReponse.setPression(pression.doubleValue());

            BigDecimal humidite = (BigDecimal) main.get("humidity");
            mesureReponse.setHumidite(humidite.doubleValue());

            JsonObject wind = (JsonObject) deserialize.get("wind");
            Vent vent = new Vent();
            BigDecimal vitesse = (BigDecimal) wind.get("speed");
            vent.setVitesse(vitesse.doubleValue());
            BigDecimal direction = (BigDecimal) wind.get("deg");
            vent.setDirection(direction.doubleValue());
            mesureReponse.setVent(vent);
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println("WEB/OWM/GETDATAVILLE : Erreur de deserialisation de la mesure : " + ex.getMessage());
        }finally {
            return mesureReponse;
        }
    }
}
