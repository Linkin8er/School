import java.util.Random;
public class MontyHall
{
 
    public static void main(String[] args){
    MontyHall runner = new MontyHall();
    } 
    
    public MontyHall(){
   
        Random random = new Random();
        int guess;
        int door;
        int correctGuesses = 0;
   
        for(int x=0; x<=10000; x++){
     
            door  = random.nextInt(3)+1;//assigns the correct door to a random nuber between 1-3
            guess = random.nextInt(3)+1;//assigns the guess to a random number between 1 - 3
     
            if(door != guess) correctGuesses++;// checks to see if the guess is not equal to the door. if not, then it will be a correct guess
            //the only time you get a guess wrong if you switch every time is if the original was the correct door.
            //to simplify the code, we simply check to see if the car is behind the initial guess. IF not, then we will switch to the correct answer.
        }
   
        System.out.println("Number of correct guesses switching: " + correctGuesses);
        System.out.println("percent is : "+ (((double)correctGuesses/(double)10000)*100) + "%");
        correctGuesses=0;
   
        for(int y=0; y<=10000; y++){
     
            door  = random.nextInt(3)+1;//assigns the correct door to a random nuber between 1-3
            guess = random.nextInt(3)+1;//assigns the guess to a random number between 1 - 3
     
            if(guess == door) correctGuesses++;// checks to see if the guess is equal to the door. If it guessed correctly, we add 1 to the correct guesses.
            //the only time you get a correct guess is if the original was the correct door.
            //to simplify the code, we simply check to see if the car is behind the initial guess. if not, then we will not swith and have the correct answer.
        }
        System.out.println("Number of correct guesses staying: " + correctGuesses);
        System.out.println("percent is : "+ (((double)correctGuesses/(double)10000)*100)+ "%");
    }
}







