package pe.drcausa.android_mvvm_template.ui.menu.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

import pe.drcausa.android_mvvm_template.R;
import pe.drcausa.android_mvvm_template.ui.menu.MenuActivity;

public class PrefsFragment extends Fragment {

    private final MenuActivity parentActivity;

    public PrefsFragment(MenuActivity parentActivity) {
        super(R.layout.fragment_prefs);
        this.parentActivity = parentActivity;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MaterialButton btnReturn = view.findViewById(R.id.btnReturn);
        MaterialButton btnUpdateLanguage = view.findViewById(R.id.btnUpdateLanguage);
        MaterialButton btnUpdateTheme = view.findViewById(R.id.btnUpdateTheme);

        btnReturn.setOnClickListener(v -> handleBtnReturn());
        btnUpdateLanguage.setOnClickListener(v -> handleBtnUpdateLanguage());
        btnUpdateTheme.setOnClickListener(v -> handleBtnUpdateTheme());
    }

    private void handleBtnReturn() { parentActivity.getOnBackPressedDispatcher().onBackPressed(); }

    private void handleBtnUpdateLanguage() {
        Toast.makeText(requireContext(), "Update Language", Toast.LENGTH_SHORT).show();
    }

    private void handleBtnUpdateTheme() {
        Toast.makeText(requireContext(), "Update Theme", Toast.LENGTH_SHORT).show();
    }
}
