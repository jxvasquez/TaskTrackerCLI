import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.printf("""
                    Acciones:
                    1. add
                    2. delete
                    3. update
                    4. list
                    Comando:\s""");

        String comando = sc.nextLine();
        TaskManager gestor = new TaskManager("tareas.json");

        switch (comando) {
            case "1":
                System.out.print("Ingrese su tarea: ");
                String task = sc.nextLine();
                if (task != null) {
                    gestor.addTask(task);
                } else {
                    System.out.println("Error: Debes escribir la descripcion de la tarea");
                }
                break;
            case "2":
                System.out.print("Ingrese su tarea a eliminar: ");
                int taskToDelete = sc.nextInt();
                sc.nextLine();
                if (taskToDelete != 0) {
                    gestor.deleteTask(taskToDelete);
                } else {
                    System.out.println("Error: Debes escribir el ID que se va a eliminar");
                }
                break;
            case "3":
                System.out.print("Ingrese el ID de la tarea a editar: ");
                int idToUpdate = sc.nextInt();
                sc.nextLine();
                if (idToUpdate != 0) {
                    System.out.print("Ingrese el nuevo estado (TODO, IN_PROGRESS o DONE): ");
                    String newStatus = sc.nextLine();
                    String textClean = newStatus.trim().toUpperCase().replace(" ", "_");

                    Status convertedStatus = null;
                    try {
                        convertedStatus = Status.valueOf(textClean);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: El estado ingresado no es valido. Usa TODO, IN_PROGRESS o DONE.");
                        break;
                    }

                    gestor.updateTask(idToUpdate, convertedStatus);
                } else {
                    System.out.println("Error: Debes escribir un ID valido");
                }
                break;
            case "4":
                gestor.listTasks();
                break;
            default:
                System.out.println("Error: Comando no reconocido");
                break;
        }
    }
}