package co.snOmOtiOn.bogdan.catchthejellyv112020;
import java.util.Random;

public class _RandomLinesHorizontal {
    static int dimX = 10, dimY = 6;
    static Coordinates[] v;
    public static Coordinates[] main() {
        v = new Coordinates[dimY * dimX];
        Random rand = new Random();
        int lineNo;
        for (int i = 0; i < dimY; i++) {
            lineNo = rand.nextInt(dimY);
            for (int j = 0; j < dimX; j++) {
                v[i * dimX + j] = new Coordinates(lineNo, j);
            }
        }
        return v;
    }
}
