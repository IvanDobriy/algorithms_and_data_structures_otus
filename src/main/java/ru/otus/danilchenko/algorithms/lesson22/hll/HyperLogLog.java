package ru.otus.danilchenko.algorithms.lesson22.hll;


import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.IArray;
import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.SingleArray;

public class HyperLogLog {
    private final int precision;
    private final int m;
    private final IArray<Byte> registers;
    private final double alphaMM;

    public HyperLogLog() {
        this(14);
    }

    public HyperLogLog(int precision) {
        if (precision < 4 || precision > 16) {
            throw new IllegalArgumentException("precision must be between 4 and 16");
        }
        this.precision = precision;
        this.m = 1 << precision;
        this.registers = new SingleArray<>(new Byte[m]);
        for (int i = 0; i < m; i++) {
            registers.set((byte) 0, i);
        }
        this.alphaMM = computeAlphaMM(m);
    }

    public int getPrecision() {
        return precision;
    }

    public IArray<Byte> registersCopy() {
        IArray<Byte> copy = new SingleArray<>(new Byte[m]);
        for (int i = 0; i < m; i++) {
            copy.set((byte) 0, i);
        }
        for (int i = 0; i < m; i++) {
            copy.set(registers.get(i), i);
        }
        return copy;
    }

    public void add(String value) {
        addHash(MurmurHash3.hash64(value));
    }

    public void add(long value) {
        addHash(MurmurHash3.hash64(value));
    }

    public void addHash(long hash) {
        int idx = (int) ((hash >>> (64 - precision)) & (m - 1));
        int rank = rank(hash, precision);
        if (rank > (registers.get(idx) & 0xFF)) {
            registers.set((byte) rank, idx);
        }
    }

    public long count() {
        double z = harmonicMean();
        double e = alphaMM * z;

        if (e <= 2.5 * m) {
            int v = 0;
            for (int i = 0; i < m; i++) {
                if (registers.get(i) == 0) v++;
            }
            if (v != 0) {
                e = m * Math.log((double) m / v);
            }
        }

        return Math.max(0, (long) e);
    }

    public int size() {
        return 4 + m;
    }

    private double harmonicMean() {
        double sum = 0.0;
        for (int i = 0; i < m; i++) {
            sum += 1.0 / (1L << (registers.get(i) & 0xFF));
        }
        return 1.0 / sum;
    }

    private static double computeAlphaMM(int m) {
        double alpha;
        switch (m) {
            case 16:
                alpha = 0.673;
                break;
            case 32:
                alpha = 0.697;
                break;
            case 64:
                alpha = 0.709;
                break;
            default:
                alpha = 0.7213 / (1 + 1.079 / m);
                break;
        }
        return alpha * m * m;
    }

    private static int rank(long hash, int precision) {
        long x = hash << precision;
        int rank = 1;
        while ((x & Long.MIN_VALUE) == 0 && rank <= 64 - precision) {
            rank++;
            x <<= 1;
        }
        return rank;
    }
}
