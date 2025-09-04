package pe.drcausa.android_mvvm_template.data.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import pe.drcausa.android_mvvm_template.data.db.dao.UserDao;
import pe.drcausa.android_mvvm_template.data.model.User;
import pe.drcausa.android_mvvm_template.utils.PasswordUtils;

public class UserRepository {
    private final UserDao userDao;
    private final Executor executor;
    private final MutableLiveData<List<User>> allUsers;

    public UserRepository(Context context) {
        this.userDao = new UserDao(context);
        this.executor = Executors.newSingleThreadExecutor();
        this.allUsers = new MutableLiveData<>();
        loadAllUsers();
    }

    private void loadAllUsers() {
        executor.execute(() -> {
            List<User> users = userDao.getAll();
            allUsers.postValue(users);
        });
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public LiveData<Long> insertUserAsync(User user) {
        MutableLiveData<Long> result = new MutableLiveData<>();
        executor.execute(() -> {
            long userId = userDao.insert(user);
            result.postValue(userId);
            loadAllUsers();
        });
        return result;
    }

    public LiveData<User> getUserByIdAsync(int id) {
        MutableLiveData<User> result = new MutableLiveData<>();
        executor.execute(() -> result.postValue(userDao.getById(id)));
        return result;
    }

    public LiveData<Boolean> updateUserAsync(User user) {
        MutableLiveData<Boolean> result = new MutableLiveData<>();
        executor.execute(() -> {
            boolean success = userDao.update(user) > 0;
            result.postValue(success);
            if (success) { loadAllUsers(); }
        });
        return result;
    }

    public LiveData<Boolean> deleteUserAsync(int userId) {
        MutableLiveData<Boolean> result = new MutableLiveData<>();
        executor.execute(() -> {
            boolean success = userDao.delete(userId) > 0;
            result.postValue(success);
            if (success) { loadAllUsers(); }
        });
        return result;
    }

    public LiveData<User> loginUserAsync(String username, String plainPassword) {
        MutableLiveData<User> result = new MutableLiveData<>();
        executor.execute(() -> {
            User user = userDao.getByUsername(username);
            if (user != null && PasswordUtils.verifyPassword(plainPassword, user.getPassword())) {
                result.postValue(user);
            } else {
                result.postValue(null);
            }
        });
        return result;
    }
}
