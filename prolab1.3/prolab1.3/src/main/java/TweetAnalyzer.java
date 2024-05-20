import opennlp.tools.postag.POSTaggerME;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TweetAnalyzer {
    static List<String> unneeded = new ArrayList<>(Arrays.asList("off", "into","finish","speak","keep","guy","two","own","mean",
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
            "happy","other","myself","state"," ı","but"));
    public static List<String> getInterests(String tweet, POSTaggerME posTagger){
        List<String> interests = new ArrayList<>();
        for(String text : tweet.split("\n")){
            String[] tokens = tokenizeText(text);

            String[] posTags = posTagger.tag(tokens);
            String prefix = "";
            for (int i = 0; i < tokens.length; i++) {
                if(posTags[i].equals("VBG") || posTags[i].equals("NN") || posTags[i].equals("NNS")){
                    String interest = prefix + tokens[i];
                    if(interest.contains("\\n")){
                        continue;
                    }
                    interest = interest.toLowerCase();
                    interest = interest.replaceAll(",","");
                    interest = interest.replaceAll("\\.","");
                    interest = interest.replaceAll("]","");
                    interest = interest.replaceAll("\\[","");
                    interest = interest.replaceAll("\\'","");
                    interest = interest.replaceAll("\\'","");
                    interest = interest.trim();
                    if(interest.length() > 0 && !TweetAnalyzer.unneeded.contains(interest)){
                        //System.out.println("I " +  interest);
                        interests.add(interest);
                    }
                    prefix = "";
                }
                else if (posTags[i].equals("JJ")){
                    prefix += tokens[i] + " ";
                }
                // System.out.println("Token: " + tokens[i] + ", POS Tag: " + posTags[i]);
            }
        }


        return interests;
    }


    private static String[] tokenizeText(String text) {
        // You can replace this with your own tokenization logic
        return text.split("\\s+");
    }
}
