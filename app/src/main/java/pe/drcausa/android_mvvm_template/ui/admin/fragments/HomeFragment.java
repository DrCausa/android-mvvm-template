package pe.drcausa.android_mvvm_template.ui.admin.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import pe.drcausa.android_mvvm_template.R;
import pe.drcausa.android_mvvm_template.ui.menu.MenuActivity;

public class HomeFragment extends Fragment {
    private MaterialButton btnMenu;
    private RecyclerView recyclerLogs;

    public HomeFragment() {
        super(R.layout.fragment_admin_home);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnMenu = view.findViewById(R.id.btnMenu);
        recyclerLogs = view.findViewById(R.id.recyclerLogs);

        recyclerLogs.setLayoutManager(new LinearLayoutManager(requireContext()));

        btnMenu.setOnClickListener(v -> handleBtnMenu());
    }

    private void handleBtnMenu() {
        Intent intent = new Intent(requireActivity(), MenuActivity.class);
        startActivity(intent);
        requireActivity().finish();
    }
}
