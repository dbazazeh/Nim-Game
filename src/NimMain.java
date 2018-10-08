/**
 * Created by Dana on 2/15/16.
 */
import java.util.Scanner;
import java.util.Random;
public class NimMain {
    public static void main (String args []){
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(10);

        if (randomInt % 2 == 0){
            int cMove;
            NimGame a = new NimGame(15, 'A');
            int n;

            Scanner in = new Scanner(System.in);


            while (a.getnumSticks() > 1) {
                System.out.println("There are " + a.getnumSticks() + " remaining in the pile");
                System.out.println("Enter a valid number between 1-3");
                n = in.nextInt();

                if ((n != 1 || n != 2 || n != 3) && n > a.getnumSticks()) {
                    while (n != 1 || n != 2 || n != 3) {
                        System.out.println("You can only enter 1,2 or 3");
                        n = in.nextInt();
                    }
                    while (n > a.getnumSticks()) {
                        System.out.println("There are only " + a.getnumSticks() + " remaining");
                        System.out.println("Enter a valid number between 1-3");
                        n = in.nextInt();
                    }
                }
                else {
                    a.playTurn(n);
                    a.buildGameTree(a.getnumSticks(), 'B');
                    if (a.getnumSticks() == 1)
                    {
                        System.out.println("You won!");

                    }
                    if (a.getnumSticks() > 1) {
                        cMove = a.takeBestMove();
                        System.out.println("The computer took " + cMove + " sticks");
                        if (a.getnumSticks() == 1)
                        {
                            System.out.println("You lost!");
                        }
                    }
                }
            }
            System.out.println("Game is over!");
        }
        else {
            int cMove;
            NimGame a = new NimGame(15, 'B');
            int n;

            Scanner in = new Scanner(System.in);

            while (a.getnumSticks() > 1) {
                cMove = a.takeBestMove();
                System.out.println("The computer took " + cMove + " sticks");
                if (a.getnumSticks() == 1) {
                    System.out.println("There are " + a.getnumSticks() + " remaining in the pile");
                    System.out.println("You lost!");
                    break;
                }


                System.out.println("There are " + a.getnumSticks() + " remaining in the pile");
                System.out.println("Enter a valid number between 1-3");
                n = in.nextInt();

                if ((n != 1 || n != 2 || n != 3) && n > a.getnumSticks()) {
                    while (n != 1 || n != 2 || n != 3) {
                        System.out.println("You can only enter 1,2 or 3");
                        n = in.nextInt();
                    }

                    while (n > a.getnumSticks()) {
                        System.out.println("There are only " + a.getnumSticks() + " remaining");
                        System.out.println("Enter a valid number between 1-3");
                        n = in.nextInt();
                    }

                } else {

                    a.playTurn(n);
                    a.buildGameTree(a.getnumSticks(), 'A');
                    if (a.getnumSticks() == 1) {
                        System.out.println("You won!");
                    }
                }
            }
            System.out.println("Game is over!");
        }

    }
}
