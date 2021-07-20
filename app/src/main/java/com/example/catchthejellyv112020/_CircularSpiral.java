package co.snOmOtiOn.bogdan.catchthejellyv112020;

import java.util.Random;

public class _CircularSpiral {
    static private int min(int n1, int n2) {
        return n1 < n2 ? n1 : n2;
    }

    static Coordinates[] v;

    public static Coordinates[] main() {
        v = new Coordinates[Predictable0.gameRangeY * Predictable0.gameRangeX];
        for (int j = 0; j < 6; j++) {
            for (int i = 0; i < 10; i++) {
                v[10 * j + i] = new Coordinates(j, i);
            }
        }

        int dimX = Predictable0.gameRangeX, dimY = Predictable0.gameRangeY;
        Random rand = new Random();
        Coordinates poz = PositionTranslator(rand.nextInt(2 * dimX + 2 * dimY - 4));// = new Coordinates(5, 1);
        int index = 0;

        if (poz.y == 0) {
            int startOffset = poz.x;
            for (int frame = 0; frame < min(dimX / 2, dimY / 2); frame++) {
                for (int i = startOffset; i < dimX - frame; i++) {
                    v[index++] = new Coordinates(frame, i);
                }
                for (int i = frame + 1; i < dimY - frame; i++) {	// "+ 1" pentru ca altfel colturile s-ar repeta; la fel si la celelalte 2 loop-uri
                    v[index++] = new Coordinates(i, dimX - 1 - frame);
                }
                for (int i = frame + 1; i < dimX - frame; i++) {
                    v[index++] = new Coordinates(dimY - 1 - frame, dimX - i - 1);
                }
                for (int i = frame + 1; i < dimY - 1 - frame; i++) {	// mai e un " - 1" deoarece aici nu trebuie sa revina exact la acelasi bound ca prima oara
                    v[index++] = new Coordinates(dimY - i - 1, frame);
                }
                startOffset = frame + 1;
            }
        }
        else if (poz.x == dimX - 1) {
            int startOffset = poz.y;
            for (int frame = 0; frame < min(dimX / 2, dimY / 2); frame++) {
                for (int i = startOffset; i < dimY - frame; i++) {
                    v[index++] = new Coordinates(i, dimX - 1 - frame);
                }
                for (int i = frame + 1; i < dimX - frame; i++) {
                    v[index++] = new Coordinates(dimY - 1 - frame, dimX - i - 1);
                }
                for (int i = frame + 1; i < dimY - frame; i++) {
                    v[index++] = new Coordinates(dimY - i - 1, frame);
                }
                for (int i = frame + 1; i < dimX - 1 - frame; i++) {
                    v[index++] = new Coordinates(frame, i);
                }
                startOffset = frame + 1;
            }
        }
        else if (poz.y == dimY - 1) {
            int startOffset = dimX - poz.x - 1;
            for (int frame = 0; frame < min(dimX / 2, dimY / 2); frame++) {
                for (int i = startOffset; i < dimX - frame; i++) {
                    v[index++] = new Coordinates(dimY - 1 - frame, dimX - i - 1);
                }
                for (int i = frame + 1; i < dimY - frame; i++) {
                    v[index++] = new Coordinates(dimY - i - 1, frame);
                }
                for (int i = frame + 1; i < dimX - frame; i++) {
                    v[index++] = new Coordinates(frame, i);
                }
                for (int i = frame + 1; i < dimY - 1 - frame; i++) {
                    v[index++] = new Coordinates(i, dimX - 1 - frame);
                }
                startOffset = frame + 1;
            }
        }
        else if (poz.x == 0) {
            int startOffset = dimY - poz.y - 1;
            for (int frame = 0; frame < min(dimX / 2, dimY / 2); frame++) {
                for (int i = startOffset; i < dimY - frame; i++) {
                    v[index++] = new Coordinates(dimY - i - 1, frame);
                }
                for (int i = frame + 1; i < dimX - frame; i++) {
                    v[index++] = new Coordinates(frame, i);
                }
                for (int i = frame + 1; i < dimY - frame; i++) {
                    v[index++] = new Coordinates(i, dimX - 1 - frame);
                }
                for (int i = frame + 1; i < dimX - 1 - frame; i++) {
                    v[index++] = new Coordinates(dimY - 1 - frame, dimX - i - 1);
                }
                startOffset = frame + 1;
            }
        }
        return v;
    }

    static private Coordinates PositionTranslator(int p) {
        if (p < Predictable0.gameRangeX)
            return new Coordinates(0, p);
        p -= Predictable0.gameRangeX; p += 1;
        if (p < Predictable0.gameRangeY)
            return new Coordinates(p, Predictable0.gameRangeX - 1);
        p -= Predictable0.gameRangeY; p += 1;
        if (p < Predictable0.gameRangeX)
            return new Coordinates(Predictable0.gameRangeY - 1, Predictable0.gameRangeX - p - 1);
        p -= Predictable0.gameRangeX; p += 1;
        return new Coordinates(Predictable0.gameRangeY - p - 1, 0);
    }
}