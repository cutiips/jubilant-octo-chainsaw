package ch.hearc.heg.scl.dataset;

import java.sql.* ;
import static java.sql.DriverManager.*; //registerDriver

/**
 * Classe pour la gestion de la base de données Oracle
 */
public class DBOracle {

    /**
     * Création d'une session
     * @return
     */
    public static Connection createSession() {
        try {
            registerDriver(new oracle.jdbc.OracleDriver());
            Connection sess = getConnection("--URL--");

            sess.setAutoCommit(true);

            return sess;

        } catch (SQLException sqlerr) {
            System.out.println(sqlerr);
        }
        return null ;
    }
    public static void dropSession( Connection sess) {
        try {
            sess.close();
        } catch (SQLException sqlerr) {
            System.out.println(sqlerr);
        }
    }
}

