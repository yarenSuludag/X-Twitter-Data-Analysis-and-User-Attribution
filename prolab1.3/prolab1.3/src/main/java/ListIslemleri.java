import java.util.*;

public class ListIslemleri<T> {
    private List<T> liste;

    public ListIslemleri() {
        liste = new ArrayList<>();
    }



    public void elemanEkle(T eleman) {
        liste.add(eleman);
    }

    public void elemanSil(T eleman) {
        liste.remove(eleman);
    }

    public boolean elemanAra(T eleman) {
        return liste.contains(eleman);
    }

    public int listeBoyutu() {
        return liste.size();
    }

    public void listeYazdir() {
        System.out.println("Listenin Elemanları:");
        for (T eleman : liste) {
            System.out.println(eleman);
        }
    }

    public List<T> getListe() {
        return liste;
    }
    public T[] toArray(T[] arr) {
        return liste.toArray(arr);
    }

    // Özel bir liste yapısı döndüren bir metod

    public void setListe(List<T> liste) {
        this.liste = liste;
    }
    public static void processUserTweets(InterestAnalyzer analyzer, String username, String[] interestsArray) {
        analyzer.processUserTweets(username, Arrays.asList(interestsArray));
    }
    public static void processUserInterest(InterestAnalyzer analyzer, String username, List<String> interests) {
        analyzer.createWordFrequency(username, interests);
    }
    public static List<String> getUserInterests(InterestAnalyzer analyzer, String userName) {
        return analyzer.getUserInterests(userName);
    }

    public List<String> getUsersWithSameInterest(InterestAnalyzer analyzer, String interest) {
        return analyzer.getUsersWithSameInterest(interest);
    }
}