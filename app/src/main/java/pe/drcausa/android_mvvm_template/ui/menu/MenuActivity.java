package pe.drcausa.android_mvvm_template.ui.menu;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

import pe.drcausa.android_mvvm_template.R;

public class MenuActivity extends AppCompatActivity {

    private MaterialButton btnPrefs, btnHome, btnSearch, btnNewPost, btnMyAccount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_menu);

        btnPrefs = findViewById(R.id.btnPrefs);
        btnHome = findViewById(R.id.btnHome);
        btnSearch = findViewById(R.id.btnSearch);
        btnNewPost = findViewById(R.id.btnNewPost);
        btnMyAccount = findViewById(R.id.btnMyAccount);

        btnPrefs.setOnClickListener(v -> handlePrefs());
        btnHome.setOnClickListener(v -> handleHome());
        btnSearch.setOnClickListener(v -> handleSearch());
        btnNewPost.setOnClickListener(v -> handleNewPost());
        btnMyAccount.setOnClickListener(v -> handleMyAccount());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void handleHome() {
        Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
    }

    private void handleSearch() {
        Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
    }

    private void handleNewPost() {
        Toast.makeText(this, "New Post", Toast.LENGTH_SHORT).show();
    }

    private void handleMyAccount() {
        Toast.makeText(this, "My Account", Toast.LENGTH_SHORT).show();
    }

    private void handlePrefs() {
        Toast.makeText(this, "Prefs", Toast.LENGTH_SHORT).show();
    }
}
