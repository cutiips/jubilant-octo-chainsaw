package ch.hearc.heg.scl.dataset;
import ch.hearc.heg.scl.business.Mesure;

import java.sql.* ;

import ch.hearc.heg.scl.business.Ville;

import static ch.hearc.heg.scl.dataset.Tools.*;

/**
 * Classe pour l'insertion des données dans la base de données
 */
public class Insert {

    /**
     * Insertion d'une ville dans la base de données
     * @param sess
     * @param ville
     */
    public static void insertVille(Connection sess, Ville ville) {
        try {
            System.out.println("========== CONNEXION EN COURS ... ==========");

            Connection session = DBOracle.createSession();

            // contrôle si le nom d'une ville existe déjà
            String sqlControl = "SELECT nom FROM ville WHERE nom = ?";
            PreparedStatement pstmtControl = session.prepareStatement(sqlControl);
            pstmtControl.setString(1, ville.getNom());
            ResultSet rs = pstmtControl.executeQuery();
            if (rs.next()) {
                System.out.println("La ville " + ville.getNom() + " existe deja dans la base de donnees");
                return;
            } else {
                String sql = "INSERT INTO ville (openweathermapid, nom, num_pays, latitude, longitude, timezone) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement pstmt = session.prepareStatement(sql);
                pstmt.setInt(1, ville.getOpenWeatherMapId());
                pstmt.setString(2, ville.getNom());
                pstmt.setInt(3, ville.getPays().getNumero());
                pstmt.setDouble(4, ville.getLatitude());
                pstmt.setDouble(5, ville.getLongitude());
                pstmt.setInt(6, ville.getTimezone());
                pstmt.executeUpdate();
                System.out.println("-- Insertion de la ville terminee --");
            }
        } catch (SQLException sqlerr) {
            System.out.println("ERREUR OP insertVille : " + sqlerr);
        }
    }

    /**
     * Insertion des mesures météo dans la base de données
     * @param sess
     * @param ville
     */
    public static void insertMesure(Connection sess, Ville ville) {
        try {

            Connection session = DBOracle.createSession();

            // contrôle si les données météo pour une ville existe déjà
            String sqlControl = "SELECT numero FROM mesure WHERE num_ville = ?";
            int num_ville_int = findVille(session, ville.getNom());
            String num_ville_string = Integer.toString(num_ville_int);
            PreparedStatement pstmtControl = session.prepareStatement(sqlControl);
            pstmtControl.setString(1, num_ville_string);
            ResultSet rs = pstmtControl.executeQuery();

            if (rs.next()) {
                System.out.println("Les mesures de " + ville.getNom() + " existe deja dans la base de donnees");
            }  else {
                for (Long key : ville.getMesures().keySet()) {
                    Mesure mesure = ville.getMesures().get(key);
                    String sql = "INSERT INTO mesure (dt, description, temperature, temperature_ressentie, pression, humidite, num_ville) VALUES (?,?,?,?,?,?,?) ";
                    PreparedStatement pstmt = session.prepareStatement(sql);
                    java.sql.Date dateSQL = new java.sql.Date(mesure.getDate().getTime());
                    pstmt.setDate(1, dateSQL);
                    pstmt.setString(2, mesure.getDescription());
                    pstmt.setDouble(3, mesure.getTemperature());
                    pstmt.setDouble(4, mesure.getTemperature_ressentie());
                    pstmt.setDouble(5, mesure.getPression());
                    pstmt.setDouble(6, mesure.getHumidite());
                    pstmt.setInt(7, findVille(session, ville.getNom()));
                    pstmt.executeUpdate();
                }
                System.out.println("-- Insertion des mesures terminees --");

            }
        } catch (SQLException sqlerr) {
            System.out.println("ERREUR OP insertMesure : "+sqlerr);
            sqlerr.printStackTrace();
        }
    }

    /**
     * Insertion des vents dans la base de données
     * @param sess
     * @param ville
     */
    public static void insertVent(Connection sess, Ville ville) {
        try {

            Connection session = DBOracle.createSession();
            int num_ville = findVille(session, ville.getNom());

            //contrôle si les données du vent pour une ville existe déjà
            String sqlControl = "SELECT numero FROM vent WHERE num_mesure = ?";
            PreparedStatement pstmtControl = session.prepareStatement(sqlControl);
            pstmtControl.setString(1, Integer.toString(findMesure(session, num_ville)));
            ResultSet rs = pstmtControl.executeQuery();
            if (rs.next()) {
                System.out.println("Les données du vent pour la ville " + ville.getNom() + " existent deja dans la base de donnees");
                return;
            } else {
                for (Long key : ville.getMesures().keySet()) {
                    Mesure mesure = ville.getMesures().get(key);
                    String sql = "INSERT INTO vent (vitesse, direction, num_mesure) VALUES (?,?,?)";
                    PreparedStatement pstmt = session.prepareStatement(sql);
                    pstmt.setDouble(1, mesure.getVent().getVitesse());
                    pstmt.setDouble(2, mesure.getVent().getDirection());
                    pstmt.setInt(3, findMesure(session, num_ville));
                    pstmt.executeUpdate();
                }
                System.out.println("-- Insertion des vents termines --");
                System.out.println("========== DECONNEXION EN COURS ... ==========");
            }
        } catch (SQLException sqlerr) {
            System.out.println("ERREUR OP insertVent :"+sqlerr);
        }
    }
}
