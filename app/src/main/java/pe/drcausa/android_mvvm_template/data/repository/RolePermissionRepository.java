package pe.drcausa.android_mvvm_template.data.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import pe.drcausa.android_mvvm_template.data.db.dao.RolePermissionDao;
import pe.drcausa.android_mvvm_template.data.model.RolePermission;

public class RolePermissionRepository {
    private final RolePermissionDao rolePermissionDao;
    private final Executor executor;
    private final MutableLiveData<List<RolePermission>> allRolePermissions;

    public RolePermissionRepository(Context context) {
        this.rolePermissionDao = new RolePermissionDao(context);
        this.executor = Executors.newSingleThreadExecutor();
        this.allRolePermissions = new MutableLiveData<>();
        loadAllRolePermissions();
    }

    private void loadAllRolePermissions() {
        executor.execute(() -> {
            List<RolePermission> rolePermissions = rolePermissionDao.getAll();
            allRolePermissions.postValue(rolePermissions);
        });
    }

    public LiveData<List<RolePermission>> getAllRolePermissions() {
        return allRolePermissions;
    }

    public LiveData<Long> insertRolePermissionAsync(RolePermission rolePermission) {
        MutableLiveData<Long> result = new MutableLiveData<>();
        executor.execute(() -> {
            long id = rolePermissionDao.insert(rolePermission);
            result.postValue(id);
            loadAllRolePermissions();
        });
        return result;
    }

    public LiveData<RolePermission> getRolePermissionByIdAsync(int id) {
        MutableLiveData<RolePermission> result = new MutableLiveData<>();
        executor.execute(() -> result.postValue(rolePermissionDao.getById(id)));
        return result;
    }

    public LiveData<List<RolePermission>> getAllRolePermissionsByRoleIdAsync(long roleId) {
        MutableLiveData<List<RolePermission>> result = new MutableLiveData<>();
        executor.execute(() -> result.postValue(rolePermissionDao.getAllByRoleId(roleId)));
        return result;
    }

    public LiveData<List<RolePermission>> getAllRolePermissionsByPermissionIdAsync(long permissionId) {
        MutableLiveData<List<RolePermission>> result = new MutableLiveData<>();
        executor.execute(() -> result.postValue(rolePermissionDao.getAllByPermissionId(permissionId)));
        return result;
    }

    public LiveData<Boolean> updateRolePermissionAsync(RolePermission rolePermission) {
        MutableLiveData<Boolean> result = new MutableLiveData<>();
        executor.execute(() -> {
            boolean success = rolePermissionDao.update(rolePermission) > 0;
            result.postValue(success);
            if (success) { loadAllRolePermissions(); }
        });
        return result;
    }

    public LiveData<Boolean> deleteRolePermissionAsync(int rolePermissionId) {
        MutableLiveData<Boolean> result = new MutableLiveData<>();
        executor.execute(() -> {
            boolean success = rolePermissionDao.delete(rolePermissionId) > 0;
            result.postValue(success);
            if (success) { loadAllRolePermissions(); }
        });
        return result;
    }
}
