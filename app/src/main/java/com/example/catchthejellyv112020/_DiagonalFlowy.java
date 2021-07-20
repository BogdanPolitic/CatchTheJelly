package co.snOmOtiOn.bogdan.catchthejellyv112020;

import java.util.Random;

public class _DiagonalFlowy {
    static int dimX = Predictable0.gameRangeX, dimY = Predictable0.gameRangeY;
    static int NV = 0, NE = 1, SE = 2, SV = 3;
    static Coordinates poz;
    static Coordinates[] v;
    public static Coordinates[] main() {
        v = new Coordinates[dimX * dimY];
        Random rand = new Random();
        poz = new Coordinates(rand.nextInt(dimY - 2) + 1, rand.nextInt(dimX - 2) + 1);	// nu se va afla niciodata pe o margine!

        int direction = rand.nextInt(4);
        for (int i = 0; i < dimX * dimY; i++) {
            v[i] = poz;
            switch (direction) {
                case 0:
                    poz = new Coordinates(poz.y - 1, poz.x - 1);
                    break;
                case 1:
                    poz = new Coordinates(poz.y - 1, poz.x + 1);
                    break;
                case 2:
                    poz = new Coordinates(poz.y + 1, poz.x + 1);
                    break;
                case 3:
                    poz = new Coordinates(poz.y + 1, poz.x - 1);
                    break;
                default:
                    break;
            }
            if (poz.x == 0 && poz.y == 0) {
                if (direction == NV)
                    direction = SE;
            } else if (poz.x == 0 && poz.y == dimY - 1) {
                if (direction == SV)
                    direction = NE;
            } else if (poz.x == dimX - 1 && poz.y == 0) {
                if (direction == NE)
                    direction = SV;
            } else if (poz.x == dimX - 1 && poz.y == dimY - 1) {
                if (direction == SE)
                    direction = NV;
            } else if (poz.x == 0) {
                if (direction == NV)
                    direction = NE;
                if (direction == SV)
                    direction = SE;
            } else if (poz.x == dimX - 1) {
                if (direction == NE)
                    direction = NV;
                if (direction == SE)
                    direction = SV;
            } else if (poz.y == 0) {
                if (direction == NV)
                    direction = SV;
                if (direction == NE)
                    direction = SE;
            } else if (poz.y == dimY - 1) {
                if (direction == SV)
                    direction = NV;
                if (direction == SE)
                    direction = NE;
            }
        }

        return v;
    }
}

