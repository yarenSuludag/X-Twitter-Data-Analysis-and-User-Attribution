
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class InterestConnection {
    String interest;
    Set<String> users;

    InterestConnection(String interest, String user1, String user2){
        this.interest = interest;
        this.users = new HashSet<>();
        users.add(user1);
        users.add(user2);
    }

    public void addUser(String user){
        users.add(user);
    }

    @Override
    public String toString() {
        return "Interest : "+ interest +"\nUsers : " +    users.stream()
                .collect(Collectors.joining(",", "{", "}"));

    }
}
