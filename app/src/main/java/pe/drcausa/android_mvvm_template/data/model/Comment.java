package pe.drcausa.android_mvvm_template.data.model;

public class Comment {
    private int id;
    private String commentId;
    private long postId;
    private long userId;
    private String content;
    private String createdAt;
    private String updatedAt;

    public Comment(int id, String commentId, long postId, long userId, String content, String createdAt, String updatedAt) {
        this.id = id;
        this.commentId = commentId;
        this.postId = postId;
        this.userId = userId;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Comment(String commentId, long postId, long userId, String content) {
        this.commentId = commentId;
        this.postId = postId;
        this.userId = userId;
        this.content = content;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCommentId() { return commentId; }
    public void setCommentId(String commentId) { this.commentId = commentId; }

    public long getPostId() { return postId; }
    public void setPostId(long postId) { this.postId = postId; }

    public long getUserId() { return userId; }
    public void setUserId(long userId) { this.userId = userId; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}
