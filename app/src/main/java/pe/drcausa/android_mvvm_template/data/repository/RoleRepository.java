package pe.drcausa.android_mvvm_template.data.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import pe.drcausa.android_mvvm_template.data.db.dao.RoleDao;
import pe.drcausa.android_mvvm_template.data.model.Role;

public class RoleRepository {
    private final RoleDao roleDao;
    private final Executor executor;
    private final MutableLiveData<List<Role>> allRoles;

    public RoleRepository(Context context) {
        this.roleDao = new RoleDao(context);
        this.executor = Executors.newSingleThreadExecutor();
        this.allRoles = new MutableLiveData<>();
        loadAllRoles();
    }

    private void loadAllRoles() {
        executor.execute(() -> {
            List<Role> roles = roleDao.getAll();
            allRoles.postValue(roles);
        });
    }

    public LiveData<List<Role>> getAllRoles() {
        return allRoles;
    }

    public LiveData<Long> insertRoleAsync(Role role) {
        MutableLiveData<Long> result = new MutableLiveData<>();
        executor.execute(() -> {
            long id = roleDao.insert(role);
            result.postValue(id);
            loadAllRoles();
        });
        return result;
    }

    public LiveData<Role> getRoleByIdAsync(long id) {
        MutableLiveData<Role> result = new MutableLiveData<>();
        executor.execute(() -> result.postValue(roleDao.getById(id)));
        return result;
    }

    public LiveData<Boolean> updateRoleAsync(Role role) {
        MutableLiveData<Boolean> result = new MutableLiveData<>();
        executor.execute(() -> {
            boolean success = roleDao.update(role) > 0;
            result.postValue(success);
            if (success) { loadAllRoles(); }
        });
        return result;
    }

    public LiveData<Boolean> deleteRoleAsync(long roleId) {
        MutableLiveData<Boolean> result = new MutableLiveData<>();
        executor.execute(() -> {
            boolean success = roleDao.delete(roleId) > 0;
            result.postValue(success);
            if (success) { loadAllRoles(); }
        });
        return result;
    }
}
