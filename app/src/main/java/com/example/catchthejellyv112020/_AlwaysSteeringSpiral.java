package co.snOmOtiOn.bogdan.catchthejellyv112020;

import java.util.Random;

public class _AlwaysSteeringSpiral {
    static int dimY = 6, dimX = 10;
    static Coordinates[] v;
    static Coordinates pos;
    static Coordinates thirdPrevPos;
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
        return true;
    }

    private static void generatePos() {
        Random rand = new Random();
        var = rand.nextInt(8);
        switch (var) {
            case 0:	// stanga sus -> dreapta
                pos = new Coordinates(0, 0);
                curDir = RIGHT;
                break;
            case 1:	// stanga sus -> jos
                pos = new Coordinates(0, 0);
                curDir = DOWN;
                break;
            case 2: // dreapta sus -> stanga
                pos = new Coordinates(0, dimX - 1);
                curDir = LEFT;
                break;
            case 3:	// dreapta sus -> jos
                pos = new Coordinates(0, dimX - 1);
                curDir = DOWN;
                break;
            case 4:	// stanga jos -> dreapta
                pos = new Coordinates(dimY - 1, 0);
                curDir = RIGHT;
                break;
            case 5:	// stanga jos -> sus
                pos = new Coordinates(dimY - 1, 0);
                curDir = UP;
                break;
            case 6:	// dreapta jos -> stanga
                pos = new Coordinates(dimY - 1, dimX - 1);
                curDir = LEFT;
                break;
            case 7:	// dreapta jos -> sus
                pos = new Coordinates(dimY - 1, dimX - 1);
                curDir = UP;
                break;
        }
    }

    private static void nextDir() {
        if (var == 0 || var == 3 || var == 5 || var == 6) {
            if (curDir == DOWN) {
                curDir = LEFT;
                if (!allowed()) curDir = RIGHT;
            }
            else if (curDir == UP) {
                curDir = RIGHT;
                if (!allowed()) curDir = LEFT;
            }
            else if (curDir == RIGHT) {
                curDir = DOWN;
                if (!allowed()) curDir = UP;
            }
            else if (curDir == LEFT) {
                curDir = UP;
                if (!allowed()) curDir = DOWN;
            }
        } else if (var == 1 || var == 2 || var == 4 || var == 7) {
            if (curDir == DOWN) {
                curDir = RIGHT;
                if (!allowed()) curDir = LEFT;
            }
            else if (curDir == UP) {
                curDir = LEFT;
                if (!allowed()) curDir = RIGHT;
            }
            else if (curDir == RIGHT) {
                curDir = UP;
                if (!allowed()) curDir = DOWN;
            }
            else if (curDir == LEFT) {
                curDir = DOWN;
                if (!allowed()) curDir = UP;
            }
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
            thirdPrevPos = v[max(0, i - 3)];
            if (i != 0) nextDir();
            nextPos();
        }
        return v;
    }

}

