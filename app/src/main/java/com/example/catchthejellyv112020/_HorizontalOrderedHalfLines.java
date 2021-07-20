package co.snOmOtiOn.bogdan.catchthejellyv112020;

public class _HorizontalOrderedHalfLines {
    static int dimX = 10, dimY = 6;
    static Coordinates[] v;
    public static Coordinates[] main() {
        v = new Coordinates[dimY * dimX];
        int idx = 0;
        for (int i = 0; i < dimY; i++) {
            for (int j = 0; j < dimX / 2; j++) {
                v[idx++] = new Coordinates(i, j);
            }
        }
        for (int i = 0; i < dimY; i++) {
            for (int j = dimX / 2; j < dimX; j++) {
                v[idx++] = new Coordinates(i, j);
            }
        }
        return v;
    }
}

