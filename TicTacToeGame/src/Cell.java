public class Cell {
    private int row;
    private int col;
    private Character symbol;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        symbol = null;
    }

    public Cell(int row, int col, Character symbol) {
        this.row = row;
        this.col = col;
        this.symbol = symbol;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Character getSymbol() {
        return symbol;
    }
}
