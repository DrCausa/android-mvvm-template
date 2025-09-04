package pe.drcausa.android_mvvm_template.data.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import pe.drcausa.android_mvvm_template.data.db.dao.UserDao;
import pe.drcausa.android_mvvm_template.data.model.User;

public class UserRepository {
    private final UserDao userDao;
    private final Executor executor;

    public UserRepository(Context context) {
        this.userDao = new UserDao(context);
        this.executor = Executors.newSingleThreadExecutor();
    }

    public LiveData<Long> insertUserAsync(User user) {
        MutableLiveData<Long> result = new MutableLiveData<>();
        executor.execute(() -> result.postValue(userDao.insert(user)));
        return result;
    }

    public LiveData<User> getUserByIdAsync(int id) {
        MutableLiveData<User> result = new MutableLiveData<>();
        executor.execute(() -> result.postValue(userDao.getById(id)));
        return result;
    }

    public LiveData<User> getUserByCredentialsAsync(String username, String password) {
        MutableLiveData<User> result = new MutableLiveData<>();
        executor.execute(() -> result.postValue(userDao.getByCredentials(username, password)));
        return result;
    }

    public LiveData<List<User>> getAllUsersAsync() {
        MutableLiveData<List<User>> result = new MutableLiveData<>();
        executor.execute(() -> result.postValue(userDao.getAll()));
        return result;
    }

    public LiveData<Boolean> updateUserAsync(User user) {
        MutableLiveData<Boolean> result = new MutableLiveData<>();
        executor.execute(() -> result.postValue(userDao.update(user) > 0));
        return result;
    }

    public LiveData<Boolean> deleteUserAsync(int userId) {
        MutableLiveData<Boolean> result = new MutableLiveData<>();
        executor.execute(() -> result.postValue(userDao.delete(userId) > 0));
        return result;
    }
}
