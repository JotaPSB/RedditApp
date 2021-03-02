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

}
