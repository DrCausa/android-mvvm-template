package pe.drcausa.android_mvvm_template.ui.menu;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.button.MaterialButton;

import pe.drcausa.android_mvvm_template.R;
import pe.drcausa.android_mvvm_template.ui.menu.fragments.HomeFragment;
import pe.drcausa.android_mvvm_template.ui.menu.fragments.ManageAccountFragment;
import pe.drcausa.android_mvvm_template.ui.menu.fragments.MyProfileFragment;
import pe.drcausa.android_mvvm_template.ui.menu.fragments.NewPostFragment;
import pe.drcausa.android_mvvm_template.ui.menu.fragments.PrefsFragment;
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

        btnHome.setOnClickListener(v -> handleBtnHome());
        btnSearch.setOnClickListener(v -> handleBtnSearch());
        btnNewPost.setOnClickListener(v -> handleBtnNewPost());
        btnMyProfile.setOnClickListener(v -> handleBtnMyProfile());

        switchFragment(new HomeFragment(this));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                FragmentManager fm = getSupportFragmentManager();

                if (fm.getBackStackEntryCount() > 1) {
                    fm.popBackStack();
                } else {
                    finish();
                }
            }
        });

        getSupportFragmentManager().registerFragmentLifecycleCallbacks(new FragmentManager.FragmentLifecycleCallbacks() {
            @Override
            public void onFragmentResumed(@NonNull FragmentManager fm, @NonNull Fragment f) {
                super.onFragmentResumed(fm, f);
                updateBottomIcons(f);
            }
        }, true);
    }

    private void handleBtnHome() {
        switchFragment(new HomeFragment(this));
    }

    private void handleBtnSearch() {
        switchFragment(new SearchFragment(this));
    }

    private void handleBtnNewPost() {
        switchFragment(new NewPostFragment(this));
    }

    private void handleBtnMyProfile() {
        switchFragment(new MyProfileFragment(this));
    }

    public void switchFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    private void updateBottomIcons(Fragment fragment) {
        inactivateBottomIcons();

        if (fragment instanceof HomeFragment || fragment instanceof PrefsFragment) {
            btnHome.setIconResource(R.drawable.home_fill);
        } else if (fragment instanceof SearchFragment) {
            btnSearch.setIconResource(R.drawable.search);
        } else if (fragment instanceof NewPostFragment) {
            btnNewPost.setIconResource(R.drawable.add_notes_fill);
        } else if (fragment instanceof MyProfileFragment || fragment instanceof ManageAccountFragment) {
            btnMyProfile.setIconResource(R.drawable.account_circle_fill);
        }
    }

    private void inactivateBottomIcons() {
        btnHome.setIconResource(R.drawable.home);
        btnSearch.setIconResource(R.drawable.search);
        btnNewPost.setIconResource(R.drawable.add_notes);
        btnMyProfile.setIconResource(R.drawable.account_circle);
    }
}
