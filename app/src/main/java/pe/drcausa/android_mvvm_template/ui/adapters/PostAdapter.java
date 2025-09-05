package pe.drcausa.android_mvvm_template.ui.adapters;

import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import pe.drcausa.android_mvvm_template.R;
import pe.drcausa.android_mvvm_template.data.model.Post;
import pe.drcausa.android_mvvm_template.data.model.User;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    public interface OnPostActionListener {
        void onEdit(Post post);
        void onDelete(Post post);
    }

    private List<Post> posts = new ArrayList<>();
    private Map<Long, User> userMap;
    private final OnPostActionListener listener;

    public PostAdapter(OnPostActionListener listener, Map<Long, User> userMap) {
        this.listener = listener;
        this.userMap = userMap;
    }

    private String getTimeAgo(String dateTime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        try {
            Date date = simpleDateFormat.parse(dateTime);
            if (date != null) {
                long timeInMillis = date.getTime();
                return DateUtils.getRelativeTimeSpanString(
                        timeInMillis,
                        System.currentTimeMillis(),
                        DateUtils.SECOND_IN_MILLIS
                ).toString();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateTime;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_post, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post post = posts.get(position);
        User user = userMap.get(post.getUserId());

        holder.txtPostUser.setText(user != null ? user.getUsername() : "Unknown Author");
        holder.txtPostContent.setText(post.getContent());
        holder.txtPostDate.setText(getTimeAgo(post.getCreatedAt()));
        holder.txtPostAbout.setText(R.string.txt_test);

        holder.btnMore.setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(v.getContext(), holder.btnMore);
            MenuInflater inflater = popup.getMenuInflater();
            inflater.inflate(R.menu.menu_post_item, popup.getMenu());

            popup.setOnMenuItemClickListener(item -> {
                if (item.getItemId() == R.id.btnEditPost) {
                    listener.onEdit(post);
                    return true;
                } else if (item.getItemId() == R.id.btnDeletePost) {
                    listener.onDelete(post);
                    return true;
                }
                return false;
            });

            popup.show();
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
        notifyDataSetChanged();
    }

    public void setUserMap(Map<Long, User> userMap) {
        this.userMap = userMap;
        notifyDataSetChanged();
    }

    static class PostViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProfilePicture;
        MaterialTextView txtPostUser, txtPostAbout, txtPostContent, txtPostDate;
        MaterialButton btnMore;
        LinearLayout layoutPostUserRanks;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfilePicture = itemView.findViewById(R.id.ivProfilePicture);
            txtPostUser = itemView.findViewById(R.id.txtPostUser);
            txtPostAbout = itemView.findViewById(R.id.txtPostAbout);
            txtPostContent = itemView.findViewById(R.id.txtPostContent);
            txtPostDate = itemView.findViewById(R.id.txtPostDate);
            btnMore = itemView.findViewById(R.id.btnMore);
            layoutPostUserRanks = itemView.findViewById(R.id.layoutPostUserRanks);
        }
    }
}
