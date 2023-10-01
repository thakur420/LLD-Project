import java.util.Scanner;

public class HumanPlayer extends Player{
    public HumanPlayer(String playerName, Character playerSymbol) {
        super(playerName,playerSymbol);
    }

    @Override
    public Move makeMove(int size) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter row in range[0,"+size+"]:");
        int row = sc.nextInt();
        System.out.println("Enter col in range[0,"+size+"]:");
        int col = sc.nextInt();
        Cell cell = new Cell(row,col,this.getSymbol());
        return new Move(cell,this);
    }
}
