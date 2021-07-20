package co.snOmOtiOn.bogdan.catchthejellyv112020;

import java.util.Random;

public class _BasicSpiralVertical {
    static int dimY = 10, dimX = 6;
    static Coordinates[] v;
    static int verticalSense = 0; // down; 1 up
    static int horizontalSense = 0; // right; 1 left
    static Coordinates pos;
    static int difX, difY;
    static int x, y;
    static int index = 0;
    private static void initialization() {
        Random rand = new Random();
        verticalSense = rand.nextInt(2);
        horizontalSense = rand.nextInt(2);
        if (verticalSense == 0) {y = 0; difY = 1;} else {y = dimY - 1; difY = -1;}
        if (horizontalSense == 0) {x = 0; difX = 1;} else {x = dimX - 1; difX = -1;}
    }

    private static boolean condY() {
        if (verticalSense == 0) {
            return y < dimY;
        } else {
            return y >= 0;
        }
    }

    public static Coordinates[] main() {
        v = new Coordinates[dimY * dimX];
        pos = new Coordinates(0, 0);
        index = 0;
        initialization();
        for (; condY(); y += difY) {
            if (difX == 1)
                for (; x < dimX; x += difX) {
                    v[index++] = new Coordinates(x, y);
                }
            else
                for (; x >= 0; x += difX) {
                    v[index++] = new Coordinates(x, y);
                }
            if (difX == 1) x = dimX - 1;
            else x = 0;
            difX *= -1;
        }
        return v;
    }
}


