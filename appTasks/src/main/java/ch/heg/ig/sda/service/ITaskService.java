package ch.heg.ig.sda.service;

import ch.heg.ig.sda.business.Task;
import java.util.List;

/**
 * Interface définissant les opérations disponibles pour la gestion des tâches
 */
public interface ITaskService {
    /**
     * Ajoute une nouvelle tâche avec la description spécifiée
     *
     * @param description La description de la tâche à ajouter
     */
    void addTask(String description);

    /**
     * Récupère la liste de toutes les tâches existantes
     *
     * @return La liste de toutes les tâches.
     */
    List<Task> getAllTasks();

    /**
     * Marque une tâche spécifique comme terminée
     *
     * @param task La tâche à marquer comme terminée
     */
    void markTaskAsCompleted(Task task) throws ServiceException ;

    /**
     * Supprime toutes les tâches marquées comme terminées de la liste
     */
    void removeCompletedTasks();
}
