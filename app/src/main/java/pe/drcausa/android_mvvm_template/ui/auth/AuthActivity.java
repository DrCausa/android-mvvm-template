package pe.drcausa.android_mvvm_template.ui.auth;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

import pe.drcausa.android_mvvm_template.R;
import pe.drcausa.android_mvvm_template.ui.auth.fragments.LoginFragment;
import pe.drcausa.android_mvvm_template.utils.ActivityUtils;

public class AuthActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_auth);

        ActivityUtils.switchFragment(this, new LoginFragment());

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
    }
}
