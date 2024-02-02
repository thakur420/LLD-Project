import java.util.*;

public class AutoComplete {
    ArrayList<String> dictionary  = null;
    ArrayList<Integer> weight = null;
    ArrayList<String> words = null;
    int totalChar = 26;
    int maxOutputSize = 5;
    TrieNode root = null;
    private void takeUserInput(Scanner sc){
        int n = sc.nextInt();
        int m = sc.nextInt();

        dictionary  = new ArrayList<>();
        for(int i = 0; i < n; i++)
            dictionary.add(sc.next());

        weight = new ArrayList<>();
        for(int i = 0; i < n; i++)
            weight.add(sc.nextInt());

        words = new ArrayList<>();
        for(int i=0; i < m ;i++)
            words.add(sc.next());
    }

    private void insertWord(String word,int idx){
        TrieNode current = root;
//        System.out.println("Prfix for word "+ word);
        for(int i = 0; i < word.length(); i++){
            char ch = word.charAt(i);
            if(current.child[ch - 'a'] == null)
                current.child[ch - 'a'] = new TrieNode();
            current = current.child[ch - 'a'];
            if(current.prefixWordIndex.size() < 5)
                current.prefixWordIndex.add(idx);
//            System.out.println(ch);
//            System.out.println(current.prefixWordIndex);
        }
    }
    private ArrayList<String> sortByWeight() {
        ArrayList<String> result = new ArrayList<>(dictionary);
        Collections.sort(result, Comparator.comparingInt(word -> weight.get(dictionary.indexOf(word))).reversed());
        return result;
    }
    private void printResult(ArrayList<Integer> wordIndex) {
        if(wordIndex.get(0) == -1){
            System.out.println("-1 ");
            return;
        }
        for(int idx : wordIndex)
            System.out.print(dictionary.get(idx)+" ");
        System.out.println();
    }

    private ArrayList<Integer> findAllWordIndex(String word) {
        TrieNode current = root;
        for(int i = 0; i < word.length(); i++){
            char ch = word.charAt(i);
            if(current == null)
                break;
            current = current.child[ch-'a'];
        }
        if(current == null)
            return new ArrayList<>(List.of(-1));
        return current.prefixWordIndex;
    }
    private void generateAutoCompleteWords(){
        dictionary = sortByWeight();
        this.root = new TrieNode();
        for(int i = 0; i < dictionary.size(); i++)
            insertWord(dictionary.get(i),i);
        for(String word : words){
            ArrayList<Integer> wordIndex = findAllWordIndex(word);
            printResult(wordIndex);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testcase = sc.nextInt();
        AutoComplete autoComplete = new AutoComplete();
        while(testcase-- > 0){
            autoComplete.takeUserInput(sc);
            autoComplete.generateAutoCompleteWords();
            autoComplete.printPrefixIndex(autoComplete.root,new StringBuilder());
        }
    }

    private void printPrefixIndex(TrieNode current,StringBuilder prefix) {
//        System.out.println("prefix word => " + prefix+", "+current);
        if(current == null)
            return;
        for(int i =0; i < totalChar; i++){
            char ch = (char) (i + 'a');
            prefix.append(ch);
            if(current.child[i] != null){
                System.out.println(prefix);
                System.out.println(current.child[i].prefixWordIndex);
                printPrefixIndex(current.child[i],prefix);
            }
            prefix.deleteCharAt(prefix.length()-1);
        }
    }

    private class TrieNode{
        TrieNode[] child;
        int wordCount;
        ArrayList<Integer> prefixWordIndex;
        TrieNode(){
            this.child = new TrieNode[totalChar];
//            this.wordCount = 0;
            this.prefixWordIndex = new ArrayList<>();
        }
    }
}