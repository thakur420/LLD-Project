import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TicTacToeWinnerCheck implements WinnerCheck{
    private int size;
    private List<Map<Character, Integer>> rowMap;
    private List<Map<Character,Integer>> colMap;
    private Map<Character,Integer> leftToRightDiagonal;
    private Map<Character,Integer> rightToLeftDiagonal;

    public TicTacToeWinnerCheck(int size) {
        this.size = size;
        rowMap = new ArrayList<>();
        colMap = new ArrayList<>();
        leftToRightDiagonal = new HashMap<>();
        rightToLeftDiagonal = new HashMap<>();
        for(int i=0; i < size; i++){
            rowMap.add(new HashMap<>());
            colMap.add(new HashMap<>());
        }
    }

    @Override
    public boolean hasWinner(Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Character symbol = move.getCell().getSymbol();
        if(rowWinnerCheck(row,symbol))
            return true;
        if(colWinnerCheck(col,symbol))
            return true;
        if(isDiagonalMove(row,col) && leftToRightDiagonalWinner(symbol))
            return true;
        return isDiagonalMove(row, col) && rightToLeftDiagonalWinner(symbol);
    }

    private boolean rightToLeftDiagonalWinner(Character symbol) {
        rightToLeftDiagonal.putIfAbsent(symbol,0);
        rightToLeftDiagonal.put(symbol,rightToLeftDiagonal.get(symbol)+1);
        return rightToLeftDiagonal.get(symbol) == size;
    }

    private boolean leftToRightDiagonalWinner(Character symbol) {
        leftToRightDiagonal.putIfAbsent(symbol,0);
        leftToRightDiagonal.put(symbol,leftToRightDiagonal.get(symbol)+1);
        return leftToRightDiagonal.get(symbol) == size;
    }

    private boolean isDiagonalMove(int row, int col) {
        return row == col || (row + col) == size - 1;
    }

    private boolean rowWinnerCheck(int row,Character symbol){
        rowMap.get(row).putIfAbsent(symbol,0);
        rowMap.get(row).put(symbol,rowMap.get(row).get(symbol)+1);
        return rowMap.get(row).get(symbol) == size;
    }
    private boolean colWinnerCheck(int col,Character symbol){
        colMap.get(col).putIfAbsent(symbol,0);
        colMap.get(col).put(symbol,colMap.get(col).get(symbol)+1);
        return colMap.get(col).get(symbol) == size;
    }
    
}
