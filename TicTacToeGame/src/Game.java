import java.util.*;
//import java.util.List;

import Exception.InvalidBoardSizeException;
import Exception.InvalidNumberOfPlayerException;
import Exception.InvalidBotCountPlayerException;
import Exception.DuplicateSymbolException;

/*
TODO : Add Builder for this class so that validation can be done
       before creating game object.
*/
public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private GameStatus gameStatus;
    private int noOfSymbolOnBoard;
    private WinnerCheck tictactoeWinnerCheck;

    public Game(Board board, List<Player> players) {
        this.board = board;
        this.players = players;
        this.moves = new ArrayList<>();
        this.gameStatus = GameStatus.IN_PROGESS;
        this.noOfSymbolOnBoard = 0;
        this.tictactoeWinnerCheck = new TicTacToeWinnerCheck(board.getSize());
    }
    public void validateGameConfiguration() throws InvalidBoardSizeException,
            InvalidNumberOfPlayerException,
            InvalidBotCountPlayerException, DuplicateSymbolException {
        validateBoardSize();
        validateBotCount();
        validatePlayerCount();
        validateDuplicateSymbol();
    }
    public Player startGame(){
        // Shuffle the players
        Collections.shuffle(players);
        // Create a new queue and add the shuffled elements
        Queue<Player> playerQueue = new LinkedList<>(players);
        Player currentPlayer = null;
        while(gameStatus == GameStatus.IN_PROGESS){
            currentPlayer = playerQueue.remove();
            board.display();
            Move move = takeValidMove(currentPlayer);
            if(currentPlayer instanceof HumanPlayer && undo()){
                System.out.println("Play your move again:");
                move = takeValidMove(currentPlayer);
            }
            noOfSymbolOnBoard += 1;
            board.applyMove(move);// fill the cell with the player corresponding move.
            moves.add(move);
            playerQueue.add(currentPlayer);
            checkWinner(move);
        }
        board.display();
        if(gameStatus == GameStatus.WIN)
            return currentPlayer;
        return null;
    }

    private void checkWinner(Move move) {
        if(tictactoeWinnerCheck.hasWinner(move)){
            gameStatus = GameStatus.WIN;
        }
        else if(noOfSymbolOnBoard == board.getSize()*board.getSize()){
            gameStatus = GameStatus.DRAW;
        }
    }

    private Move takeValidMove(Player currentPlayer) {
        Move move = currentPlayer.makeMove(board.getSize());
        while(!isValidMove(move)){
            System.out.println("Your move was not valid. Please play a valid move");
            board.display();
            move = currentPlayer.makeMove(board.getSize());
        }
        return move;
    }

    private boolean undo() {
        System.out.println("Hey! want to undo your last Move?");
        Scanner sc = new Scanner(System.in);
        String undoMove = sc.next();
        return undoMove.equalsIgnoreCase("Y");
    }

    private boolean isValidMove(Move move) {
        Cell cell = move.getCell();
        int row = cell.getRow();
        int col = cell.getCol();
        if(row < 0 || row >= board.getSize())
            return false;
        if(col < 0 || col >= board.getSize())
            return false;
        return board.board.get(row).get(col).getSymbol() == null;
    }

    private void validateBoardSize() throws InvalidBoardSizeException {
        if(board.getSize() < 3 || board.getSize() > 10)
            throw new InvalidBoardSizeException("Board Size should be in the range [3,10], current size "+ board.getSize());
    }
    private void validateBotCount() throws InvalidBotCountPlayerException {
        boolean isBotPresent  = false;
        for(Player player : players){
            if(player instanceof BotPlayer){
                if(isBotPresent){
                    throw new InvalidBotCountPlayerException("Bot Count can't be more than 1.");
                }
                isBotPresent = true;
            }
        }
    }

    private void validatePlayerCount() throws InvalidNumberOfPlayerException {
        if(players.size() != board.getSize()-1)
            throw new InvalidNumberOfPlayerException("No of Player Should be Board size minus 1 current noOfPlayer,boardSize"+
                    players.size()+"," +board.getSize());
    }
    private void validateDuplicateSymbol() throws DuplicateSymbolException {
        HashSet<Character> symbolSet = new HashSet<>();
        for ( Player player : players){
            symbolSet.add(player.getSymbol());
        }
        if(symbolSet.size() != players.size())
            throw new DuplicateSymbolException("All player should have unique symbol");
    }

    public void replay() throws InterruptedException {
        System.out.println("Replaying the game ....");
        Board gameBoard = new Board(board.getSize());
        for(Move move : moves){
            Cell cell = move.getCell();
            Player player = move.getPlayer();
            System.out.println("Move played by "+ player.getName()+ ",sign "+player.getSymbol());
            System.out.println("Move played in cell "+cell.getRow()+"," +cell.getCol());
            gameBoard.applyMove(move);
            gameBoard.display();
            Thread.sleep(2000);
        }
    }
}
