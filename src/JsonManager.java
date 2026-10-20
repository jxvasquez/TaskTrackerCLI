import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.List;

public class JsonManager  {
    String ruta = "src/taskList.json";

    public JsonManager (String rutaIngresada) {
        this.ruta = rutaIngresada;
    }

    public void saveTaskJSON (List<Task> tasks) {
        String jsonResult = "[";
        int totaltask = tasks.size();
        int cont = 1;

        for (Task task : tasks) {
            String builder = """
                    {"id": %d,
                    "description": "%s",
                    "status": "%s",
                    "createdAt": %s,
                    "updatedAt": %s }
                    """;
            jsonResult += builder.formatted(task.getId(), task.getDescription(), task.getStatus().getStatus(), task.getCreatedAt(), task.getUpdatedAt());

            if (cont < totaltask) {
                jsonResult += ",";
            }
            cont++;
        }
        jsonResult += "]";
        try {
            Path path = Path.of(ruta);
            Files.writeString(path, jsonResult);
            System.out.println("Tareas guardadas exitosamente");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
