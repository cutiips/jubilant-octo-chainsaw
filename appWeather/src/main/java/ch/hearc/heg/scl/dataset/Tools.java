package ch.hearc.heg.scl.dataset;

import ch.hearc.heg.scl.business.Mesure;
import ch.hearc.heg.scl.business.Ville;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe d'outils pour la recherche de données dans la base de données
 */
public class Tools {

    /**
     * Recherche et retourne le numéro d'une ville dans la base de données
     * @param sess
     * @param nom
     * @return
     */
    public static int findVille(Connection sess, String nom) {
        try {
            Connection session = DBOracle.createSession();

            String sql = "SELECT numero FROM ville WHERE nom = ?";
            PreparedStatement pstmt = session.prepareStatement(sql);
            pstmt.setString(1, nom);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException sqlerr) {
            System.out.println(sqlerr);
        }
        return -1;
    }

    /**
     * Recherche et retourne le numéro d'une mesure dans la base de données
     * @param sess
     * @param ville
     * @return
     */
    public static int findMesure(Connection sess, int ville) {
        try {
            Connection session = DBOracle.createSession();

            String sql = "SELECT numero FROM mesure WHERE num_ville = "+ville;
            PreparedStatement pstmt = session.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException sqlerr) {
            System.out.println(sqlerr);
        }
        return -1;
    }

    /**
     * Recherche et retourne le numéro d'un vent dans la base de données
     * @param sess
     * @param mesure
     * @return
     */
    public static int findVent(Connection sess, int mesure) {
        try {
            Connection session = DBOracle.createSession();

            String sql = "SELECT numero FROM vent WHERE num_mesure = "+mesure;
            PreparedStatement pstmt = session.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException sqlerr) {
            System.out.println(sqlerr);
        }
        return -1;
    }
}
