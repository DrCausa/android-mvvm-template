package pe.drcausa.android_mvvm_template.ui.auth.fragments;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import pe.drcausa.android_mvvm_template.R;

public class RegisterFragment extends Fragment {

    private TextInputEditText edtUsername, edtEmail, edtPassword, edtConfirmPassword;
    private MaterialButton btnRegister;
    private MaterialTextView txtGoToLogin;

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

        btnRegister.setOnClickListener(v -> handleBtnRegister());
        txtGoToLogin.setOnClickListener(v -> handleTxtGoToLogin());
    }

    private void handleBtnRegister() {
    }

    private void handleTxtGoToLogin() {
        requireActivity().getOnBackPressedDispatcher().onBackPressed();
    }
}
