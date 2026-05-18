package ru.otus.danilchenko.algorithms.lesson22.hll;


import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.IArray;
import ru.otus.danilchenko.algorithms.lesson4.dynamic_arrays.SingleArray;

import java.nio.charset.StandardCharsets;

public final class MurmurHash3 {

    private static final long C = -4132994306676758123L;
    private static final int R = 47;

    private MurmurHash3() {
        // утилитарный класс
    }

    public static long hash64(String value) {
        byte[] raw = value.getBytes(StandardCharsets.UTF_8);
        IArray<Byte> data = bytesToIArray(raw);
        return hash64(data, 0, data.size(), C);
    }

    public static long hash64(long value) {
        IArray<Byte> data = new SingleArray<>(new Byte[8]);
        for (int i = 0; i < 8; i++) {
            data.set((byte) 0, i);
        }
        for (int i = 7; i >= 0; i--) {
            data.set((byte) (value & 0xFFL), i);
            value >>>= 8;
        }
        return hash64(data, 0, data.size(), C);
    }

    public static long hash64(IArray<Byte> data, int offset, int len, long seed) {
        long h = seed ^ (len & 0xffffffffL);

        int remainder = len & 7;
        int alignedLen = len - remainder;
        int i = offset;
        int end = offset + alignedLen;

        while (i < end) {
            long k = (data.get(i) & 0xffL)
                    | ((data.get(i + 1) & 0xffL) << 8)
                    | ((data.get(i + 2) & 0xffL) << 16)
                    | ((data.get(i + 3) & 0xffL) << 24)
                    | ((data.get(i + 4) & 0xffL) << 32)
                    | ((data.get(i + 5) & 0xffL) << 40)
                    | ((data.get(i + 6) & 0xffL) << 48)
                    | ((data.get(i + 7) & 0xffL) << 56);

            k *= C;
            k ^= k >>> R;
            k *= C;

            h ^= k;
            h *= C;
            i += 8;
        }

        if (remainder >= 7) h ^= (data.get(i + 6) & 0xffL) << 48;
        if (remainder >= 6) h ^= (data.get(i + 5) & 0xffL) << 40;
        if (remainder >= 5) h ^= (data.get(i + 4) & 0xffL) << 32;
        if (remainder >= 4) h ^= (data.get(i + 3) & 0xffL) << 24;
        if (remainder >= 3) h ^= (data.get(i + 2) & 0xffL) << 16;
        if (remainder >= 2) h ^= (data.get(i + 1) & 0xffL) << 8;
        if (remainder >= 1) {
            h ^= data.get(i) & 0xffL;
            h *= C;
        }

        h ^= h >>> R;
        h *= C;
        h ^= h >>> R;

        return h;
    }

    private static IArray<Byte> bytesToIArray(byte[] raw) {
        IArray<Byte> result = new SingleArray<>(new Byte[raw.length]);
        for (int i = 0; i < raw.length; i++) {
            result.set(raw[i], i);
        }
        return result;
    }
}
