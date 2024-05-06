package ch.heg.ig.sda.app;

import ch.heg.ig.sda.business.Task;
import ch.heg.ig.sda.service.ITaskService;
import ch.heg.ig.sda.service.ServiceException;


import java.util.List;
import java.util.Scanner;

/**
 * Classe pour la gestion de l'entrée de l'utilisateur liée aux tâches
 */

public class TaskInput {

    /**
     * Gère les input de l'utilisateur pour marquer une tâche comme terminée
     *
     * @param taskServiceIN, le ch.heg.ig.sda.service de gestion des tâches
     * @param tasksIN, la liste des tâches disponibles
     */
    public void userInput (ITaskService taskServiceIN, List<Task> tasksIN) {
        Scanner input = new Scanner(System.in);
        int userInt = input.nextInt()-1;

        try {
            if (userInt < 0 || userInt >= tasksIN.size()){
                throw new ServiceException("Tâche invalide ou inexistante");
            }

            Task taskToComplete = tasksIN.get(userInt);
            taskServiceIN.markTaskAsCompleted(taskToComplete);

            System.out.println("Tâche marquée comme terminée : " + taskToComplete.getDescription());
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
    }
}
