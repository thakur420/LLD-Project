import java.util.Random;

public class BotPlayer extends Player {
    private BotDiffcultyLevel botDiffcultyLevel;

    public BotPlayer(String name,Character symbol,BotDiffcultyLevel botDiffcultyLevel) {
        super(name,symbol);
        this.botDiffcultyLevel = botDiffcultyLevel;
    }

    @Override
    public Move makeMove(int size) {
        Random random  = new Random();
        int row = random.nextInt(size);
        int col = random.nextInt(size);
        System.out.println("Bot move => "+ row +"," +col);
        Cell cell = new Cell(row,col,this.getSymbol());
        return new Move(cell,this);
    }
}
