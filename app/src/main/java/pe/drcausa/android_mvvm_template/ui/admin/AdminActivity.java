package pe.drcausa.android_mvvm_template.ui.admin;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

import pe.drcausa.android_mvvm_template.R;
import pe.drcausa.android_mvvm_template.ui.menu.fragments.HomeFragment;
import pe.drcausa.android_mvvm_template.utils.ActivityUtils;

public class AdminActivity extends AppCompatActivity {
    private MaterialButton btnHome, btnUsers, btnPermissions, btnRoles;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_admin);

        btnHome = findViewById(R.id.btnHome);
        btnUsers = findViewById(R.id.btnUsers);
        btnPermissions = findViewById(R.id.btnPermissions);
        btnRoles = findViewById(R.id.btnRoles);

        btnHome.setOnClickListener(v -> handleBtnHome());
        btnUsers.setOnClickListener(v -> handleBtnUsers());
        btnPermissions.setOnClickListener(v -> handleBtnPermissions());
        btnRoles.setOnClickListener(v -> handleBtnRoles());

        ActivityUtils.switchFragment(this, new HomeFragment());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void handleBtnHome() {
    }

    private void handleBtnUsers() {
    }

    private void handleBtnPermissions() {
    }

    private void handleBtnRoles() {
    }
}
