package pe.drcausa.android_mvvm_template.ui.menu.fragments;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import pe.drcausa.android_mvvm_template.R;
import pe.drcausa.android_mvvm_template.utils.ActivityUtils;
import pe.drcausa.android_mvvm_template.viewmodel.UserViewModel;

public class MyProfileFragment extends Fragment {

    private MaterialButton btnReturn, btnManageAccount;
    private MaterialTextView txtFullName, txtUsername;
    private UserViewModel userViewModel;

    public MyProfileFragment() {
        super(R.layout.fragment_menu_my_profile);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnReturn = view.findViewById(R.id.btnReturn);
        btnManageAccount = view.findViewById(R.id.btnManageAccount);
        txtFullName = view.findViewById(R.id.txtFullName);
        txtUsername = view.findViewById(R.id.txtUsername);

        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);

        userViewModel.getCurrentUser().observe(getViewLifecycleOwner(), user -> {
            if (user != null) {
                String userFullName = user.getFirstName() + " " + user.getLastName();
                txtFullName.setText(userFullName);
                txtUsername.setText(user.getUsername());
            } else {
                txtFullName.setText("");
                txtUsername.setText("");
            }
        });

        btnReturn.setOnClickListener(v -> handleBtnReturn());
        btnManageAccount.setOnClickListener(v -> handleBtnManageAccount());
    }

    private void handleBtnReturn() { requireActivity().getOnBackPressedDispatcher().onBackPressed(); }

    private void handleBtnManageAccount() {
        ActivityUtils.switchFragment(
                (AppCompatActivity) requireActivity(),
                new ManageAccountFragment()
        );
    }
}
