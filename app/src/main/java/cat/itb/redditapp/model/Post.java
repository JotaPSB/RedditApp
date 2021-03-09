package cat.itb.redditapp.model;

import java.util.List;

public class Post {
    private Community community;
    private String user;
    private String title;
    private String contentText;
    private int votes;
    private int numComments;
    private List<Comment> comments;


    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Post(Community community, String user, String title, String contentText, int votes, int numComments, List<Comment> comments) {
        this.community = community;
        this.user = user;
        this.title = title;
        this.contentText = contentText;
        this.votes = votes;
        this.numComments = numComments;
        this.comments = comments;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
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

    public int getNumComments() {
        return numComments;
    }

    public void setNumComments(int numComments) {
        this.numComments = numComments;
    }


}
