public class Tool {
    private String id;
    private String name;
    private String category;
    private ToolStatus status;
    private String checkedOutTo;

    public Tool(String id, String name, String category){
        this.id = id;
        this.name = name;
        this.category = category;
        this.status = ToolStatus.AVAILABLE;
        this.checkedOutTo = null;
    }

    public String getName() {
        return name;
    }
    public String getCategory() {
        return category;
    }

    public ToolStatus getStatus() {
        return status;
    }
    public void setStatus(ToolStatus staus) {
        this.status = staus;
    }
    public String getCheckedOutTo() {
        return checkedOutTo;
    }
    public void setCheckedOutTo(String checkedOutTo) {
        this.checkedOutTo = checkedOutTo;
    }
    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (%s) - Status: %s %s",
                id, name, category, status,
                (checkedOutTo != null ? "[Out to: " + checkedOutTo + "]" : ""));
    }
}
