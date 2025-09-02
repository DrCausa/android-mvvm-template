package pe.drcausa.android_mvvm_template.ui.menu.fragments;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

import pe.drcausa.android_mvvm_template.R;
import pe.drcausa.android_mvvm_template.ui.menu.MenuActivity;

public class HomeFragment extends Fragment {

    private final MenuActivity parentActivity;

    public HomeFragment(MenuActivity parentActivity) {
        super(R.layout.fragment_home);
        this.parentActivity = parentActivity;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MaterialButton btnPrefs = view.findViewById(R.id.btnPrefs);

        btnPrefs.setOnClickListener(v -> handleBtnPrefs());
    }

    private void handleBtnPrefs() { parentActivity.switchFragment(new PrefsFragment(parentActivity)); }
}
