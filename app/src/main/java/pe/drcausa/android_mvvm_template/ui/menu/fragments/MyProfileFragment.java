package pe.drcausa.android_mvvm_template.ui.menu.fragments;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.HashMap;
import java.util.Map;

import pe.drcausa.android_mvvm_template.R;
import pe.drcausa.android_mvvm_template.data.model.User;
import pe.drcausa.android_mvvm_template.ui.adapters.PostAdapter;
import pe.drcausa.android_mvvm_template.utils.ActivityUtils;
import pe.drcausa.android_mvvm_template.viewmodel.PostViewModel;
import pe.drcausa.android_mvvm_template.viewmodel.UserViewModel;

public class MyProfileFragment extends Fragment {

    private MaterialButton btnReturn, btnManageAccount;
    private MaterialTextView txtFullName, txtUsername;
    private RecyclerView recyclerUserPosts;

    private UserViewModel userViewModel;
    private PostViewModel postViewModel;
    private PostAdapter postAdapter;
    private final Map<Long, User> userMap = new HashMap<>();

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
        recyclerUserPosts = view.findViewById(R.id.recyclerUserPosts);

        recyclerUserPosts.setLayoutManager(new LinearLayoutManager(requireContext()));

        postAdapter = new PostAdapter(null, userMap);
        recyclerUserPosts.setAdapter(postAdapter);

        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
        postViewModel = new ViewModelProvider(requireActivity()).get(PostViewModel.class);

        userViewModel.getCurrentUser().observe(getViewLifecycleOwner(), user -> {
            if (user != null) {
                String fullName = user.getFirstName() + " " + user.getLastName();
                txtFullName.setText(fullName);
                txtUsername.setText(user.getUsername());

                userMap.clear();
                userMap.put((long) user.getId(), user);
                postAdapter.setUserMap(userMap);

                postViewModel.getPostsByUserId(user.getId())
                        .observe(getViewLifecycleOwner(), posts -> {
                            if (posts != null) { postAdapter.setPosts(posts); }
                        });
            } else {
                txtFullName.setText("");
                txtUsername.setText("");
                postAdapter.setPosts(null);
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
