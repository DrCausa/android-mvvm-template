package pe.drcausa.android_mvvm_template.data.model;

public class Role {
    private int id;
    private String roleId;
    private String name;
    private String description;
    private String imageUrl;

    public Role(int id, String roleId, String name, String description, String imageUrl) {
        this.id = id;
        this.roleId = roleId;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public Role(String roleId, String name, String description, String imageUrl) {
        this.roleId = roleId;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getRoleId() { return roleId; }
    public void setRoleId(String roleId) { this.roleId = roleId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
