package pe.drcausa.android_mvvm_template.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.UUID;

import pe.drcausa.android_mvvm_template.data.model.User;
import pe.drcausa.android_mvvm_template.data.repository.UserRepository;
import pe.drcausa.android_mvvm_template.utils.PasswordUtils;
import pe.drcausa.android_mvvm_template.utils.SessionManager;

public class UserViewModel extends AndroidViewModel {
    private final UserRepository userRepository;
    private final SessionManager sessionManager;

    private final MediatorLiveData<User> currentUser = new MediatorLiveData<>();
    private final MutableLiveData<String> loginError = new MutableLiveData<>();
    private final MutableLiveData<String> registerError = new MutableLiveData<>();
    private final LiveData<List<User>> allUsers;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        sessionManager = new SessionManager(application);

        allUsers = userRepository.getAllUsers();

        int savedUserId = sessionManager.getUserId();
        if (savedUserId != -1) {
            LiveData<User> userSource = userRepository.getUserByIdAsync(savedUserId);
            currentUser.addSource(userSource, user -> {
                if (user != null) {
                    currentUser.setValue(user);
                }
                currentUser.removeSource(userSource);
            });
        }
    }

    public void login(String username, String password) {
        LiveData<User> userSource = userRepository.loginUserAsync(username, password);
        currentUser.addSource(userSource, user -> {
            if (user != null) {
                currentUser.setValue(user);
                sessionManager.startSession(user.getId());
            } else {
                loginError.setValue("Invalid credentials");
            }
            currentUser.removeSource(userSource);
        });
    }

    public void register(String username, String email, String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            registerError.postValue("The passwords do not match");
            return;
        }

        String hashedPassword = PasswordUtils.hashPassword(password);
        User newUser = new User(
                UUID.randomUUID().toString(),
                username,
                hashedPassword,
                email,
                null,
                null,
                null
        );

        LiveData<Long> insertSource = userRepository.insertUserAsync(newUser);
        currentUser.addSource(insertSource, id -> {
            if (id != null && id > 0) {
                newUser.setId(Math.toIntExact(id));
                currentUser.setValue(newUser);
                sessionManager.startSession(Math.toIntExact(id));
            } else {
                registerError.setValue("Error registering user");
            }
            currentUser.removeSource(insertSource);
        });
    }

    public void updateUser(User user) {
        LiveData<Boolean> updateSource = userRepository.updateUserAsync(user);
        currentUser.addSource(updateSource, success -> {
            if (Boolean.TRUE.equals(success)) {
                currentUser.setValue(user);
            }
            currentUser.removeSource(updateSource);
        });
    }

    public void deleteUser(int userId) {
        LiveData<Boolean> deleteSource = userRepository.deleteUserAsync(userId);
        currentUser.addSource(deleteSource, success -> {
            if (Boolean.TRUE.equals(success)) {
                currentUser.setValue(null);
                sessionManager.endSession();
            }
            currentUser.removeSource(deleteSource);
        });
    }

    public void logout() {
        currentUser.postValue(null);
        sessionManager.endSession();
    }

    public LiveData<User> getCurrentUser() { return currentUser; }
    public LiveData<String> getLoginError() { return loginError; }
    public LiveData<String> getRegisterError() { return registerError; }
    public LiveData<List<User>> getAllUsers() { return allUsers; }
}
