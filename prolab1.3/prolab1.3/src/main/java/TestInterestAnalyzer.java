import java.util.Arrays;
import java.util.List;

public class TestInterestAnalyzer {
    public static void main(String[] args) {
        InterestAnalyzer analyzer = new InterestAnalyzer();
        ListIslemleri listIslemleri = new ListIslemleri();

        // Kullanıcı 1: Alice
        List<String> aliceTweets = Arrays.asList(
                "Coding is fun!",
                "Java programming is interesting"
        );
        analyzer.processUserTweets("Alice", aliceTweets);

        // Kullanıcı 2: Bob
        List<String> bobTweets = Arrays.asList(
                "I love music",
                "Rock music is the best!"
        );
        analyzer.processUserTweets("Bob", bobTweets);

        // Kullanıcı 3: Carol
        List<String> carolTweets = Arrays.asList(
                "Programming languages are diverse",
                "Music and coding make a great combination"
        );
        analyzer.processUserTweets("Carol", carolTweets);

        // Alice'in ilgi alanlarını alalım
        List<String> aliceInterests = listIslemleri.getUserInterests(analyzer, "Alice");
        System.out.println("Alice's interests: " + aliceInterests);

        // Bob'un ilgi alanlarını alalım
        List<String> bobInterests = listIslemleri.getUserInterests(analyzer, "Bob");
        System.out.println("Bob's interests: " + bobInterests);

        // Music ilgi alanına sahip kullanıcıları bulalım
        List<String> usersWithMusicInterest = listIslemleri.getUsersWithSameInterest(analyzer, "music");
        System.out.println("Users interested in Music: " + usersWithMusicInterest);
    }
}