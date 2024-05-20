import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvReader {

    public static User[] ReadCsv(String filename, int filterCount){
        List<User> userList = new ArrayList<>();
        int userCount = 0;
        try (Reader reader = new FileReader(filename)) {
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader());
            for (CSVRecord csvRecord : csvParser) {

                String username = csvRecord.get("Username");
                String fullName = csvRecord.get("Full Name");
                String followersData = csvRecord.get("Followers");
                String followingUsersData = csvRecord.get("Following");
                String language = csvRecord.get("Language");
                String region = csvRecord.get("Region");
                String interestsData = csvRecord.get("Tweets");

                // Verilerin alınması
                String[] followingUsersArray = followingUsersData.split(",");
                String[] followersArray = followersData.split(",");
                int followingCount = followingUsersArray.length;
                int followerCount = followersArray.length;
                User user = new User(username,fullName,followerCount,followingCount,language,region);
                user.setFollowers(Arrays.asList(followersArray));
                user.setFollowingUsers(Arrays.asList(followingUsersArray));
                userList.add(user);
                userCount++;
                if(userCount == filterCount){
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (User[]) userList.toArray(new User[userList.size()]);
    }
}
