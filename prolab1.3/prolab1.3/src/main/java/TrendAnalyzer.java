
import java.util.*;

public class TrendAnalyzer {
    private LinkedListHashMap<String, Integer> hashtagFrequency; // Hashtag frekanslarını tutmak için hash tablosu

    public TrendAnalyzer() {
        this.hashtagFrequency = new LinkedListHashMap<>(100);
    }

    // Tweetlerdeki hashtag'leri işleyerek frekanslarını güncelleyen metod
    public void processTweetsForTrends(List<String> tweets) {
        for (String tweet : tweets) {
            String[] words = tweet.split("\\s+");
            for (String word : words) {
                if (word.startsWith("#")) { // Hashtag'i kontrol et
                    String hashtag = word.toLowerCase();
                    // Hashtag frekanslarını güncelle
                    hashtagFrequency.put(hashtag, hashtagFrequency.getOrDefault(hashtag, 0) + 1);
                }
            }
        }
    }

    // En popüler N hashtagleri getiren metod
    public List<String> getTopTrends(int n) {
        List<String> topTrends = new ArrayList<>(hashtagFrequency.keySet());
        topTrends.sort((h1, h2) -> hashtagFrequency.get(h2).compareTo(hashtagFrequency.get(h1)));
        return topTrends.subList(0, Math.min(n, topTrends.size()));
    }

    // Hashtag frekanslarını döndüren metod
    public LinkedListHashMap<String, Integer> getHashtagFrequency() {
        return hashtagFrequency;
    }
}