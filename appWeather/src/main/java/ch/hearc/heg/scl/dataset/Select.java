package ch.hearc.heg.scl.dataset;

import ch.hearc.heg.scl.business.Ville;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe pour les requêtes SELECT
 */
public class Select {

    /**
     * Retourne le nom du pays en fonction de son code
     * interrogation la base de données
     * @param var
     */
    public static void CodeCountry(String var) {
        try {
            System.out.println("Connexion a la base de donnees Oracle");

            Connection sess = DBOracle.createSession() ;
            Statement stmt = sess.createStatement();

            ResultSet cur = stmt.executeQuery("SELECT nom,code,numero FROM pays WHERE code ="+ var);

            while (cur.next()) {
                System.out.print(cur.getString("nom"));
            }

            cur.close();
            stmt.close();
            DBOracle.dropSession(sess);

            System.out.println("\nConnexion a la base de donnees Oracle terminee");

        } catch (SQLException sqler) {
            System.out.println(sqler);
            System.out.println(sqler.getErrorCode());
            System.out.println(sqler.getMessage() );
        }
    }

    /**
     * Retourne les informations de la ville en fonction de son nom
     * interrogation la base de données
     * @param ville
     */
    public static void VilleInfo(Ville ville) {
        try {
            int var = ville.getPays().getNumero();

            System.out.println(var);

            System.out.println("Connexion a la base de donnees Oracle");

            Connection sess = DBOracle.createSession() ;
            Statement stmt = sess.createStatement();

            ResultSet cur = stmt.executeQuery("SELECT numero,openweathermapid, nom, num_pays, timezone FROM ville WHERE num_pays ="+ var);

            while (cur.next()) {
                System.out.println(cur.getString("numero"));
                System.out.println(cur.getString("openweathermapid"));
                System.out.println(cur.getString("nom"));
                System.out.println(cur.getString("num_pays"));
                System.out.println(cur.getString("timezone"));
            }

            cur.close();
            stmt.close();
            DBOracle.dropSession(sess);

            System.out.println("\nConnexion a la base de donnees Oracle terminee");

        } catch (SQLException sqler) {
            System.out.println(sqler);
            System.out.println(sqler.getErrorCode());
            System.out.println(sqler.getMessage() );
        }
    }

    /**
     * Retourne les coordonnées de la ville en fonction de sa latitude et longitude
     * interrogation la base de données
     * @param ville
     */
    public static void CoordInfo(Ville ville) {
        try {
            Double lat = ville.getLatitude();
            Double lon = ville.getLongitude();

            System.out.println("Latitude : " + lat + "Longitude : " + lon);

            System.out.println("Connexion a la base de donnees Oracle");

            Connection sess = DBOracle.createSession() ;
            Statement stmt = sess.createStatement();

            ResultSet cur = stmt.executeQuery("SELECT numero,openweathermapid, nom, num_pays, timezone FROM ville WHERE latitude ="+ lat + "AND longitude ="+ lon);

            while (cur.next()) {
                System.out.println(cur.getString("numero"));
                System.out.println(cur.getString("openweathermapid"));
                System.out.println(cur.getString("nom"));
                System.out.println(cur.getString("num_pays"));
                System.out.println(cur.getString("timezone"));
            }

            cur.close();
            stmt.close();
            DBOracle.dropSession(sess);

            System.out.println("\nConnexion a la base de donnees Oracle terminee");

        } catch (SQLException sqler) {
            System.out.println(sqler);
            System.out.println(sqler.getErrorCode());
            System.out.println(sqler.getMessage() );
        }
    }

    /**
     * Retourne toutes les villes
     * interrogation la base de données
     */
    public static void selectAllVilles() {
        try {
            System.out.println("Connexion a la base de donnees Oracle");

            Connection sess = DBOracle.createSession() ;
            Statement stmt = sess.createStatement();

            ResultSet cur = stmt.executeQuery("SELECT nom FROM ville");

            int i = 1;

            while (cur.next()) {
                System.out.print("["+ i++ + "] ");
                System.out.println(cur.getString("nom"));
            }

            cur.close();
            stmt.close();
            DBOracle.dropSession(sess);

            System.out.println("\nConnexion a la base de donnees Oracle terminee");

        } catch (SQLException sqler) {
            System.out.println(sqler);
            System.out.println(sqler.getErrorCode());
            System.out.println(sqler.getMessage() );
        }
    }

    /**
     * Retourne toutes les mesures
     * interrogation la base de données
     */
    public static void selectAllMesures() {
        try {
            System.out.println("Connexion a la base de donnees Oracle");

            Connection sess = DBOracle.createSession() ;
            Statement stmt = sess.createStatement();

            ResultSet cur = stmt.executeQuery("SELECT numero, dt, description, temperature, temperature_ressentie, pression, humidite FROM mesure");

            int i = 1;

            while (cur.next()) {
                System.out.print("["+ i++ + "] ");
                System.out.print("Numero : "+cur.getString("numero"));
                System.out.print(" Date : "+cur.getDate("dt"));
                System.out.print(" Description : "+cur.getString("description"));
                System.out.print(" Temperature : "+cur.getDouble("temperature")+ " °C");
                System.out.print(" Temperature ressentie : "+cur.getDouble("temperature_ressentie")+ " °C");
                System.out.print(" Pression : "+cur.getInt("pression")+ " hPa");
                System.out.print(" Humidite : "+cur.getInt("humidite")+ " %");
                System.out.println();
            }

            cur.close();
            stmt.close();
            DBOracle.dropSession(sess);

            System.out.println("\nConnexion a la base de donnees Oracle terminee");

        } catch (SQLException sqler) {
            System.out.println(sqler);
            System.out.println(sqler.getErrorCode());
            System.out.println(sqler.getMessage() );
        }
    }

    /**
     * Retourne toutes les données sur les vents
     * interrogation la base de données
     */
    public static void selectAllVents() {
        try {
            System.out.println("Connexion a la base de donnees Oracle");

            Connection sess = DBOracle.createSession();
            Statement stmt = sess.createStatement();

            ResultSet cur = stmt.executeQuery("SELECT numero, vitesse, direction FROM vent");

            int i = 1;

            while (cur.next()) {
                System.out.print("[" + i++ + "] ");
                System.out.print("Numero : "+cur.getInt("numero"));
                System.out.print(" Vitesse :  "+cur.getDouble("vitesse"));
                System.out.print(" Direction : "+cur.getInt("direction"));
                System.out.println();
            }

            cur.close();
            stmt.close();
            DBOracle.dropSession(sess);

            System.out.println("\nConnexion a la base de donnees Oracle terminee");

        } catch (SQLException sqler) {
            System.out.println(sqler);
            System.out.println(sqler.getErrorCode());
            System.out.println(sqler.getMessage());
        }
    }

    /**
     * Retourne toutes les données sur les villes, les mesures et les vents
     * interrogation la base de données
     */
    public static void selectAll() {
        try {
            System.out.println("Connexion a la base de donnees Oracle");

            Connection sess = DBOracle.createSession();
            Statement stmt = sess.createStatement();

            ResultSet cur = stmt.executeQuery("SELECT ville.numero, ville.nom, mesure.dt, mesure.description, mesure.temperature, mesure.temperature_ressentie, mesure.pression, mesure.humidite, vent.vitesse, vent.direction FROM ville, mesure, vent WHERE ville.numero = mesure.num_ville AND mesure.numero = vent.num_mesure ORDER BY ville.numero ASC");

            int i = 1;

            while (cur.next()) {
                System.out.print("["+cur.getInt("numero")+"]");
                System.out.println(" Nom de la ville : "+cur.getString("nom"));
                System.out.print(" Date : "+cur.getDate("dt"));
                System.out.print(" Description : "+cur.getString("description"));
                System.out.print(" Temperature : "+cur.getDouble("temperature")+ " °C");
                System.out.print(" Temperature ressentie : "+cur.getDouble("temperature_ressentie")+ " °C");
                System.out.print(" Pression : "+cur.getInt("pression")+ " hPa");
                System.out.print(" Humidite : "+cur.getInt("humidite")+ " %");
                System.out.print(" Vitesse :  "+cur.getDouble("vitesse"));
                System.out.print(" Direction : "+cur.getInt("direction"));
                System.out.println();
            }

            cur.close();
            stmt.close();
            DBOracle.dropSession(sess);

            System.out.println("\nConnexion a la base de donnees Oracle terminee");

        } catch (SQLException sqler) {
            System.out.println(sqler);
            System.out.println(sqler.getErrorCode());
            System.out.println(sqler.getMessage());
        }
    }

    /**
     * Retourne les mesures et les vents en fonction de la ville
     * interrogation la base de données
     * @param ville
     */
    public static void selectMesureVent(Ville ville) {
        try {
            System.out.println("Connexion a la base de donnees Oracle");

            Connection sess = DBOracle.createSession();
            Statement stmt = sess.createStatement();

            ResultSet cur = stmt.executeQuery("SELECT mesure.dt, mesure.description, mesure.temperature, mesure.temperature_ressentie, mesure.pression, mesure.humidite, vent.vitesse, vent.direction FROM ville, mesure, vent WHERE ville.numero = mesure.num_ville AND mesure.numero = vent.num_mesure AND ville.nom = '"+ville.getNom()+"'");

            int i = 1;

            while (cur.next()) {
                System.out.print("[" + i++ + "] ");
                System.out.print(" Date : "+cur.getDate("dt"));
                System.out.print(" Description : "+cur.getString("description"));
                System.out.print(" Temperature : "+cur.getDouble("temperature")+ " °C");
                System.out.print(" Temperature ressentie : "+cur.getDouble("temperature_ressentie")+ " °C");
                System.out.print(" Pression : "+cur.getInt("pression")+ " hPa");
                System.out.print(" Humidite : "+cur.getInt("humidite")+ " %");
                System.out.print(" Vitesse :  "+cur.getDouble("vitesse"));
                System.out.print(" Direction : "+cur.getInt("direction"));
                System.out.println();
            }

            cur.close();
            stmt.close();
            DBOracle.dropSession(sess);

            System.out.println("\nConnexion a la base de donnees Oracle terminee");

        } catch (SQLException sqler) {
            System.out.println(sqler);
            System.out.println(sqler.getErrorCode());
            System.out.println(sqler.getMessage());
        }
    }

    /** NEW 240327
     * Retourne les mesures et les vents en fonction de la ville
     * interrogation la base de données
     * @param ville
     */
    public static void selectDataFromCity(String ville) {
        try {
            System.out.println("Connexion a la base de donnees Oracle");

            Connection sess = DBOracle.createSession();
            Statement stmt = sess.createStatement();

            ResultSet cur = stmt.executeQuery("SELECT ville.nom, mesure.dt, mesure.description, mesure.temperature, mesure.temperature_ressentie, mesure.pression, mesure.humidite, vent.vitesse, vent.direction FROM ville, mesure, vent WHERE ville.numero = mesure.num_ville AND mesure.numero = vent.num_mesure AND ville.nom = '"+ville+"'");

            while (cur.next()) {
                System.out.println("Nom de la ville : "+cur.getString("nom"));
                System.out.println(" Date : "+cur.getDate("dt"));
                System.out.println(" Description : "+cur.getString("description"));
                System.out.println(" Temperature : "+cur.getDouble("temperature")+ " °C");
                System.out.println(" Temperature ressentie : "+cur.getDouble("temperature_ressentie")+ " °C");
                System.out.println(" Pression : "+cur.getInt("pression")+ " hPa");
                System.out.println(" Humidite : "+cur.getInt("humidite")+ " %");
                System.out.println(" Vitesse :  "+cur.getDouble("vitesse"));
                System.out.println(" Direction : "+cur.getInt("direction"));
                System.out.println();
            }

            cur.close();
            stmt.close();
            DBOracle.dropSession(sess);

            System.out.println("\nConnexion a la base de donnees Oracle terminee");

        } catch (SQLException sqler) {
            System.out.println(sqler);
            System.out.println(sqler.getErrorCode());
            System.out.println(sqler.getMessage());
        }
    }

    /** NEW 240327
     * Retourne le numéro de la ville en fonction de son nom
     */
    public static Ville getVilleByNumber(int numero){
        try {
            Connection sess = DBOracle.createSession();
            Statement stmt = sess.createStatement();

            String query = "SELECT nom FROM ville WHERE numero = " + numero;
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                String nomVille = rs.getString("nom");
                return getVilleByName(nomVille);
            }
        } catch (SQLException sqler) {
            System.out.println(sqler);
            System.out.println(sqler.getErrorCode());
            System.out.println(sqler.getMessage() );
        }
        return null;
    }

    /** NEW 240327
     * Retourne la ville en fonction de son nom
     */
    public static Ville getVilleByName(String nomVille) {
        try {
            Connection sess = DBOracle.createSession();
            Statement stmt = sess.createStatement();

            String query = "SELECT * FROM ville WHERE nom = '" + nomVille + "'";
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                Ville ville = new Ville();
                ville.setNom(rs.getString("nom"));
                ville.setLatitude(rs.getDouble("latitude"));
                ville.setLongitude(rs.getDouble("longitude"));

                Select.selectDataFromCity(ville.getNom());

                return ville;
            }
        } catch (SQLException sqler) {
            System.out.println(sqler);
            System.out.println(sqler.getErrorCode());
            System.out.println(sqler.getMessage() );
        }
        return null;
    }

    /** NEW 240327
     * Retourne le numéro de la ville en fonction de son nom
     */
    public static void selectAllVillesWithNumber() {
        try {
            System.out.println("Connexion a la base de donnees Oracle");

            Connection sess = DBOracle.createSession();
            Statement stmt = sess.createStatement();

            ResultSet cur = stmt.executeQuery("SELECT numero, nom FROM ville ORDER BY numero ASC");

            int i = 1;

            while (cur.next()) {
                System.out.print("["+cur.getInt("numero")+ "]");
                System.out.print(" Nom : "+cur.getString("nom"));
                System.out.println();
            }

            cur.close();
            stmt.close();
            DBOracle.dropSession(sess);

            System.out.println("Connexion a la base de donnees Oracle terminee");

        } catch (SQLException sqler) {
            System.out.println(sqler);
            System.out.println(sqler.getErrorCode());
            System.out.println(sqler.getMessage());
        }
    }
}
