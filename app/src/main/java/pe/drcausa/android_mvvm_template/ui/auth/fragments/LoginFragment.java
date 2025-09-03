package pe.drcausa.android_mvvm_template.ui.auth.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import pe.drcausa.android_mvvm_template.R;
import pe.drcausa.android_mvvm_template.ui.menu.MenuActivity;
import pe.drcausa.android_mvvm_template.utils.ActivityUtils;

public class LoginFragment extends Fragment {

    private TextInputEditText edtUsername, edtPassword;
    private MaterialButton btnLogin;
    private MaterialTextView txtGoToRegister, txtGoToRestorePassword;

    public LoginFragment() {
        super(R.layout.fragment_auth_login);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edtUsername = view.findViewById(R.id.edtUsername);
        edtPassword = view.findViewById(R.id.edtPassword);
        btnLogin = view.findViewById(R.id.btnLogin);
        txtGoToRegister = view.findViewById(R.id.txtGoToRegister);
        txtGoToRestorePassword = view.findViewById(R.id.txtGoToRestorePassword);

        btnLogin.setOnClickListener(v -> handleBtnLogin());
        txtGoToRegister.setOnClickListener(v -> handleTxtGoToRegister());
        txtGoToRestorePassword.setOnClickListener(v -> handleTxtGoToRestorePassword());
    }

    private void handleBtnLogin() {
        Intent intent = new Intent(requireActivity(), MenuActivity.class);
        startActivity(intent);
        requireActivity().finish();
    }

    private void handleTxtGoToRegister() {
        ActivityUtils.switchFragment(
                (AppCompatActivity) requireActivity(),
                new RegisterFragment()
        );
    }

    private void handleTxtGoToRestorePassword() {
    }
}
