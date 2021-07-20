package co.snOmOtiOn.bogdan.catchthejellyv112020;
import java.util.Random;

public class _RandomLinesAndSense {
    static int dimX = 10, dimY = 6;
    static Coordinates[] v;
    public static Coordinates[] main() {
        v = new Coordinates[dimY * dimX];
        Random rand = new Random();
        int lineNo, columnNo;
        int sense = 1;
        int direction = 1;
        int idx = 0;
        for (; idx < dimY * dimX;) {
            lineNo = rand.nextInt(dimY);
            columnNo = rand.nextInt(dimX);
            sense = rand.nextInt(2);
            direction = rand.nextInt(2);
            if (direction == 0) {
                if (sense == 0) {
                    for (int j = 0; j < dimX && idx < dimX * dimY; j++) {
                        v[idx++] = new Coordinates(lineNo, j);
                    }
                } else {
                    for (int j = dimX - 1; j >= 0 && idx < dimX * dimY; j--) {
                        v[idx++] = new Coordinates(lineNo, j);
                    }
                }
            } else {
                if (sense == 0) {
                    for (int i = 0; i < dimY && idx < dimX * dimY; i++) {
                        v[idx++] = new Coordinates(i, columnNo);
                    }
                } else {
                    for (int i = dimY - 1; i >= 0 && idx < dimX * dimY; i--) {
                        v[idx++] = new Coordinates(i, columnNo);
                    }
                }
            }
        }
        return v;
    }
}
