package cat.itb.redditapp.model;

public class Post {
    private String community;
    private String user;
    private String title;
    private String contentText;
    private int votes;
    private int comments;
    private String profilePicture;

    public Post(String community, String user, String title, String contentText, int votes, int comments, String profilePicture) {
        this.community = community;
        this.user = user;
        this.title = title;
        this.contentText = contentText;
        this.votes = votes;
        this.comments = comments;
        this.profilePicture = profilePicture;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
