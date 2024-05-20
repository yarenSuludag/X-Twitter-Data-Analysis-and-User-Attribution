import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class InterestAnalyzer {
    private LinkedHashMap<String, Map<String, Integer>> userWordFrequency;

    public InterestAnalyzer() {
        this.userWordFrequency = new LinkedHashMap<>();
    }

    public void createWordFrequency(String username, List<String> interests){
        Map<String, Integer> wordFrequency = new HashMap<>();
        for(String interest : interests){
            wordFrequency.put(interest, wordFrequency.getOrDefault(interest, 0) + 1);
        }
        userWordFrequency.put(username, wordFrequency);
    }
    public void processUserTweets(String username, List<String> tweets) {
        Map<String, Integer> wordFrequency = calculateWordFrequency(tweets);
        userWordFrequency.put(username, wordFrequency);
    }
    String[] unneeded = {"off", "into","finish","speak","keep","guy","two","own","mean",
            "will","for","put","set","listen","new","drop","want","they","world","must",
            "apply","job","alone","face","crime","you","number","least","life","together","million",
            "return","plant","leg","door","piece","hot","line","source","teach","measure","remain","available",
            "weight","appear","their","positive","view","lot","social","available","not","type","fact","ask",
            "worker","letter","claim","all","end","agree","exist","should","because","remember","compare","even","goal",
            "mouth","beautiful","cost","choose","something","mind","drive","now","open","last","care","step","there",
            "then","huge","surface","another","open","current","expert","thus","smile","ready","would","offer",
            "again","month","or","kind","trial","loss","attack","explain","area","member","town","spend",
            "thank","whole","save","grow","generation","this","part","street","civil","official","same","cover",
            "same","win","call","green","threat","focus","how","nice","six","treat","us","nothing","hit",
            "everything","center","behavior","budget","check","hear","need","former","per","live","note",
            "floor","hold","tax","ago","staff","nation","partner","shoulder",
            "both","use","him","able","between","fear","until","quickly","case","his","her","do","similar",
            "reveal","fear","where","who","what","which","when","was","were","only","only","under","near",
            "tonight","tomorrow","tell","hold","and","night","wide","final","tax","somebody","give","just","top",
            "go","significant","whatever","none","right","team","week","guess","test","hard","sell","age","support",
            "cold","public","argue","truth","your","my","page","option","single","why","table","candidate","",
            "each","herself","themselves","common","else","could","can","enough","turn","pass","nearly","very","less",
            "fund","assume","according","forget","long","upon","land","father","station","sense","bag","year","day",
            "item","on","at","away","process","down","fly","thing","prepare","learn","build", "finally","in","it","rule",
            "stage","pick","than","look", "wind","bit","evening","pick","air","throughout","example","mr","altough",
            "perhaps","man","rich","three","cell","card","study","personal","capital","main","within","","","",
            "edge","from","understand","necessary","relate","old","lay","little","reason","also","order","share","push",
            "little","way","red","while","usually","foreign","cup","carry","sign","matter","reality","big","race","me","executive","seven",
            "four","one","begin","everyone","size","think","simple","property","contain","firm","head","entire","spring","bed",
            "pretty","baby","bar","kid","ı ","wall","already","wait","parent","nor","total","find","back","resource",
            "figure","like","someone","stop","although","enter","watch","join","trouble","up","total",
            "happy","other","myself","state"," ı","but","maybe","if","thought","mine"};
    private Map<String, Integer> calculateWordFrequency(List<String> tweets) {
        Map<String, Integer> wordFrequency = new HashMap<>();
        for (String tweet : tweets) {
            String[] words = tweet.split("\\s+");
            for (String word : words) {
                if(Arrays.asList(unneeded).contains(word)){
                    continue;
                }
                word = word.toLowerCase();
                wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
            }
        }
        return wordFrequency;
    }

    public List<String> getTopInterests(int n) {
        LinkedHashMap<String, Integer> totalFrequency = calculateTotalFrequency();
        return extractTopWords(n, totalFrequency);
    }

    private LinkedHashMap<String, Integer> calculateTotalFrequency() {
        LinkedHashMap<String, Integer> totalFrequency = new LinkedHashMap<>();
        for (Map<String, Integer> wordFrequency : userWordFrequency.values()) {
            for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
                String word = entry.getKey();
                int frequency = entry.getValue();
                totalFrequency.put(word, totalFrequency.getOrDefault(word, 0) + frequency);
            }
        }
        return totalFrequency;
    }

    private List<String> extractTopWords(int n, LinkedHashMap<String, Integer> totalFrequency) {
        List<String> topWords = new ArrayList<>(totalFrequency.keySet());
        topWords.sort((w1, w2) -> totalFrequency.get(w2).compareTo(totalFrequency.get(w1)));
        return topWords.subList(0, Math.min(n, topWords.size()));
    }

    public List<String> getUserInterests(String username) {
        Map<String, Integer> wordFrequency = userWordFrequency.get(username);
        if (wordFrequency == null) {
            return Collections.emptyList();
        }

        List<String> interests = new ArrayList<>();
        List<String> topWords = getTopInterests(3); // Top 5 words as an example
        for (String word : topWords) {
            if (wordFrequency.containsKey(word)) {
                interests.add(word);

            }

        }
        System.out.println("Kullanıcı: " + username);
        System.out.println("İlgi Alanları: " + topWords);
        return interests;
    }

    public List<String> getUsersWithSameInterest(String interest) {
        List<String> usersWithSameInterest = new ArrayList<>();

        for (Map.Entry<String, Map<String, Integer>> entry : userWordFrequency.entrySet()) {
            String username = entry.getKey();
            Map<String, Integer> wordFrequency = entry.getValue();
            if (wordFrequency.containsKey(interest)) {
                usersWithSameInterest.add(username);
            }
        }
        return usersWithSameInterest;
    }
    public String findInterest(List<String> tweets) {
        LinkedList<String> words = extractWordsFromTweets(tweets);
        return findMostCommonWord(words);
    }

    private LinkedList<String> extractWordsFromTweets(List<String> tweets) {
        LinkedList<String> words = new LinkedList<>();
        for (String tweet : tweets) {
            String[] tweetWords = tweet.split("\\s+");
            words.addAll((LinkedList<String>) Arrays.asList(tweetWords));
        }
        return words;
    }

    private String findMostCommonWord(LinkedList<String> words) {
        if (words.isEmpty()) {
            return "";
        }

        Map<String, Integer> wordFrequency = new HashMap<>();
        String mostCommonWord = "";
        int maxCount = 0;

        for (String word : words) {
            String lowerCaseWord = word.toLowerCase();
            int count = wordFrequency.getOrDefault(lowerCaseWord, 0) + 1;
            wordFrequency.put(lowerCaseWord, count);

            if (count > maxCount) {
                maxCount = count;
                mostCommonWord = lowerCaseWord;
            }

        }
        return mostCommonWord;

    }

    public void printInterestsAndUsers() {

        for (Map.Entry<String, Map<String, Integer>> entry : userWordFrequency.entrySet()) {
            String username = entry.getKey();
            Map<String, Integer> wordFrequency = entry.getValue();

           System.out.println("Kullanıcı: " + username);
           System.out.println("İlgi Alanları: " + wordFrequency.keySet());
        }
    }

    public void saveInterestsAndUsers() {
        try {
        String filename = "interests/result.txt";
        FileWriter myWriter = new FileWriter(filename);
        for (Map.Entry<String, Map<String, Integer>> entry : userWordFrequency.entrySet()) {
            String username = entry.getKey();
            Map<String, Integer> wordFrequency = entry.getValue();
            myWriter.write("Kullanıcı: " + username + "\n");
            myWriter.write("İlgi Alanları: "+ wordFrequency.keySet() + "\n");
            myWriter.write("---------------------------------------------\n");
        }
        myWriter.close();
        } catch (
                IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public List<InterestConnection> createInterestConnection(){
        Map<String, InterestConnection> connections = new HashMap<>();
        List<String> userSet = new ArrayList<>(userWordFrequency.keySet());
        for(int i = 0; i < userSet.size() - 1; i++){
            for(int j = i + 1; j < userSet.size(); j++){
                if(i == j){
                    continue;
                }
                String user1 = userSet.get(i);
                String user2 = userSet.get(j);
                List<String> words1 = new ArrayList<>(userWordFrequency.get(user1).keySet());
                List<String> words2 = new ArrayList<>(userWordFrequency.get(user2).keySet());
                for(String word : words1){
                    if(words2.contains(word)){
                        if(!connections.containsKey(word)){
                            connections.put(word, new InterestConnection(word,user1, user2));
                        }
                        else{
                            connections.get(word).addUser(user1);
                            connections.get(word).addUser(user2);
                        }
                    }
                }
            }
        }
        return new ArrayList<>(connections.values());
    }
}