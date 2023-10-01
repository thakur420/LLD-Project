public abstract class Player {
    private String name;
    private char symbol;

    public Player() {
    }

    public Player(String name, Character symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }

    public abstract Move makeMove(int size);
}
