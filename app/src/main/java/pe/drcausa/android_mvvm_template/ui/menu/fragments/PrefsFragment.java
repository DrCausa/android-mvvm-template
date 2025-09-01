package pe.drcausa.android_mvvm_template.ui.menu.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

import pe.drcausa.android_mvvm_template.R;

public class PrefsFragment extends Fragment {

    public PrefsFragment() {
        super(R.layout.fragment_prefs);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MaterialButton btnReturn = view.findViewById(R.id.btnReturn);
        MaterialButton btnUpdateLanguage = view.findViewById(R.id.btnUpdateLanguage);
        MaterialButton btnUpdateTheme = view.findViewById(R.id.btnUpdateTheme);

        btnReturn.setOnClickListener(v -> handleReturn());
        btnUpdateLanguage.setOnClickListener(v -> handleUpdateLanguage());
        btnUpdateTheme.setOnClickListener(v -> handleUpdateTheme());
    }

    private void switchFragment(Fragment fragment) {
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    private void handleReturn() { switchFragment(new HomeFragment()); }

    private void handleUpdateLanguage() {
        Toast.makeText(requireContext(), "Update Language", Toast.LENGTH_SHORT).show();
    }

    private void handleUpdateTheme() {
        Toast.makeText(requireContext(), "Update Theme", Toast.LENGTH_SHORT).show();
    }
}
