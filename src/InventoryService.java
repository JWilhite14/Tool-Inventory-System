import java.util.List;

public class InventoryService {
    private final ToolRepository repository;

    public InventoryService(ToolRepository repository){
        this.repository = repository;
    }

    // 1. Add a new tool to the inventory
    public void addTool(String id, String name, String category) {
        String formattedID = id.toUpperCase();
        String formattedCat = category.toUpperCase();
        // Create the new Tool object
        Tool newTool = new Tool(formattedID, name, formattedCat);

        // Pass it to the repository to save it to memory and disk
        repository.save(newTool);
        System.out.println("Success: Added " + name + " to the inventory.");
    }

    // 2. Check out a tool to an employee/assignee
    public void checkOutTool(String id, String assignee) {
        String formattedID = id.toUpperCase();
        // Find the tool using the repository's findById method
        var optionalTool = repository.findById(formattedID);

        if (optionalTool.isPresent()) {
            Tool tool = optionalTool.get();

            // Business Rule: Can we check it out?
            if (tool.getStatus() == ToolStatus.AVAILABLE) {
                tool.setStatus(ToolStatus.CHECKED_OUT);
                tool.setCheckedOutTo(assignee);

                // Save the updated state
                repository.save(tool);
                System.out.println("Success: " + tool.getName() + " checked out to " + assignee);
            } else {
                System.out.println("Error: Tool is currently " + tool.getStatus() + " and cannot be checked out.");
            }
        } else {
            System.out.println("Error: Tool with ID " + id + " not found.");
        }
    }

    // 3. Return a tool back to available status
    public void returnTool(String id) {
        String formattedID = id.toUpperCase();
        var optionalTool = repository.findById(formattedID);

        if (optionalTool.isPresent()) {
            Tool tool = optionalTool.get();

            tool.setStatus(ToolStatus.AVAILABLE);
            tool.setCheckedOutTo(null); // Clear the assignee

            repository.save(tool);
            System.out.println("Success: " + tool.getName() + " has been returned and is now AVAILABLE.");
        } else {
            System.out.println("Error: Tool with ID " + id + " not found.");
        }
    }

    // 4. Search for tools by category using Java Streams
    public List<Tool> findToolsByCategory(String category) {
        String formattedCat = category.toUpperCase();
        return repository.findAll().stream() // 1. Source: turn our list into a stream conveyor belt
                .filter(tool -> tool.getCategory().equalsIgnoreCase(formattedCat)) // 2. Intermediate: keep only tools matching the category
                .toList(); // 3. Terminal: collect the filtered results into a clean List
    }

    // 5. Find all tools currently checked out
    public List<Tool> findCheckedOutTools() {
        return repository.findAll().stream()
                .filter(tool -> tool.getStatus() == ToolStatus.CHECKED_OUT)
                .toList();
    }

}
