package co.snOmOtiOn.bogdan.catchthejellyv112020;
import java.util.Random;

public class _RandomLinesVertical {
    static int dimX = 10, dimY = 6;
    static Coordinates[] v;
    public static Coordinates[] main() {
        v = new Coordinates[dimY * dimX];
        Random rand = new Random();
        int columnNo;
        for (int x = 0; x < dimX; x++) {
            columnNo = rand.nextInt(dimX);
            for (int y = 0; y < dimY; y++) {
                v[x * dimY + y] = new Coordinates(y, columnNo);
            }
        }
        return v;
    }
}

