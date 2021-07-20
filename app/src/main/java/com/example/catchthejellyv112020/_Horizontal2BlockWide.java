package co.snOmOtiOn.bogdan.catchthejellyv112020;
import java.util.Random;

public class _Horizontal2BlockWide {
    static int dimX = 10, dimY = 6;
    static Coordinates[] v;
    public static Coordinates[] main(int putSense) {
        v = new Coordinates[dimY * dimX];
        int idx = 0;
        Random rand = new Random();
        for (; idx < dimY * dimX;) {
            int row = rand.nextInt(5);
            int line = rand.nextInt(dimY);
            line = (line + 2) % dimY;
            int sense = rand.nextInt(5);
            for (int j = dimX / 5 * row; j < dimX / 5 * (row + 1) && idx < dimY * dimX; j++) {
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


