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
}
