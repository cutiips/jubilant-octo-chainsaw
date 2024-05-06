package ch.hearc.heg.scl.io;

import java.util.Scanner;
import ch.hearc.heg.scl.business.Ville;
import ch.hearc.heg.scl.dataset.DBOracle;
import ch.hearc.heg.scl.dataset.Insert;
import ch.hearc.heg.scl.dataset.Select;
import ch.hearc.heg.scl.web.OpenWeatherMapService;

/**
 * Classe pour le menu
 */
public class Menu {

    /**
     * Menu principal
     */
    public static void menu() {

        Scanner clavier = new Scanner(System.in);

        boolean exit = false;

        while(!exit){
            int choice = 1;

            System.out.println("Que voulez-vous faire ?");
            System.out.println("(1) Inserer une ville dans la base de donnees");
            System.out.println("(2) Afficher des villes depuis la base de donnees");
            System.out.println("(3) Afficher des villes depuis OpenWeatherMap");
            System.out.println("(4) Afficher les donnees d'une ville");
            System.out.println("(9) Quitter");
            System.out.println();
            System.out.print("Mon choix : ");

            choice = Integer.parseInt(clavier.nextLine());

            switch (choice){
                case 1 : insertCity();
                     break;
                case 2 : selectCity();
                    break;
                case 3 : selectCityFromOpenWeatherMap();
                    break;
                case 4 : selectCityData();
                    break;
                case 9 : exit = true;
                    break;
                default: System.out.println("Veuillez faire un choix");
            }
        }
    }

    /**
     * Insertion d'une ville dans la base de données
     * persistence
     */
    static void insertCity(){
        Ville ville = Saisie.saisirLatitudeLongitude();

        //récupération des data depuis l'API
        Ville WebResponse = OpenWeatherMapService.getData(ville);

        //si les coordonnées ne sont pas valides
        if (WebResponse == null){
            System.out.println("Les coordonnees ne sont pas valides");
            return;
        }

        //persistence des données
        Insert.insertVille(DBOracle.createSession(), WebResponse);
        Insert.insertMesure(DBOracle.createSession(), WebResponse);
        Insert.insertVent(DBOracle.createSession(), WebResponse);
    }

    /**
     * Affichage des villes de la base de données
     * persistence
     */
    static void selectCity(){
        Select.selectAll();
    }

    /**
     * Affichage des villes depuis OpenWeatherMap
     * web service
     */
    static void selectCityFromOpenWeatherMap(){
        Ville ville = Saisie.saisirLatitudeLongitude();
        Ville WebResponse = OpenWeatherMapService.getData(ville);
        System.out.println(WebResponse);;
    }

    /**
     * Affichage des données d'une ville
     * persistence
     */
    static void selectCityData(){
        Select.selectAllVillesWithNumber();

        System.out.print("Entrez le numero de la ville : ");

        int choix = Integer.parseInt(new Scanner(System.in).nextLine());

        Ville ville = Select.getVilleByNumber(choix);

        if(ville == null){
            System.out.println("La ville n'existe pas");
            return;
        }
    }
}
