package co.snOmOtiOn.bogdan.catchthejellyv112020;

public class _OrderRows {
    static Coordinates[] v;
    public static Coordinates[] main() {
        v = new Coordinates[Predictable0.gameRangeY * Predictable0.gameRangeX];
        for (int j = 0; j < 6; j++) {
            for (int i = 0; i < 10; i++) {
                v[10 * j + i] = new Coordinates(j, i);
            }
        }
        return v;
    }
}
