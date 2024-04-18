import java.util.Scanner;
public class Tester {

    Scanner scan = new Scanner(System.in);
    public static void main(String[] args){

        StatsLibrary statsTester = new StatsLibrary();
        PokemonCardGame testGame = new PokemonCardGame();
        
        MonteCarloPokeTester test = new MonteCarloPokeTester(15);
        MonteCarloBrickDeck bricker = new MonteCarloBrickDeck(5);
        
        testGame.runGame();

        statsTester.operationPicker();

    }
    
}
