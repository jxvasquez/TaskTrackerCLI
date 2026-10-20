import java.util.ArrayList;
import java.util.List;

public class TaskManager   {
    private List<Task> tasks;
    private JsonManager gestorJSON;

    public TaskManager(String rutaArchivo) {
        this.tasks = new ArrayList<>();
        this.gestorJSON = new JsonManager(rutaArchivo);
    }

    public void addTask(String description) {
        int newId = tasks.size()+1;
        Task newTask = new Task(newId, description);
        tasks.add(newTask);
        System.out.printf("""
                Tarea Creada:
                \tId: %d
                \tDescripcion: %s
                \tEstado: %s
                """,newId,description, Status.TODO.getStatus());

        gestorJSON.saveTaskJSON(tasks);
    }

    public void deleteTask(int id) {
        Task taskToDelete = null;

        for (Task task : tasks) {
            if (task.getId() == id) {
                taskToDelete = task;
                break;
            }
        }

        if (taskToDelete == null) {
            System.out.println("Error: No se encontro la tarea con Id: " + id);
            return;
        }

        String deletedDescription = taskToDelete.getDescription();
        tasks.remove(taskToDelete);

        System.out.printf("""
                Tarea Eliminada:
                \tId: %d
                \tDescripcion: %s 
                """, id, deletedDescription);

        gestorJSON.saveTaskJSON(tasks);
    }

    public void updateTask (int id, Status newStatus){
        Task taskFinded = null;
        for (Task task : tasks) {
            if (task.getId() == id) {
                taskFinded = task;
                break;
            }
        }
        if (taskFinded == null) {
            System.out.println("Error: No se encontro la tarea con Id: " + id);
            return;
        }
        taskFinded.setStatus(newStatus);
        System.out.println("Estado actualizado.");

        gestorJSON.saveTaskJSON(tasks);
    }

    public void listTasks() {
        if (tasks.isEmpty()){
            System.out.println("No hay tareas registradas.");
            return;
        }
        System.out.println("\n--- LISTA DE TAREAS ---");
        for (Task task : tasks) {
            System.out.printf("ID: %d | Estado: %s | Descripcion: %s\n",
                    task.getId(),
                    task.getStatus().getStatus(),
                    task.getDescription());
        }
        System.out.println("-----------------------\n");
    }

}
