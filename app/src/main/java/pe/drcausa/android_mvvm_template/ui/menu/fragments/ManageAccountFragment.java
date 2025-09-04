package pe.drcausa.android_mvvm_template.ui.menu.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import pe.drcausa.android_mvvm_template.R;
import pe.drcausa.android_mvvm_template.ui.auth.AuthActivity;
import pe.drcausa.android_mvvm_template.viewmodel.UserViewModel;

public class ManageAccountFragment extends Fragment {
    private MaterialButton btnReturn, btnUpdateProfile, btnUpdatePassword, btnLogout, btnDeleteAccount;
    private TextInputEditText edtNewUsername, edtNewEmail, edtNewFirstName, edtNewLastName, edtCurrentPassword, edtNewPassword, edtConfirmNewPassword;
    private UserViewModel userViewModel;

    public ManageAccountFragment() {
        super(R.layout.fragment_manage_account);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnReturn = view.findViewById(R.id.btnReturn);
        btnUpdateProfile = view.findViewById(R.id.btnUpdateProfile);
        btnUpdatePassword = view.findViewById(R.id.btnUpdatePassword);
        btnLogout = view.findViewById(R.id.btnLogout);
        btnDeleteAccount = view.findViewById(R.id.btnDeleteAccount);
        edtNewUsername = view.findViewById(R.id.edtNewUsername);
        edtNewEmail = view.findViewById(R.id.edtNewEmail);
        edtNewFirstName = view.findViewById(R.id.edtNewFirstName);
        edtNewLastName = view.findViewById(R.id.edtNewLastName);
        edtCurrentPassword = view.findViewById(R.id.edtCurrentPassword);
        edtNewPassword = view.findViewById(R.id.edtNewPassword);
        edtConfirmNewPassword = view.findViewById(R.id.edtConfirmNewPassword);

        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);

        btnReturn.setOnClickListener(v -> handleBtnReturn());
        btnUpdateProfile.setOnClickListener(v -> handleBtnUpdateProfile());
        btnUpdatePassword.setOnClickListener(v -> handleBtnUpdatePassword());
        btnLogout.setOnClickListener(v -> handleBtnLogout());
        btnDeleteAccount.setOnClickListener(v -> handleBtnDeleteAccount());
    }

    private void handleBtnReturn() { requireActivity().getOnBackPressedDispatcher().onBackPressed(); }

    private void handleBtnUpdateProfile() {
        Toast.makeText(requireContext(), "Update Profile", Toast.LENGTH_SHORT).show();
    }

    private void handleBtnUpdatePassword() {
        Toast.makeText(requireContext(), "Update Password", Toast.LENGTH_SHORT).show();
    }

    private void handleBtnLogout() {
        userViewModel.logout();
        Intent intent = new Intent(requireContext(), AuthActivity.class);
        startActivity(intent);
        requireActivity().finish();
    }

    private void handleBtnDeleteAccount() {
        if (userViewModel.getCurrentUser().getValue() != null) {
            int userId = userViewModel.getCurrentUser().getValue().getId();
            userViewModel.deleteUser(userId);
            Toast.makeText(requireContext(), "Account deleted", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(requireContext(), AuthActivity.class);
            startActivity(intent);
            requireActivity().finish();
        } else {
            Toast.makeText(requireContext(), "No account to delete", Toast.LENGTH_SHORT).show();
        }
    }
}
