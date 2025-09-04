package pe.drcausa.android_mvvm_template.ui.auth.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import pe.drcausa.android_mvvm_template.R;
import pe.drcausa.android_mvvm_template.viewmodel.UserViewModel;

public class RegisterFragment extends Fragment {

    private TextInputEditText edtUsername, edtEmail, edtPassword, edtConfirmPassword;
    private MaterialButton btnRegister;
    private MaterialTextView txtGoToLogin;
    private UserViewModel userViewModel;

    public RegisterFragment() {
        super(R.layout.fragment_auth_register);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edtUsername = view.findViewById(R.id.edtUsername);
        edtEmail = view.findViewById(R.id.edtEmail);
        edtPassword = view.findViewById(R.id.edtPassword);
        edtConfirmPassword = view.findViewById(R.id.edtConfirmPassword);
        btnRegister = view.findViewById(R.id.btnRegister);
        txtGoToLogin = view.findViewById(R.id.txtGoToLogin);

        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);

        userViewModel.getRegisterError().observe(getViewLifecycleOwner(), error -> {
            if (error != null) {
                Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show();
            }
        });

        userViewModel.getCurrentUser().observe(getViewLifecycleOwner(), user -> {
            if (user != null) {
                Toast.makeText(requireContext(), "Registered successfully", Toast.LENGTH_SHORT).show();
                requireActivity().getOnBackPressedDispatcher().onBackPressed();
            }
        });

        btnRegister.setOnClickListener(v -> handleBtnRegister());
        txtGoToLogin.setOnClickListener(v -> handleTxtGoToLogin());
    }

    private void handleBtnRegister() {
        String username = edtUsername.getText().toString();
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();
        String confirmPassword = edtConfirmPassword.getText().toString();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        userViewModel.register(username, email, password, confirmPassword);
    }

    private void handleTxtGoToLogin() {
        requireActivity().getOnBackPressedDispatcher().onBackPressed();
    }
}
