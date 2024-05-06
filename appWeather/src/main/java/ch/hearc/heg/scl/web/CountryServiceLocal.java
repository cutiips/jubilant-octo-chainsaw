package ch.hearc.heg.scl.web;

import ch.hearc.heg.scl.dataset.DBOracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Classe pour la récupération des données locales
 */
public class CountryServiceLocal {
    /**
     * Récupération des données locales
     * @param codePays
     * @return
     */
    public static String getLocalData(String codePays){
        try {
            Connection sess = DBOracle.createSession();
            PreparedStatement pstmt = sess.prepareStatement("SELECT nom,code FROM pays WHERE code = ?");

            pstmt.setString(1, codePays);

            try (ResultSet cur = pstmt.executeQuery()) {
                while (cur.next()) {
                    String nom = cur.getString("nom");
                    String code = cur.getString("code");

                    String var = nom + " " + code;

                    return var;
                }
            }

        } catch (Exception sqler) {
            System.out.println(sqler);
        } finally {
            return "erreur - db1";
        }

    }

}
