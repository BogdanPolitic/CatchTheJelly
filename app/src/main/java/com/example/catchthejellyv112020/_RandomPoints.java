package co.snOmOtiOn.bogdan.catchthejellyv112020;
import java.util.Random;

public class _RandomPoints {
    static int dimX = 10, dimY = 6;
    static Coordinates[] v;
    public static Coordinates[] main(int putSense) {
        v = new Coordinates[dimY * dimX];
        int idx = 0;
        Random rand = new Random();
        for (; idx < dimY * dimX;) {
            int row = rand.nextInt(dimX);
            int line = rand.nextInt(dimY);
            line = (line + 2) % dimY;
            int sense = rand.nextInt(5);
            for (int j = row; j < row + 1 && idx < dimY * dimX; j++) {
                if (putSense == 0) v[idx++] = new Coordinates(line, j);
                else {
                    if (sense == 0) v[idx++] = new Coordinates(line, j);
                    else v[idx++] = new Coordinates(line, dimX - 1 - j);
                }
            }
        }
        return v;
    }
}


