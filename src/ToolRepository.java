import java.io.*;
import java.nio.file.*;
import java.util.*;

public class ToolRepository {
    private final Path filePath = Paths.get("inventory_data.dat");
    private Map<String, Tool> tools = new HashMap<>();

    public void loadData() {
        if (Files.exists(filePath)) {
            try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(filePath))) {
                @SuppressWarnings("unchecked")
                Map<String, Tool> loaded = (Map<String, Tool>) ois.readObject();
                tools = loaded;
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Could not load inventory data. Starting fresh.");
            }
        }
    }

    public void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(filePath))) {
            oos.writeObject(tools);
        } catch (IOException e) {
            System.out.println("Error saving inventory data: " + e.getMessage());
        }
    }

    public void save(Tool tool) {
        tools.put(tool.getId(), tool);
        saveData();
    }

    public Optional<Tool> findById(String id) {
        return Optional.ofNullable(tools.get(id));
    }

    public List<Tool> findAll() {
        return new ArrayList<>(tools.values());
    }
}
