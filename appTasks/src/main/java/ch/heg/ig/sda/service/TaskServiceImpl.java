package ch.heg.ig.sda.service;

import ch.heg.ig.sda.business.BusinessException;
import ch.heg.ig.sda.business.Task;
//import ch.heg.ig.sda.business.TaskManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Implémentation de l'interface TaskService qui fournit des opérations
 * de gestion des tâches en utilisant TaskManager
 */
public class TaskServiceImpl implements ITaskService {
    private final List<Task> tasks;

    /**
     * Constructeur de la classe TaskServiceImpl
     * Initialise un gestionnaire de tâches.
     */
    public TaskServiceImpl() {
        tasks = new ArrayList<>();
    }

    /**
     * Ajoute une nouvelle tâche avec la description spécifiée
     *
     * @param description La description de la tâche à ajouter
     */
    @Override
    public final void addTask(String description) {
        Task task = new Task(description);
        tasks.add(task);
    }

    /**
     * Récupère la liste de toutes les tâches existantes
     *
     * @return La liste de toutes les tâches
     */
    @Override
    public final List<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Marque une tâche spécifique comme terminée
     *
     * @param task La tâche à marquer comme terminée
     */
    @Override
    public void markTaskAsCompleted(Task task) throws ServiceException {
        for (Task t : tasks) {
            if (t.equals(task)) {
                t.markAsCompleted();
                return;
            }
        }
        throw new ServiceException("La tâche n'existe pas.");
    }

    /**
     * Supprime toutes les tâches marquées comme terminées de la liste
     */
    @Override
    public final void removeCompletedTasks() {
        tasks.removeIf(Task::isCompleted);
    }
}
