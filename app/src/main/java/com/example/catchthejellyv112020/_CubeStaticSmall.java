package co.snOmOtiOn.bogdan.catchthejellyv112020;
import java.util.Random;

public class _CubeStaticSmall {
    static int dimX = 10, dimY = 6;
    static Coordinates[] vModulo;
    static Coordinates[] v;
    public static Coordinates[] main() {
        vModulo = new Coordinates[] {
                new Coordinates(dimY / 2 - 1, dimX / 2 - 1),
                new Coordinates(dimY / 2 - 1, dimX / 2),
                new Coordinates(dimY / 2, dimX / 2),
                new Coordinates(dimY / 2, dimX / 2 - 1)};
        v = new Coordinates[dimX * dimY];
        Random rand = new Random();
        int offset = rand.nextInt(vModulo.length);
        int sense = rand.nextInt(2);
        if (sense == 0)
            sense = -1;
        for (int i = 0; i < v.length; i++) {
            v[i] = vModulo[((offset + sense * i) % vModulo.length + vModulo.length) % vModulo.length];
        }
        return v;
    }

}


