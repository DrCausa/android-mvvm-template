package pe.drcausa.android_mvvm_template.data.model;

public class RolePermission {
    private int id;
    private long roleId;
    private long permissionId;

    public RolePermission(int id, long roleId, long permissionId) {
        this.id = id;
        this.roleId = roleId;
        this.permissionId = permissionId;
    }

    public RolePermission(long roleId, long permissionId) {
        this.roleId = roleId;
        this.permissionId = permissionId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public long getRoleId() { return roleId; }
    public void setRoleId(long roleId) { this.roleId = roleId; }

    public long getPermissionId() { return permissionId; }
    public void setPermissionId(long permissionId) { this.permissionId = permissionId; }
}
