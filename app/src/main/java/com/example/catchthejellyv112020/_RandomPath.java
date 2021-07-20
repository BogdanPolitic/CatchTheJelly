package co.snOmOtiOn.bogdan.catchthejellyv112020;
import java.util.Random;

public class _RandomPath {
    static int dimY = 6, dimX = 10;
    static Coordinates[] v;
    static Coordinates pos;
    static Coordinates thirdPrevPos, prevPos;
    static int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
    static int curDir;	// catre ce se VA indrepta! NU de unde a venit!
    static int i;
    static int var;

    private static boolean allowed() {
        if ((pos.x == 0 && curDir == LEFT) ||
                (pos.x == dimX - 1 && curDir == RIGHT) ||
                (pos.y == 0 && curDir == UP) ||
                (pos.y == dimY - 1 && curDir == DOWN))	// bound crash
            return false;
        if ((pos.x == 1 && pos.y == 0 && curDir == LEFT) ||
                (pos.x == 1 && pos.y == dimY - 1 && curDir == LEFT) ||
                (pos.x == dimX - 2 && pos.y == 0 && curDir == RIGHT) ||
                (pos.x == dimX - 2 && pos.y == dimY - 1 && curDir == RIGHT) ||
                (pos.x == 0 && pos.y == 1 && curDir == UP) ||
                (pos.x == dimX - 1 && pos.y == 1 && curDir == UP) ||
                (pos.x == 0 && pos.y == dimY - 2 && curDir == DOWN) ||
                (pos.x == dimX - 1 && pos.y == dimY - 2 && curDir == DOWN))	// corner
            return false;
        if ((pos.x - 1 == thirdPrevPos.x && pos.y == thirdPrevPos.y && curDir == LEFT) ||
                (pos.x + 1 == thirdPrevPos.x && pos.y == thirdPrevPos.y && curDir == RIGHT) ||
                (pos.x == thirdPrevPos.x && pos.y - 1 == thirdPrevPos.y && curDir == UP) ||
                (pos.x == thirdPrevPos.x && pos.y + 1 == thirdPrevPos.y && curDir == DOWN))	// prevPos crash
            return false;
        if ((pos.x - 1 == prevPos.x && pos.y == prevPos.y && curDir == LEFT) ||
                (pos.x + 1 == prevPos.x && pos.y == prevPos.y && curDir == RIGHT) ||
                (pos.x == prevPos.x && pos.y - 1 == prevPos.y && curDir == UP) ||
                (pos.x == prevPos.x && pos.y + 1 == prevPos.y && curDir == DOWN))	// prevPos crash
            return false;
        return true;
    }

    private static void generatePos() {
        Random rand = new Random();
        var = rand.nextInt(4);
        int x = rand.nextInt(dimX);
        int y = rand.nextInt(dimY);
        switch (var) {
            case 0:
                curDir = RIGHT;
                break;
            case 1:
                curDir = DOWN;
                break;
            case 2:
                curDir = LEFT;
                break;
            case 3:
                curDir = UP;
                break;
        }
        pos = new Coordinates(y, x);
        System.out.println("pos = " + "(" + pos.y + " " + pos.x + ") curDir = " + curDir);
    }

    private static void nextDir() {
        Random rand = new Random();
        curDir = rand.nextInt(4);
        //curDir = (curDir + curDir / 2) % 4;
        while (!allowed()) {
            curDir = rand.nextInt(4);
            //curDir = (curDir + curDir / 2) % 4;
        }
    }

    private static void nextPos() {
        switch (curDir) {
            case 0:	// up
                pos = new Coordinates(pos.y - 1, pos.x);
                break;
            case 1:	// down
                pos = new Coordinates(pos.y + 1, pos.x);
                break;
            case 2:	// left
                pos = new Coordinates(pos.y, pos.x - 1);
                break;
            case 3:	// right
                pos = new Coordinates(pos.y, pos.x + 1);
                break;
        }
    }

    private static int max(int a, int b) {
        return a > b ? a : b;
    }

    public static Coordinates[] main() {
        v = new Coordinates[dimY * dimX];
        thirdPrevPos = new Coordinates(0, 0);
        generatePos();
        for (i = 0; i < dimY * dimX; i++) {
            v[i] = pos;
            prevPos = v[max(0, i - 1)];
            thirdPrevPos = v[max(0, i - 3)];
            if (i != 0) nextDir();
            nextPos();
        }
        return v;
    }

}

