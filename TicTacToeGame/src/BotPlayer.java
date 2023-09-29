public class BotPlayer extends Player {
    private BotDiffcultyLevel botDiffcultyLevel;

    public BotPlayer(String name,Character symbol,BotDiffcultyLevel botDiffcultyLevel) {
        super(name,symbol);
        this.botDiffcultyLevel = botDiffcultyLevel;
    }

    @Override
    public Move makeMove() {
        return null;
    }
}
