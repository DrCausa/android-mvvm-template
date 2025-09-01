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
import pe.drcausa.android_mvvm_template.ui.menu.fragments.MyProfileFragment;
import pe.drcausa.android_mvvm_template.ui.menu.fragments.NewPostFragment;
import pe.drcausa.android_mvvm_template.ui.menu.fragments.SearchFragment;

public class MenuActivity extends AppCompatActivity {

    private MaterialButton btnHome, btnSearch, btnNewPost, btnMyProfile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_menu);

        btnHome = findViewById(R.id.btnHome);
        btnSearch = findViewById(R.id.btnSearch);
        btnNewPost = findViewById(R.id.btnNewPost);
        btnMyProfile = findViewById(R.id.btnMyProfile);

        btnHome.setOnClickListener(v -> handleHome());
        btnSearch.setOnClickListener(v -> handleSearch());
        btnNewPost.setOnClickListener(v -> handleNewPost());
        btnMyProfile.setOnClickListener(v -> handleMyProfile());

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
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new HomeFragment())
                .commit();
    }

    private void handleSearch() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new SearchFragment())
                .commit();
    }

    private void handleNewPost() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new NewPostFragment())
                .commit();
    }

    private void handleMyProfile() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new MyProfileFragment())
                .commit();
    }
}
