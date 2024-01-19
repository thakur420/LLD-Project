import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Creating Avl Tree ...");
        AVLTree avlTree = new AVLTree();
        int[] numbers = {14,17,11,7,53,4,13,12,8,60,19,16,20};
        for(int x : numbers){
            avlTree.insert(x);
            System.out.println("root => " +avlTree.getRoot().val);
        }
        avlTree.inOrder(avlTree.getRoot());
        System.out.println("\n"+avlTree.getRoot().val);

    }
}