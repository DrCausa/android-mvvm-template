package pe.drcausa.android_mvvm_template.ui.register;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import pe.drcausa.android_mvvm_template.R;

public class RegisterActivity extends AppCompatActivity {
    private TextInputEditText edtUsername, edtEmail, edtPassword, edtConfirmPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);

        edtUsername = findViewById(R.id.edtUsername);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        MaterialButton btnRegister = findViewById(R.id.btnRegister);
        MaterialTextView txtLogin = findViewById(R.id.txtLogin);

        btnRegister.setOnClickListener(v -> handleRegister());
        txtLogin.setOnClickListener(v -> handleLogin());
    }

    private void handleRegister() {
    }

    private void handleLogin() {
        finish();
    }

    private void clearFields() {
        edtUsername.setText("");
        edtEmail.setText("");
        edtPassword.setText("");
        edtConfirmPassword.setText("");
    }
}
