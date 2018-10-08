/**
 * Created by Dana on 2/15/16.
 */
public class NimGame
{
    public static final char PLAYER_A = 'A';
    public static final char PLAYER_B = 'B';

    private Node tempRoot;
    private Node gameTreeRoot;
    private int numSticks, numComputer, numHuman;

    public NimGame(int nSticks, char p) {
        gameTreeRoot = buildGameTree(nSticks, p);
        numSticks = nSticks;
        numComputer = 0;
        numHuman = 0;
        newGame();
    }


    public int getnumSticks() {
        return numSticks;
    }
    public void playTurn(int fishToTake) {
        switch (fishToTake) {
            case 1:
                tempRoot = tempRoot.left;
                numSticks--;
                numHuman++;
                break;

            case 2:
                tempRoot = tempRoot.center;
                numSticks = numSticks - 2;
                numHuman = numHuman + 2;
                break;

            case 3:
                tempRoot = tempRoot.right;
                numSticks = numSticks - 3;
                numHuman = numHuman + 3;
        }
    }
    
    final public void newGame() {
        tempRoot = gameTreeRoot;
        numComputer = 0;
        numHuman = 0;
    }
    final public Node buildGameTree(int nStick, char player) {
        Node n = new Node();
        n.nStick = nStick;
        n.player = player;

        if (nStick >= 1) {
            n.left = buildGameTree(nStick - 1, (player == PLAYER_A) ? PLAYER_B : PLAYER_A);
        }
        if (nStick >= 2) {
            n.center = buildGameTree(nStick - 2, (player == PLAYER_A) ? PLAYER_B : PLAYER_A);
        }
        if (nStick >= 3) {
            n.right = buildGameTree(nStick - 3, (player == PLAYER_A) ? PLAYER_B : PLAYER_A);
        }

        return n;
    }

    public int computeMinimax(Node n) {
        int ans;

        if (n.nStick == 0) {
            return (n.player == PLAYER_A) ? 1 : -1;
        } else if (n.player == PLAYER_A) {
            ans = Math.max(-1, computeMinimax(n.left));
            if (n.center != null) {
                ans = Math.max(ans, computeMinimax(n.center));
                if (n.right != null) {
                    ans = Math.max(ans, computeMinimax(n.right));
                }
            }
        } else {
            ans = Math.min(1, computeMinimax(n.left));
            if (n.center != null) {
                ans = Math.min(ans, computeMinimax(n.center));
                if (n.right != null) {
                    ans = Math.min(ans, computeMinimax(n.right));
                }
            }
        }
        return ans;
    }

    public int takeBestMove() {


        int v1 = computeMinimax(tempRoot.left);
        int v2 = (tempRoot.center != null)
                ? computeMinimax(tempRoot.center) : 2;
        int v3 = (tempRoot.right != null)
                ? computeMinimax(tempRoot.right) : 2;

        int takenStick;
        if (v1 < v2 && v1 < v3) {
            takenStick = 1;
            tempRoot = tempRoot.left;
        } else if (v2 < v1 && v2 < v3) {
            takenStick = 2;
            tempRoot = tempRoot.center;
        } else if (v3 < v1 && v3 < v2) {
            takenStick = 3;
            tempRoot = tempRoot.right;
        } else {
            takenStick = (int) (Math.random() * 3) + 1;
            switch (takenStick) {
                case 1:
                    tempRoot = tempRoot.left;
                    break;

                case 2:
                    tempRoot = tempRoot.center;
                    break;

                case 3:
                    tempRoot = tempRoot.right;
            }
        }

        numSticks = numSticks - takenStick;
        numComputer = numComputer + takenStick;
        return takenStick;
    }


}
