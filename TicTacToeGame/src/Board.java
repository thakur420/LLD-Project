import java.util.ArrayList;
import java.util.List;

// TODO - For Flexibility Make Board Class Abstract
public class Board {
    private int size;
    List<List<Cell>> board;

    public Board(int size) {
        this.size = size;
        board = new ArrayList<>();
        for(int i = 0; i < size; i++){
            List<Cell> rowList = new ArrayList<>();
            for(int j = 0;j < size; j++){
                Cell cell = new Cell(i,j);
                rowList.add(cell);
            }
            board.add(rowList);
        }
    }

    public int getSize() {
        return size;
    }
    public void display(){
        for (int i= 0; i < size; i++){
            List<Cell> rowList = board.get(i);
            for(int j=0; j <size;j++){
                Cell cell = rowList.get(j);
                char displayChar = ' ';
                if(cell.getSymbol() != null)
                    displayChar = cell.getSymbol();
                System.out.print("| " +displayChar + " |");
            }
            System.out.println();
        }
    }
    public void applyMove(Move move) {
        Cell cell = move.getCell();
        board.get(cell.getRow()).set(cell.getCol(), cell);
    }
}
