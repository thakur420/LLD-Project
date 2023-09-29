import java.util.*;

import Exception.*;

public class TicTacToe {
    public static void main(String[] args) throws InvalidBotCountPlayerException, InvalidNumberOfPlayerException, DuplicateSymbolException, InvalidBoardSizeException {
        // TODO : Create Board
        System.out.println("Enter Board Size : ");
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        Board board = new Board(size);
        // TODO : Create Player
        int noOfHumanPlayer = size - 1;
        Queue<Player> players = new LinkedList<>();
        Player botPlayer = takeBotPlayer(sc);
        if(botPlayer != null){
            noOfHumanPlayer -= 1;
            players.add(botPlayer);
        }
        for(int i =0; i < noOfHumanPlayer; i++){
            Player humanPlayer = takeHumanPlayer(sc);
            players.add(humanPlayer);
        }
        Game game = new Game(board,players);
        game.validateGameConfiguration();
        Player winner = game.startGame();

    }
    private static Player takeBotPlayer(Scanner sc){
        System.out.println("Is Game have Bot Player : Y/N");
        String isBotPlayer = sc.next();
        if(isBotPlayer.equalsIgnoreCase("Y")){
            System.out.println("Enter Bot Name :");
            String botName = sc.next();
            System.out.println("Enter Bot Symbol :");
            String botSymbol = sc.next();
            return new BotPlayer(botName,botSymbol.charAt(0),BotDiffcultyLevel.EASY);
        }
        return null;
    }
    private static Player takeHumanPlayer(Scanner sc){
        System.out.println("Enter Player Name :");
        String playerName = sc.next();
        System.out.println("Enter Player Symbol :");
        String playerSymbol = sc.next();
        return new HumanPlayer(playerName,playerSymbol.charAt(0));
    }
}