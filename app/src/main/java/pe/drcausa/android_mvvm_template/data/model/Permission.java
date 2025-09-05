package pe.drcausa.android_mvvm_template.data.model;

public class Permission {
    private int id;
    private String permissionId;
    private String name;
    private String description;

    public Permission(int id, String permissionId, String name, String description) {
        this.id = id;
        this.permissionId = permissionId;
        this.name = name;
        this.description = description;
    }

    public Permission(String permissionId, String name, String description) {
        this.permissionId = permissionId;
        this.name = name;
        this.description = description;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getPermissionId() { return permissionId; }
    public void setPermissionId(String permissionId) { this.permissionId = permissionId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
