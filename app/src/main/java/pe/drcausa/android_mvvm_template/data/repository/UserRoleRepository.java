package pe.drcausa.android_mvvm_template.data.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import pe.drcausa.android_mvvm_template.data.db.dao.UserRoleDao;
import pe.drcausa.android_mvvm_template.data.model.UserRole;

public class UserRoleRepository {
    private final UserRoleDao userRoleDao;
    private final Executor executor;
    private final MutableLiveData<List<UserRole>> allUserRoles;

    public UserRoleRepository(Context context) {
        this.userRoleDao = new UserRoleDao(context);
        this.executor = Executors.newSingleThreadExecutor();
        this.allUserRoles = new MutableLiveData<>();
        loadAllUserRoles();
    }

    private void loadAllUserRoles() {
        executor.execute(() -> {
            List<UserRole> userRoles = userRoleDao.getAll();
            allUserRoles.postValue(userRoles);
        });
    }

    public LiveData<List<UserRole>> getAllUserRoles() {
        return allUserRoles;
    }

    public LiveData<Long> insertUserRoleAsync(UserRole userRole) {
        MutableLiveData<Long> result = new MutableLiveData<>();
        executor.execute(() -> {
            long id = userRoleDao.insert(userRole);
            result.postValue(id);
            loadAllUserRoles();
        });
        return result;
    }

    public LiveData<UserRole> getUserRoleByIdAsync(int id) {
        MutableLiveData<UserRole> result = new MutableLiveData<>();
        executor.execute(() -> result.postValue(userRoleDao.getById(id)));
        return result;
    }

    public LiveData<List<UserRole>> getAllUserRolesByUserIdAsync(long userId) {
        MutableLiveData<List<UserRole>> result = new MutableLiveData<>();
        executor.execute(() -> result.postValue(userRoleDao.getAllByUserId(userId)));
        return result;
    }

    public LiveData<Boolean> deleteUserRoleAsync(int userRoleId) {
        MutableLiveData<Boolean> result = new MutableLiveData<>();
        executor.execute(() -> {
            boolean success = userRoleDao.delete(userRoleId) > 0;
            result.postValue(success);
            if (success) { loadAllUserRoles(); }
        });
        return result;
    }
}
