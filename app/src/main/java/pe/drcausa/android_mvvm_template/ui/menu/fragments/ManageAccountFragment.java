package pe.drcausa.android_mvvm_template.ui.menu.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

import pe.drcausa.android_mvvm_template.R;
import pe.drcausa.android_mvvm_template.ui.login.LoginActivity;

public class ManageAccountFragment extends Fragment {

    public ManageAccountFragment() {
        super(R.layout.fragment_manage_account);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MaterialButton btnReturn = view.findViewById(R.id.btnReturn);
        MaterialButton btnUpdateProfile = view.findViewById(R.id.btnUpdateProfile);
        MaterialButton btnUpdatePassword = view.findViewById(R.id.btnUpdatePassword);
        MaterialButton btnLogout = view.findViewById(R.id.btnLogout);
        MaterialButton btnDeleteAccount = view.findViewById(R.id.btnDeleteAccount);

        btnReturn.setOnClickListener(v -> handleReturn());
        btnUpdateProfile.setOnClickListener(v -> handleUpdateProfile());
        btnUpdatePassword.setOnClickListener(v -> handleUpdatePassword());
        btnLogout.setOnClickListener(v -> handleLogout());
        btnDeleteAccount.setOnClickListener(v -> handleDeleteAccount());
    }

    private void switchFragment(Fragment fragment) {
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    private void handleReturn() { switchFragment(new MyProfileFragment()); }

    private void handleUpdateProfile() {
        Toast.makeText(requireContext(), "Update Profile", Toast.LENGTH_SHORT).show();
    }

    private void handleUpdatePassword() {
        Toast.makeText(requireContext(), "Update Password", Toast.LENGTH_SHORT).show();
    }

    private void handleLogout() {
        requireActivity().startActivity(new Intent(requireContext(), LoginActivity.class));
        requireActivity().finish();
    }

    private void handleDeleteAccount() {
        Toast.makeText(requireContext(), "Delete Account", Toast.LENGTH_SHORT).show();
    }
}
