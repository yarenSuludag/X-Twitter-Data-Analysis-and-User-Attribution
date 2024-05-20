import java.util.LinkedList;

public class InterestFinder {
    private InterestHashTable<String, LinkedList<String>> interestsMap;

    public InterestFinder(InterestHashTable<String, LinkedList<String>> interestsMap) {
        this.interestsMap = interestsMap;
    }

    public InterestHashTable<String, LinkedList<String>> getInterestsMap() {
        return interestsMap;
    }

    public void setInterestsMap(InterestHashTable<String, LinkedList<String>> interestsMap) {
        this.interestsMap = interestsMap;
    }

    public void analyzeTextForInterests(User user, String text) {
        String[] words = text.split("\\s+");

        for (String word : words) {
            if (wordIsInterest(word)) {
                addUserInterest(user, word);
            }
        }
    }

    private void addUserInterest(User user, String interest) {
        LinkedList<String> userInterests = interestsMap.get(user.getUsername());
        if (userInterests == null) {
            userInterests = new LinkedList<>();
            interestsMap.put(user.getUsername(), userInterests);
        }
        userInterests.add(interest);
    }

    private boolean wordIsInterest(String word) {
        return !word.isEmpty();
    }
}