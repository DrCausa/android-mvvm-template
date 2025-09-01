package pe.drcausa.android_mvvm_template.ui.menu.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

import pe.drcausa.android_mvvm_template.R;

public class MyProfileFragment extends Fragment {

    public MyProfileFragment() {
        super(R.layout.fragment_my_profile);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MaterialButton btnReturn = view.findViewById(R.id.btnReturn);
        MaterialButton btnManageAccount = view.findViewById(R.id.btnManageAccount);

        btnReturn.setOnClickListener(v -> handleReturn());
        btnManageAccount.setOnClickListener(v -> handleManageAccount());
    }

    private void handleReturn() {
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new HomeFragment())
                .addToBackStack(null)
                .commit();
    }

    private void handleManageAccount() {
        Toast.makeText(requireContext(), "Manage Account", Toast.LENGTH_SHORT).show();
    }
}
