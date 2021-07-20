package co.snOmOtiOn.bogdan.catchthejellyv112020;
import java.util.Random;

public class _RandomLinesAndSenseHorizontal {
    static int dimX = 10, dimY = 6;
    static Coordinates[] v;
    public static Coordinates[] main() {
        v = new Coordinates[dimY * dimX];
        Random rand = new Random();
        int lineNo;
        int sense = 1;
        int idx = 0;
        for (; idx < dimY * dimX;) {
            lineNo = rand.nextInt(dimY);
            sense = rand.nextInt(2);
            if (sense == 0) {
                for (int j = 0; j < dimX && idx < dimY * dimX; j++) {
                    v[idx++] = new Coordinates(lineNo, j);
                }
            } else {
                for (int j = dimX - 1; j > 0 && idx < dimY * dimX; j--) {
                    v[idx++] = new Coordinates(lineNo, j);
                }
            }
        }
        return v;
    }
}
