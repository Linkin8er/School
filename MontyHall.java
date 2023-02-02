import java.util.Random;

public class MontyHall {
    public static void main(String[] args){

        MontyHallTester tester = new MontyHallTester();
        tester.pickADoor(10000, 1);
        tester.getResultsStay();
        tester.getResultsSwitch();
    }
}

class MontyHallTester{

    private int correctGuessesSwitch;
    private int correctGuessesStay;
    private int iterations;

    public MontyHallTester(){

        correctGuessesSwitch = 0;
        correctGuessesStay = 0;
        iterations = 0;

    }

    public void pickADoor(int numberOfTimes, int stayOrSwitch){

        iterations = numberOfTimes;
        Random random = new Random();
        int correctAnswer;
        int myGuess;

        for(int i = 0; i < numberOfTimes; i++){

            correctAnswer = random.nextInt(3);
            myGuess = random.nextInt(3);

            if(stayOrSwitch == 0){
                if(myGuess == correctAnswer) correctGuessesStay++;
            }
            if(stayOrSwitch == 1){
                if(correctAnswer == 1){
                    
                }
            }

        }
    }

    public void getResultsStay() { 
        System.out.println("I was correct " + correctGuessesStay + " times, or " + (correctGuessesStay/iterations) + "% of the time"); 
    }
    public int getResultsSwitch() { return correctGuessesSwitch; }

}
