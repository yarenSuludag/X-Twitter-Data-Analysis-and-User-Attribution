
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.*;



public class Main {
    public static void main(String[] args) {
        processDataFromFile("user_dataset.csv");
    }

    private static void processDataFromFile(String filename) {

        try (Reader reader = new FileReader(filename)) {
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader());
            Graph graph = new Graph(100);
            InterestAnalyzer analyzer = new InterestAnalyzer();
            LinkedListHashMap<String, InterestHashTable> interestsMap = new LinkedListHashMap<>(16);
            InterestHashTable interestHashTable = new InterestHashTable(16);
            int count = 0;
            for (CSVRecord csvRecord : csvParser) {
                if(count == 20){
                    break;
                }
                processUserData(csvRecord, graph, analyzer, interestsMap, interestHashTable);
                count++;
            }
            List<InterestConnection> connections = analyzer.createInterestConnection();
            System.out.println(connections.size());
            try {
                String connectionResultFilename = "interests/connections.txt";
                FileWriter myWriter = new FileWriter(connectionResultFilename);

                for(InterestConnection connection : connections){
                    myWriter.write(connection.toString() + "\n");
                    myWriter.write("---------------------------------------------\n");
                }
                myWriter.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processUserData(CSVRecord csvRecord, Graph graph,
                                        InterestAnalyzer analyzer, LinkedListHashMap<String, InterestHashTable> interestsMap,
                                        InterestHashTable interestHashTable) {
        String username = csvRecord.get("Username");
        String fullName = csvRecord.get("Full Name");
        String followersData = csvRecord.get("Followers");
        String followingUsersData = csvRecord.get("Following");
        String language = csvRecord.get("Language");
        String region = csvRecord.get("Region");
        String tweets = csvRecord.get("Tweets");

        // Verilerin alınması
        String[] followingUsersArray = followingUsersData.split(",");
        String[] followersArray = followersData.split(",");
        int followingCount = followingUsersArray.length;
        int followerCount = followersArray.length;

        User user = graph.getUser(username);

        if (user == null) {
            user = new User(username, fullName, followerCount, followingCount, language, region);
            graph.addUser(user, tweets);
        }

        graph.addUser(username);
        graph.addUser(followingUsersData);
        graph.addUser(followersData);
        graph.addFollower(followingUsersData, followersData);
        // ListIslemleri.getUserInterests(analyzer, username);
        String[] tweetArray = tweets.split(",");
        user.setTweets(Arrays.asList(tweetArray));

/*
        for (String interest : interestsArray) {
            user.addInterest(interest.trim());
        }
*/
        for (String followingUser : followingUsersArray) {
            String trimmedFollowingUser = followingUser.trim();
            graph.addUser(trimmedFollowingUser);
            graph.addFollower(username, trimmedFollowingUser);
        }

        for (String follower : followersArray) {
            String trimmedFollower = follower.trim();
            graph.addUser(trimmedFollower);
            graph.addFollower(trimmedFollower, username);
        }

        // ListIslemleri.processUserTweets(analyzer, username, tweetArray);

        POSTaggerME posTagger = null;
        try{
        InputStream modelIn = new FileInputStream("en-pos-perceptron.bin");
        POSModel model = new POSModel(modelIn);
        posTagger = new POSTaggerME(model);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(user.getUsername());
        // Tweetler analiz edildi ve interestler kişilerin objesindeki ilgi listesine eklendi.
        if(posTagger != null){
            System.out.println(user.getTweets().size());
            for(String tweet : user.getTweets()){
                List<String> interests = TweetAnalyzer.getInterests(tweet, posTagger);
                for(String interest : interests){
                    if(!user.getInterests().contains(interest)){
                        user.addInterest(interest);
                    }
                }
            }
        }

        ListIslemleri.processUserInterest(analyzer,username, user.getInterests());
        // İlgi alanlarının ve kullanıcıların yazdırılması


        analyzer.saveInterestsAndUsers();
        analyzer.printInterestsAndUsers();

    }



}