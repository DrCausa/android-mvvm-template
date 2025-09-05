package pe.drcausa.android_mvvm_template.ui.menu.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.HashMap;
import java.util.Map;

import pe.drcausa.android_mvvm_template.R;
import pe.drcausa.android_mvvm_template.data.model.Post;
import pe.drcausa.android_mvvm_template.data.model.User;
import pe.drcausa.android_mvvm_template.ui.adapters.PostAdapter;
import pe.drcausa.android_mvvm_template.ui.admin.AdminActivity;
import pe.drcausa.android_mvvm_template.utils.ActivityUtils;
import pe.drcausa.android_mvvm_template.viewmodel.PostViewModel;
import pe.drcausa.android_mvvm_template.viewmodel.UserViewModel;

public class HomeFragment extends Fragment {

    private MaterialButton btnAdmin, btnPrefs;
    private RecyclerView recyclerPosts;
    private PostAdapter postAdapter;
    private PostViewModel postViewModel;
    private UserViewModel userViewModel;
    private Map<Long, User> userMap = new HashMap<>();

    public HomeFragment() {
        super(R.layout.fragment_menu_home);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnAdmin = view.findViewById(R.id.btnAdmin);
        btnPrefs = view.findViewById(R.id.btnPrefs);
        recyclerPosts = view.findViewById(R.id.recyclerPosts);

        recyclerPosts.setLayoutManager(new LinearLayoutManager(requireContext()));

        postAdapter = new PostAdapter(new PostAdapter.OnPostActionListener() {
            @Override
            public void onEdit(Post post) {
                postViewModel.setSelectedPost(post);
                ActivityUtils.switchFragment(
                        (AppCompatActivity) requireActivity(),
                        new EditPostFragment()
                );
            }

            @Override
            public void onDelete(Post post) {
                postViewModel.deletePost(post.getId()).observe(getViewLifecycleOwner(), success -> {
                    if (Boolean.TRUE.equals(success)) {
                        Toast.makeText(requireContext(), "Post deleted", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(requireContext(), "Error deleting post", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }, userMap);

        recyclerPosts.setAdapter(postAdapter);

        postViewModel = new ViewModelProvider(requireActivity()).get(PostViewModel.class);
        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);

        userViewModel.getAllUsers().observe(getViewLifecycleOwner(), users -> {
            if (users != null) {
                userMap.clear();
                for (User user : users) {
                    userMap.put((long) user.getId(), user);
                }
                postAdapter.setUserMap(userMap);
            }
        });

        postViewModel.getAllPosts().observe(getViewLifecycleOwner(), posts -> {
            if (posts != null) {
                postAdapter.setPosts(posts);
            }
        });

        btnAdmin.setOnClickListener(v -> handleBtnAdmin());
        btnPrefs.setOnClickListener(v -> handleBtnPrefs());
    }

    private void handleBtnAdmin() {
        Intent intent = new Intent(requireActivity(), AdminActivity.class);
        startActivity(intent);
        requireActivity().finish();
    }

    private void handleBtnPrefs() {
        ActivityUtils.switchFragment(
                (AppCompatActivity) requireActivity(),
                new PrefsFragment()
        );
    }
}
