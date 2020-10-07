package xianzhan.java.base.unsafe;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unsafe test
 *
 * @author xianzhan
 * @see Unsafe
 * @since 2020-10-05
 */
public class UnsafeTest {

    private long address;

    @BeforeEach
    public void allocate() {
        address = Unsafe.allocateMemory(10);
    }

    @AfterEach
    public void free() {
        Unsafe.freeMemory(address);
    }

    @Test
    public void testAddressSize() {
        System.out.println(Unsafe.addressSize());
    }

    @Test
    public void testPageSize() {
        System.out.println(Unsafe.pageSize());
    }

    @Test
    public void testMemory() {
        Unsafe.setMemory(address, 10L, (byte) 6);
        for (int i = 0; i < 10; i++) {
            byte b = Unsafe.getByte(address + i);
            Assertions.assertEquals((byte) 6, b);
        }

        Unsafe.freeMemory(address);
    }

    @Test
    public void testShort() {
        Unsafe.putShort(address, (short) 1000);
        short thousand = Unsafe.getShort(address);
        Assertions.assertEquals((short) 1000, thousand);
    }

    @Test
    public void testChar() {
        Unsafe.putChar(address, 'z');
        char z = Unsafe.getChar(address);
        Assertions.assertEquals('z', z);
    }

    @Test
    public void testInt() {
        Unsafe.putInt(address, 65536);
        int i = Unsafe.getInt(address);
        Assertions.assertEquals(65536, i);
    }

    @Test
    public void testLong() {
        Unsafe.putLong(address, 123456789);
        long l = Unsafe.getLong(address);
        Assertions.assertEquals(123456789, l);
    }

    @Test
    public void testFloat() {
        Unsafe.putFloat(address, 1.23F);
        float f = Unsafe.getFloat(address);
        Assertions.assertEquals(1.23F, f);
    }

    @Test
    public void testDouble() {
        Unsafe.putDouble(address, 1.23);
        double d = Unsafe.getDouble(address);
        Assertions.assertEquals(1.23, d);
    }

    private static class Container {
        private boolean j;
        private char    c;
        private byte    b;
        private short   s;
        private int     i;
        private long    l;
        private float   f;
        private double  d;

        @Override
        public String toString() {
            return "Container{" +
                   "j=" + j +
                   ", c=" + c +
                   ", b=" + b +
                   ", s=" + s +
                   ", i=" + i +
                   ", l=" + l +
                   ", f=" + f +
                   ", d=" + d +
                   '}';
        }
    }

    @Test
    public void testObject() throws ClassNotFoundException, InstantiationException, NoSuchFieldException {
        Class<?> clazz = Class.forName("xianzhan.java.base.unsafe.UnsafeTest$Container");
        Object o = Unsafe.allocateInstance(clazz);
        Assertions.assertNotNull(o);

        long ja = Unsafe.objectFieldOffset(clazz.getDeclaredField("j"));
        Unsafe.putBoolean(o, ja, true);
        Assertions.assertTrue(Unsafe.getBoolean(o, ja));

        long ca = Unsafe.objectFieldOffset(clazz.getDeclaredField("c"));
        Unsafe.putChar(o, ca, 'a');
        Assertions.assertEquals('a', Unsafe.getChar(o, ca));

        long ba = Unsafe.objectFieldOffset(clazz.getDeclaredField("b"));
        Unsafe.putByte(o, ba, (byte) 6);
        Assertions.assertEquals((byte) 6, Unsafe.getByte(o, ba));

        long sa = Unsafe.objectFieldOffset(clazz.getDeclaredField("s"));
        Unsafe.putShort(o, sa, (short) 6);
        Assertions.assertEquals((short) 6, Unsafe.getShort(o, sa));

        long ia = Unsafe.objectFieldOffset(clazz.getDeclaredField("i"));
        Unsafe.putInt(o, ia, 6);
        Assertions.assertEquals(6, Unsafe.getInt(o, ia));

        long la = Unsafe.objectFieldOffset(clazz.getDeclaredField("l"));
        Unsafe.putLong(o, la, 6L);
        Assertions.assertEquals(6, Unsafe.getLong(o, la));

        long fa = Unsafe.objectFieldOffset(clazz.getDeclaredField("f"));
        Unsafe.putFloat(o, fa, 6.0F);
        Assertions.assertEquals(6.0F, Unsafe.getFloat(o, fa));

        long da = Unsafe.objectFieldOffset(clazz.getDeclaredField("d"));
        Unsafe.putDouble(o, da, 6.0);
        Assertions.assertEquals(6.0, Unsafe.getDouble(o, da));

        System.out.println(o);
    }
}
