package pe.drcausa.android_mvvm_template.ui.menu.fragments;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

import pe.drcausa.android_mvvm_template.R;
import pe.drcausa.android_mvvm_template.utils.ActivityUtils;

public class MyProfileFragment extends Fragment {

    public MyProfileFragment() {
        super(R.layout.fragment_my_profile);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MaterialButton btnReturn = view.findViewById(R.id.btnReturn);
        MaterialButton btnManageAccount = view.findViewById(R.id.btnManageAccount);

        btnReturn.setOnClickListener(v -> handleBtnReturn());
        btnManageAccount.setOnClickListener(v -> handleBtnManageAccount());
    }

    private void handleBtnReturn() { requireActivity().getOnBackPressedDispatcher().onBackPressed(); }

    private void handleBtnManageAccount() {
        ActivityUtils.switchFragment(
                (AppCompatActivity) requireActivity(),
                new PrefsFragment()
        );
    }
}
