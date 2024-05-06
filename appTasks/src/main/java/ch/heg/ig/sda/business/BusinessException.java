package ch.heg.ig.sda.business;

import ch.heg.ig.sda.service.ServiceException;

/**
 * Une exception personnalisée pour les erreurs spécifiques de la couche métier
 */
public class BusinessException extends ServiceException {

    /**
     * Constructeur avec un message d'erreur
     *
     * @param message, le message d'erreur
     */
    public BusinessException(String message){
        super(message);
    }
}
