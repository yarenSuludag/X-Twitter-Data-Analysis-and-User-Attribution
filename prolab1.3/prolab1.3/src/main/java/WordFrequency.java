public class WordFrequency {
    private LinkedListHashMap<String, Integer> wordFrequency;

    public WordFrequency() {
        this.wordFrequency = new LinkedListHashMap<>(100);
    }

    public LinkedListHashMap<String, Integer> getWordFrequency() {
        return wordFrequency;
    }

    public void setWordFrequency(LinkedListHashMap<String, Integer> wordFrequency) {
        this.wordFrequency = wordFrequency;
    }

    public void processText(String text) {
        String[] words = text.split("\\s+");

        for (String word : words) {
            word = word.toLowerCase();
            wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
        }
    }

    public int getFrequency(String word) {
        return wordFrequency.getOrDefault(word.toLowerCase(), 0);
    }}