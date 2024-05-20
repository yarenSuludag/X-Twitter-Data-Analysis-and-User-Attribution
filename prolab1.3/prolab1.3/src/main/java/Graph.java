import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

public class Graph {
    private int capacity;
    private LinkedListHashMap<String, InterestHashTable<String, LinkedList<User>>> interestsMap;
    private LinkedListHashMap<String, Set<String>> userRelations;
    public Graph(int capacity) {
        this.capacity = capacity;
        this.interestsMap = new LinkedListHashMap<>(16);
    }

    public Graph() {
        userRelations = new LinkedListHashMap<>(100);
    }
    public LinkedListHashMap<String, InterestHashTable<String, LinkedList<User>>> getInterestsMap() {
        return interestsMap;
    }

    public void setInterestsMap(LinkedListHashMap<String, InterestHashTable<String, LinkedList<User>>> interestsMap) {
        this.interestsMap = interestsMap;
    }

    public User getUser(String username) {
        InterestHashTable<String, LinkedList<User>> userInterests = interestsMap.get(username);
        if (userInterests != null) {
            LinkedList<User> userList = userInterests.get("userList");
            if (userList != null) {
                for (User user : userList) {
                    if (user.getUsername().equals(username)) {
                        return user;
                    }
                }
            }
        }
        return null;
    }

    public void addUser(User user, String interest) {
        InterestHashTable<String, LinkedList<User>> userInterests = interestsMap.get(user.getUsername());

        if (userInterests == null) {
            userInterests = new InterestHashTable<>(100);
            interestsMap.put(user.getUsername(), userInterests);
        }

        LinkedList<User> users = userInterests.get(interest);
        if (users == null) {
            users = new LinkedList<>();
            userInterests.put(interest, users);
        }

        users.add(user);
    }

    public LinkedList<User> getUsersWithSameInterest(String interest) {
        LinkedList<User> usersWithSameInterest = new LinkedList<>();
        for (InterestHashTable<String, LinkedList<User>> userInterests : interestsMap.values()) {
            LinkedList<User> users = userInterests.get(interest);
            if (users != null) {
                usersWithSameInterest.addAll(users);
            }
        }
        return usersWithSameInterest;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public LinkedListHashMap<String, Set<String>> getUserRelations() {
        return userRelations;
    }

    public void setUserRelations(LinkedListHashMap<String, Set<String>> userRelations) {
        this.userRelations = userRelations;
    }

    public LinkedList<User> getAllUsers() {
        LinkedList<User> allUsers = new LinkedList<>();

        for (InterestHashTable<String, LinkedList<User>> userInterests : interestsMap.values()) {
            if (userInterests != null && userInterests.size() > 0) {
                for (LinkedListHashMap.Entry<String, LinkedList<User>> entry : userInterests.entrySet()) {
                    LinkedList<User> users = entry.getValue();
                    if (users != null) {
                        allUsers.addAll(users);
                    }
                }
            }
        }

        return allUsers;
    }
    public void addUser(String username) {
        if (userRelations == null) {
            userRelations = new LinkedListHashMap<>(100);
        }
        if (userRelations.get(username) == null) {
            userRelations.put(username, new LinkedHashSet<>());
        }
    }
    public void addFollower(String followed, String follower) {
        addUser(followed);
        addUser(follower);

        userRelations.get(followed).add(follower);
    }

    public Set<String> getFollowers(String username) {
        return userRelations.getOrDefault(username, new HashSet<>());
    }

    public Set<String> getFollowing(String username) {
        Set<String> following = new HashSet<>();
        for (LinkedListHashMap.Entry<String, Set<String>> entry : userRelations.entrySet()) {
            if (entry.getValue().contains(username)) {
                following.add(entry.getKey());
            }
        }
        return following;
    }

    // Diğer metodlar buraya eklenebilir
}