package ch.heg.ig.sda.business;


/**
 * Classe représentant une tâche à faire
 */
public class Task {
    private final String description;
    private boolean completed;

    /**
     * Constructeur de la classe Task
     *
     * @param description La description de la tâche
     */
    public Task(String description) {
        this.description = description;
        this.completed = false;
    }

    /**
     * Obtient la description de la tâche
     *
     * @return La description de la tâche
     */
    public String getDescription() {
        return description;
    }

    /**
     * Vérifie si la tâche est terminée
     *
     * @return True si la tâche est terminée, sinon false
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Marque la tâche comme terminée
     */
    public void markAsCompleted() {
        completed = true;
    }

    /**
     * Fournit une représentation sous forme de chaîne de la tâche
     *
     * @return Une chaîne représentant la tâche, incluant la description et l'état de complétion
     */
    @Override
    public String toString() {
        return description + (completed ? " (Completed)" : "");
    }
}
