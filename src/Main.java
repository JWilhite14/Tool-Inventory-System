import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 1. Initialize the storage repository and load any existing data from disk
        ToolRepository repository = new ToolRepository();
        repository.loadData();

        // 2. Inject the repository into our business logic service layer
        InventoryService service = new InventoryService(repository);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== Tool Inventory System Initialized ===");

        // 3. The CLI Loop
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> listAllTools(repository);
                case "2" -> addNewTool(service, scanner);
                case "3" -> checkOutTool(service, scanner);
                case "4" -> returnTool(service, scanner);
                case "5" -> searchByCategory(service, scanner);
                case "6" -> {
                    // Ensure final data save before shutting down
                    repository.saveData();
                    running = false;
                    System.out.println("Shutting down... Data saved successfully.");
                }
                default -> System.out.println("Invalid option. Please choose between 1 and 6.");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("[1] List All Tools");
        System.out.println("[2] Add New Tool");
        System.out.println("[3] Check Out Tool");
        System.out.println("[4] Return Tool");
        System.out.println("[5] Search by Category (Streams)");
        System.out.println("[6] Exit");
        System.out.print("Select an option: ");
    }

    private static void listAllTools(ToolRepository repository) {
        var tools = repository.findAll();
        if (tools.isEmpty()) {
            System.out.println("No tools currently in inventory.");
        } else {
            tools.forEach(System.out::println);
        }
    }

    private static void addNewTool(InventoryService service, Scanner scanner) {
        System.out.print("Enter Tool ID (e.g., T-001): ");
        String id = scanner.nextLine();
        System.out.print("Enter Tool Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Category: ");
        String category = scanner.nextLine();

        service.addTool(id, name, category);
    }

    private static void checkOutTool(InventoryService service, Scanner scanner) {
        System.out.print("Enter Tool ID to check out: ");
        String id = scanner.nextLine();
        System.out.print("Enter Assignee Name/ID: ");
        String assignee = scanner.nextLine();

        service.checkOutTool(id, assignee);
    }

    private static void returnTool(InventoryService service, Scanner scanner) {
        System.out.print("Enter Tool ID to return: ");
        String id = scanner.nextLine();

        service.returnTool(id);
    }

    private static void searchByCategory(InventoryService service, Scanner scanner) {
        System.out.print("Enter Category to search for: ");
        String category = scanner.nextLine();

        var results = service.findToolsByCategory(category);
        if (results.isEmpty()) {
            System.out.println("No tools found in category: " + category);
        } else {
            results.forEach(System.out::println);
        }
    }
}
