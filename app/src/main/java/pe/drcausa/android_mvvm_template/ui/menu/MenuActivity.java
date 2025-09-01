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
import pe.drcausa.android_mvvm_template.ui.menu.fragments.HomeFragment;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_menu);

        MaterialButton btnHome = findViewById(R.id.btnHome);
        MaterialButton btnSearch = findViewById(R.id.btnSearch);
        MaterialButton btnNewPost = findViewById(R.id.btnNewPost);
        MaterialButton btnMyAccount = findViewById(R.id.btnMyAccount);

        btnHome.setOnClickListener(v -> handleHome());
        btnSearch.setOnClickListener(v -> handleSearch());
        btnNewPost.setOnClickListener(v -> handleNewPost());
        btnMyAccount.setOnClickListener(v -> handleMyAccount());

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new HomeFragment())
                .commit();

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
}
