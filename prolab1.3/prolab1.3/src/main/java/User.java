import java.util.LinkedList;
import java.util.List;

public class User {
    private String username;
    private String fullName;
    private int followersCount;
    private int followingCount;
    private String language;
    private String region;
    private List<String> interests;
    private List<String> tweets;
    private List<String> followingUsers;
    private List<String> followers;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
    }

    public int getFollowingCount() {
        return followingCount;
    }

    public void setFollowingCount(int followingCount) {
        this.followingCount = followingCount;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setInterests(List<String> interests) {
        this.interests = interests;
    }

    public void setTweets(List<String> tweets) {
        this.tweets = tweets;
    }

    public void setFollowingUsers(List<String> followingUsers) {
        this.followingUsers = followingUsers;
    }

    public void setFollowers(List<String> followers) {
        this.followers = followers;
    }

    public User(String username, String fullName, int followersCount, int followingCount, String language, String region) {
        this.username = username;
        this.fullName = fullName;
        this.followersCount = followersCount;
        this.followingCount = followingCount;
        this.language = language;
        this.region = region;
        this.interests = new LinkedList<>();
        this.tweets = new LinkedList<>();
        this.followingUsers = new LinkedList<>();
        this.followers = new LinkedList<>();
    }

    // Getters and setters for the fields (username, fullName, followersCount, followingCount, language, region)

    public List<String> getInterests() {
        return interests;
    }

    public void addInterest(String interest) {
        interests.add(interest);
    }

    public List<String> getTweets() {
        return tweets;
    }

    public void addTweet(String tweet) {
        tweets.add(tweet);
    }

    public List<String> getFollowingUsers() {
        return followingUsers;
    }

    public void addFollowingUser(String followingUser) {
        followingUsers.add(followingUser);
    }

    public List<String> getFollowers() {
        return followers;
    }

    public void addFollower(String follower) {
        followers.add(follower);
    }

    // Other methods specific to user functionalities
}