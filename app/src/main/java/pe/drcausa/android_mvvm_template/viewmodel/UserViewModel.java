package pe.drcausa.android_mvvm_template.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import pe.drcausa.android_mvvm_template.data.model.User;
import pe.drcausa.android_mvvm_template.data.repository.UserRepository;
import pe.drcausa.android_mvvm_template.utils.SessionManager;

public class UserViewModel extends AndroidViewModel {
    private final UserRepository userRepository;
    private final SessionManager sessionManager;

    private final MutableLiveData<User> currentUser = new MutableLiveData<>();
    private final MutableLiveData<String> loginError = new MutableLiveData<>();
    private final MutableLiveData<String> registerError = new MutableLiveData<>();
    private LiveData<List<User>> allUsers;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        sessionManager = new SessionManager(application);

        allUsers = userRepository.getAllUsersAsync();

        int savedUserId = sessionManager.getUserId();
        if (savedUserId != -1) {
            userRepository.getUserByIdAsync(savedUserId)
                    .observeForever(user -> {
                        if (user != null) { currentUser.postValue(user); }
                    });
        }
    }

    public void login(String username, String password) {
        userRepository.loginUserAsync(username, password)
                .observeForever(user -> {
                    if (user != null) {
                        currentUser.postValue(user);
                        sessionManager.startSession(user.getId());
                    } else {
                        loginError.postValue("Invalid credentials");
                    }
                });
    }

    public LiveData<User> getCurrentUser() { return currentUser; }
    public LiveData<String> getLoginError() { return loginError; }

    public void register(String username, String email, String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            registerError.postValue("The passwords do not match");
            return;
        }

        User newUser = new User(username, password, email, "", "");
        userRepository.insertUserAsync(newUser)
                .observeForever(id -> {
                    if (id != null && id > 0) {
                        newUser.setId(Math.toIntExact(id));
                        currentUser.postValue(newUser);
                        sessionManager.startSession(newUser.getId());
                    } else {
                        registerError.postValue("Error registering user");
                    }
                });
    }

    public LiveData<String> getRegisterError() { return registerError; }
    public LiveData<List<User>> getAllUsers() { return allUsers; }

    public void updateUser(User user) {
        userRepository.updateUserAsync(user);
        currentUser.postValue(user);
    }

    public void deleteUser(int userId) {
        userRepository.deleteUserAsync(userId)
                .observeForever(success -> {
                    if (success) {
                        currentUser.postValue(null);
                        sessionManager.endSession();
                    }
                });
    }

    public void logout() {
        currentUser.postValue(null);
        sessionManager.endSession();
    }
}
