package ch.heg.ig.sda.service;

/**
 * Une exception personnalisée pour les erreurs spécifiques de la couche de ch.heg.ig.sda.service
 */
public class ServiceException extends Exception{

    /**
     * Constructeur avec un message d'erreur
     *
     * @param message d'erreur
     */
    public ServiceException(String message){
        super(message);
    }

    /**
     * Constructeur avec un message d'erreur et une cause
     *
     * @param message message d'erreur
     * @param cause cause de l'erreur
     */
    public ServiceException(String message, Throwable cause){
        super(message, cause);
    }
}
