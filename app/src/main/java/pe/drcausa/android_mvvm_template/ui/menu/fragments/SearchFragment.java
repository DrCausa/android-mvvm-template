package pe.drcausa.android_mvvm_template.ui.menu.fragments;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

import pe.drcausa.android_mvvm_template.R;
import pe.drcausa.android_mvvm_template.ui.menu.MenuActivity;

public class SearchFragment extends Fragment {

    private final MenuActivity parentActivity;

    public SearchFragment(MenuActivity parentActivity) {
        super(R.layout.fragment_search);
        this.parentActivity = parentActivity;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MaterialButton btnReturn = view.findViewById(R.id.btnReturn);

        btnReturn.setOnClickListener(v -> handleBtnReturn());
    }

    private void handleBtnReturn() { parentActivity.getOnBackPressedDispatcher().onBackPressed(); }
}
