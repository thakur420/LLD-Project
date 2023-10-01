import java.util.Scanner;

public class HumanPlayer extends Player{
    public HumanPlayer(String playerName, Character playerSymbol) {
        super(playerName,playerSymbol);
    }

    @Override
    public Move makeMove() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter row :");
        int row = sc.nextInt();
        System.out.println("Enter col :");
        int col = sc.nextInt();
        Cell cell = new Cell(row,col,this.getSymbol());
        return new Move(cell,this);
    }
}
