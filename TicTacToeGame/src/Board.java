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
}
