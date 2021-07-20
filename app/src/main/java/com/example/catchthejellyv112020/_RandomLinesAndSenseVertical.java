package co.snOmOtiOn.bogdan.catchthejellyv112020;
import java.util.Random;

public class _RandomLinesAndSenseVertical {
    static int dimX = 10, dimY = 6;
    static Coordinates[] v;
    public static Coordinates[] main() {
        v = new Coordinates[dimY * dimX];
        Random rand = new Random();
        int columnNo;
        int sense = 1;
        int idx = 0;
        for (; idx < dimY * dimX;) {
            columnNo = rand.nextInt(dimX);
            sense = rand.nextInt(2);
            if (sense == 0) {
                for (int j = 0; j < dimY && idx < dimY * dimX; j++) {
                    v[idx++] = new Coordinates(j, columnNo);
                }
            } else {
                for (int j = dimY - 1; j > 0 && idx < dimY * dimX; j--) {
                    v[idx++] = new Coordinates(j, columnNo);
                }
            }
        }
        return v;
    }
}

