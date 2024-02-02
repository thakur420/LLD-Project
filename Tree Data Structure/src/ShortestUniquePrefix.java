import java.util.ArrayList;
import java.util.Scanner;

public class ShortestUniquePrefix {
    private static final int CHAR_SIZE = 26;
    private TrieNode root = null;
    public ArrayList<String> prefix(ArrayList<String> A) {
        insertWordsInTrie(A);
        return uniquePrefixList(A);
    }
    private ArrayList<String> uniquePrefixList(ArrayList<String> words) {
        ArrayList<String> unqPrefixList = new ArrayList<>();
        for(String word : words){
            String prefix = getUniquePrefix(word);
            unqPrefixList.add(prefix);
        }
        return unqPrefixList;
    }

    private String getUniquePrefix(String word) {
        TrieNode current = root;
        StringBuilder prefix = new StringBuilder();
        for(int i = 0; i < word.length(); i++){
            char ch = word.charAt(i);
            prefix.append(ch);
            if(current.child[ch-'a'] == null || current.child[ch-'a'].wordCount == 1){
                break;
            }
            current = current.child[ch-'a'];
        }
        return prefix.toString();
    }

    private void insertWordsInTrie(ArrayList<String> words) {
        this.root = new TrieNode();
        for(String word : words)
            insertWord(word);
    }

    private void insertWord(String word) {
        TrieNode current = root;
        for(int i = 0; i < word.length(); i++){
            char ch = word.charAt(i);
            if(current.child[ch - 'a'] == null)
                current.child[ch - 'a'] = new TrieNode();
            current = current.child[ch - 'a'];
            current.wordCount += 1;
        }
    }

    public static void main(String[] args) {
        Scanner sc  = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<String> words = new ArrayList<>();
        for(int i =0; i < n; i++){
            String word = sc.next();
            words.add(word);
        }
        ShortestUniquePrefix sup = new ShortestUniquePrefix();
        ArrayList<String> uniquePrefix = sup.prefix(words);
        System.out.println(uniquePrefix);
    }
    private static class TrieNode {
        TrieNode[] child;
        int wordCount;
        TrieNode(){
            this.child = new TrieNode[CHAR_SIZE];
            this.wordCount = 0;
        }
    }
}
