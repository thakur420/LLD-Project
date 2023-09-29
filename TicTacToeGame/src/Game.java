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

    public Game(Board board, List<Player> players) {
        this.board = board;
        this.players = players;
        this.moves = new ArrayList<>();
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
        Player winner = null;
        while (isGameOver()){
            Player currentPlayer = playerQueue.remove();
            Move move = currentPlayer.makeMove();
            while(!isValidMove(move)){
                move = currentPlayer.makeMove();
            }
            applyMove();
            moves.add(move);
        }
        return winner;
    }

    private boolean isValidMove(Move move) {
        return true;
    }

    private void applyMove() {
    }

    private boolean isGameOver() {
        return true;
    }

    public void initialiseGame(){
         /*
                Initialize Game with initial State.
         */
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


}
