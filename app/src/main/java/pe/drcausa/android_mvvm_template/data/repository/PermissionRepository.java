package pe.drcausa.android_mvvm_template.data.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import pe.drcausa.android_mvvm_template.data.db.dao.PermissionDao;
import pe.drcausa.android_mvvm_template.data.model.Permission;

public class PermissionRepository {
    private final PermissionDao permissionDao;
    private final Executor executor;
    private final MutableLiveData<List<Permission>> allPermissions;

    public PermissionRepository(Context context) {
        this.permissionDao = new PermissionDao(context);
        this.executor = Executors.newSingleThreadExecutor();
        this.allPermissions = new MutableLiveData<>();
        loadAllPermissions();
    }

    private void loadAllPermissions() {
        executor.execute(() -> {
            List<Permission> permissions = permissionDao.getAll();
            allPermissions.postValue(permissions);
        });
    }

    public MutableLiveData<List<Permission>> getAllPermissions() {
        return allPermissions;
    }

    public LiveData<Long> insertPermissionAsync(Permission permission) {
        MutableLiveData<Long> result = new MutableLiveData<>();
        executor.execute(() -> {
            long permissionId = permissionDao.insert(permission);
            result.postValue(permissionId);
            loadAllPermissions();
        });
        return result;
    }

    public LiveData<Permission> getPermissionByIdAsync(int id) {
        MutableLiveData<Permission> result = new MutableLiveData<>();
        executor.execute(() -> result.postValue(permissionDao.getById(id)));
        return result;
    }

    public LiveData<Boolean> updatePermissionAsync(Permission permission) {
        MutableLiveData<Boolean> result = new MutableLiveData<>();
        executor.execute(() -> {
            boolean success = permissionDao.update(permission) > 0;
            result.postValue(success);
            if (success) { loadAllPermissions(); }
        });
        return result;
    }

    public LiveData<Boolean> deletePermissionAsync(int permissionId) {
        MutableLiveData<Boolean> result = new MutableLiveData<>();
        executor.execute(() -> {
            boolean success = permissionDao.delete(permissionId) > 0;
            result.postValue(success);
            if (success) { loadAllPermissions(); }
        });
        return result;
    }
}
