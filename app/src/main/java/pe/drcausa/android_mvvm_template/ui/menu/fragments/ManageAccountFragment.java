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
import pe.drcausa.android_mvvm_template.ui.menu.MenuActivity;

public class ManageAccountFragment extends Fragment {

    private final MenuActivity parentActivity;

    public ManageAccountFragment(MenuActivity parentActivity) {
        super(R.layout.fragment_manage_account);
        this.parentActivity = parentActivity;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MaterialButton btnReturn = view.findViewById(R.id.btnReturn);
        MaterialButton btnUpdateProfile = view.findViewById(R.id.btnUpdateProfile);
        MaterialButton btnUpdatePassword = view.findViewById(R.id.btnUpdatePassword);
        MaterialButton btnLogout = view.findViewById(R.id.btnLogout);
        MaterialButton btnDeleteAccount = view.findViewById(R.id.btnDeleteAccount);

        btnReturn.setOnClickListener(v -> handleBtnReturn());
        btnUpdateProfile.setOnClickListener(v -> handleBtnUpdateProfile());
        btnUpdatePassword.setOnClickListener(v -> handleBtnUpdatePassword());
        btnLogout.setOnClickListener(v -> handleBtnLogout());
        btnDeleteAccount.setOnClickListener(v -> handleBtnDeleteAccount());
    }

    private void handleBtnReturn() { parentActivity.switchFragment(new MyProfileFragment(parentActivity)); }

    private void handleBtnUpdateProfile() {
        Toast.makeText(requireContext(), "Update Profile", Toast.LENGTH_SHORT).show();
    }

    private void handleBtnUpdatePassword() {
        Toast.makeText(requireContext(), "Update Password", Toast.LENGTH_SHORT).show();
    }

    private void handleBtnLogout() {
        Intent intent = new Intent(requireContext(), LoginActivity.class);
        startActivity(intent);
        parentActivity.finish();
    }

    private void handleBtnDeleteAccount() {
        Toast.makeText(requireContext(), "Delete Account", Toast.LENGTH_SHORT).show();
    }
}
