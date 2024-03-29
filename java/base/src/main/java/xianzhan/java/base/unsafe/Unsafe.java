//package xianzhan.java.base.unsafe;
//
//import java.lang.invoke.MethodHandles;
//import java.lang.reflect.Field;
//
///**
// * 用于访问内存
// *
// * @author xianzhan
// * @since 2020-10-05
// */
//public final class Unsafe {
//
//    private static final sun.misc.Unsafe UNSAFE;
//
//    static {
//        try {
//            Class<sun.misc.Unsafe> clazz = sun.misc.Unsafe.class;
//            Field theUnsafe = clazz.getDeclaredField("theUnsafe");
//            theUnsafe.setAccessible(true);
//            UNSAFE = (sun.misc.Unsafe) theUnsafe.get(null);
//        } catch (IllegalAccessException | NoSuchFieldException e) {
//            e.printStackTrace();
//            throw new Error("无法初始化 Unsafe");
//        }
//    }
//
//    // ------ 内存 ------
//
//    /**
//     * 报告通过 putAddress 存储的本机指针的字节大小。
//     * 这个值不是 4 就是 8。
//     * 请注意，其他基本类型(存储在本机内存块中)的大小完全由它们的信息内容决定。
//     *
//     * @return 本机指针的字节大小
//     * @implNote src/hotspot/share/classfile/javaClasses.cpp
//     * @implNote int _address_size = sizeof(void *)
//     * @see #putAddress(long, long)
//     */
//    public static int addressSize() {
//        return UNSAFE.addressSize();
//    }
//
//    /**
//     * 报告本机内存页的字节大小。这个值总是2的幂。
//     *
//     * @return 本机内存页的字节大小
//     */
//    public static int pageSize() {
//        return UNSAFE.pageSize();
//    }
//
//    /**
//     * 分配 bytes 字节大小的 "堆外内存", 返回起始地址的偏移量
//     *
//     * @param bytes 字节
//     * @return 偏移量
//     * @see #reallocateMemory(long, long)
//     * @see #freeMemory(long)
//     */
//    public static long allocateMemory(long bytes) {
//        return UNSAFE.allocateMemory(bytes);
//    }
//
//    /**
//     * 重新给 address 起始地址的内存分配长度为 bytes 字节大小的内存, 返回新的内存地址偏移量
//     *
//     * @param address 起始地址
//     * @param bytes   内存大小
//     * @return 新的内存起始地址偏移量
//     * @see #allocateMemory(long)
//     * @see #freeMemory(long)
//     */
//    public static long reallocateMemory(long address, long bytes) {
//        return UNSAFE.reallocateMemory(address, bytes);
//    }
//
//    /**
//     * 释放起始地址为 address 的内存
//     *
//     * @param address 起始地址
//     * @see #allocateMemory(long)
//     * @see #reallocateMemory(long, long)
//     */
//    public static void freeMemory(long address) {
//        UNSAFE.freeMemory(address);
//    }
//
//    /**
//     * 在给定的内存块中设置值
//     *
//     * @param address 地址
//     * @param bytes   多少字节被设定
//     * @param value   值
//     */
//    public static void setMemory(long address, long bytes, byte value) {
//        UNSAFE.setMemory(address, bytes, value);
//    }
//
//    /**
//     * 内存拷贝
//     *
//     * @param srcAddress  源地址
//     * @param destAddress 目标地址
//     * @param bytes       多少字节被拷贝
//     */
//    public static void copyMemory(long srcAddress, long destAddress, long bytes) {
//        UNSAFE.copyMemory(srcAddress, destAddress, bytes);
//    }
//
//    /**
//     * 内存屏障，禁止 load 操作重排序。<br>
//     * 屏障前的 load 操作不能被重排序到屏障后，屏障后的 load 操作不能被重排序到屏障前
//     *
//     * @since 1.8
//     */
//    public static void loadFence() {
//        UNSAFE.loadFence();
//    }
//
//    /**
//     * 内存屏障，禁止 store 操作重排序。<br>
//     * 屏障前的 store 操作不能被重排序到屏障后，屏障后的 store 操作不能被重排序到屏障前
//     *
//     * @since 1.8
//     */
//    public static void storeFence() {
//        UNSAFE.storeFence();
//    }
//
//    /**
//     * 内存屏障，禁止 load、store 操作重排序
//     *
//     * @since 1.8
//     */
//    public static void fullFence() {
//        UNSAFE.fullFence();
//    }
//
//    /**
//     * 在 address 起始地址上保存 b 数据
//     *
//     * @param address 起始地址
//     * @param b       数据
//     * @see #getByte(long)
//     */
//    public static void putByte(long address, byte b) {
//        UNSAFE.putByte(address, b);
//    }
//
//    /**
//     * 在 address 地址上获取一个字节内容
//     *
//     * @param address 起始地址
//     * @return 字节内容
//     * @see #putByte(long, byte)
//     */
//    public static byte getByte(long address) {
//        return UNSAFE.getByte(address);
//    }
//
//    public static void putShort(long address, short s) {
//        UNSAFE.putShort(address, s);
//    }
//
//    public static short getShort(long address) {
//        return UNSAFE.getShort(address);
//    }
//
//    public static void putChar(long address, char c) {
//        UNSAFE.putChar(address, c);
//    }
//
//    public static char getChar(long address) {
//        return UNSAFE.getChar(address);
//    }
//
//    public static void putInt(long address, int i) {
//        UNSAFE.putInt(address, i);
//    }
//
//    public static int getInt(long address) {
//        return UNSAFE.getInt(address);
//    }
//
//    public static void putLong(long address, long l) {
//        UNSAFE.putLong(address, l);
//    }
//
//    public static long getLong(long address) {
//        return UNSAFE.getLong(address);
//    }
//
//    public static void putFloat(long address, float f) {
//        UNSAFE.putFloat(address, f);
//    }
//
//    public static float getFloat(long address) {
//        return UNSAFE.getFloat(address);
//    }
//
//    public static void putDouble(long address, double d) {
//        UNSAFE.putDouble(address, d);
//    }
//
//    public static double getDouble(long address) {
//        return UNSAFE.getDouble(address);
//    }
//
//    /**
//     * 从起始地址上获取本地的内存地址
//     *
//     * @param address 起始地址
//     * @return 本地的内存地址
//     */
//    public static long getAddress(long address) {
//        return UNSAFE.getAddress(address);
//    }
//
//    /**
//     * 将本机指针存储到给定的内存地址中。
//     *
//     * @param address 指定的内存地址
//     * @param x       本机指针
//     */
//    public static void putAddress(long address, long x) {
//        UNSAFE.putAddress(address, x);
//    }
//
//    // ------ 对象 ------
//
//    /**
//     * 该方法在 Java 11 移除
//     *
//     * @param bytes 序列化字节
//     * @return Class
//     * @throws IllegalAccessException 非法访问异常
//     */
//    public static Class<?> defineClass(byte[] bytes) throws IllegalAccessException {
//        return MethodHandles.lookup().defineClass(bytes);
//    }
//
//    /**
//     * 绕过构造方法、初始化代码来创建对象
//     *
//     * @param cls 类
//     * @return 实例
//     * @throws InstantiationException 实例化异常
//     */
//    public static Object allocateInstance(Class<?> cls) throws InstantiationException {
//        return UNSAFE.allocateInstance(cls);
//    }
//
//    /**
//     * 从给定的 Java 变量中获取一个引用值。
//     *
//     * @param o      从该对象获取
//     * @param offset 偏移地址
//     * @return o 对象的偏移地址上的对象
//     */
//    public static Object getObject(Object o, long offset) {
//        return UNSAFE.getObject(o, offset);
//    }
//
//    public static void putObject(Object o, long offset, Object x) {
//        UNSAFE.putObject(o, offset, x);
//    }
//
//    /**
//     * 从对象的指定偏移量处获取变量的引用, 使用 volatile 的加载语义
//     *
//     * @param o      对象
//     * @param offset 偏移量
//     * @return 引用
//     */
//    public static Object getObjectVolatile(Object o, long offset) {
//        return UNSAFE.getObjectVolatile(o, offset);
//    }
//
//    /**
//     * 存储变量的引用到对象的指定偏移量, 使用 volatile 的存储语义
//     *
//     * @param o      对象
//     * @param offset 偏移量
//     * @param x      被存储对象
//     */
//    public static void putObjectVolatile(Object o, long offset, Object x) {
//        UNSAFE.putObjectVolatile(o, offset, x);
//    }
//
//    /**
//     * 有序、延迟版本的putObjectVolatile方法，不保证值的改变被其他线程立即看到。<br>
//     * 只有在field被volatile修饰符修饰时有效
//     *
//     * @param o      对象
//     * @param offset 偏移量
//     * @param x      被存储的对象
//     */
//    public static void putOrderedObject(Object o, long offset, Object x) {
//        UNSAFE.putOrderedObject(o, offset, x);
//    }
//
//    public static boolean getBoolean(Object o, long offset) {
//        return UNSAFE.getBoolean(o, offset);
//    }
//
//    public static void putBoolean(Object o, long offset, boolean x) {
//        UNSAFE.putBoolean(o, offset, x);
//    }
//
//    public static byte getByte(Object o, long offset) {
//        return UNSAFE.getByte(o, offset);
//    }
//
//    public static void putByte(Object o, long offset, byte x) {
//        UNSAFE.putByte(o, offset, x);
//    }
//
//    public static short getShort(Object o, long offset) {
//        return UNSAFE.getShort(o, offset);
//    }
//
//    public static void putShort(Object o, long offset, short x) {
//        UNSAFE.putShort(o, offset, x);
//    }
//
//    public static char getChar(Object o, long offset) {
//        return UNSAFE.getChar(o, offset);
//    }
//
//    public static void putChar(Object o, long offset, char x) {
//        UNSAFE.putChar(o, offset, x);
//    }
//
//    public static int getInt(Object o, long offset) {
//        return UNSAFE.getInt(o, offset);
//    }
//
//    public static void putInt(Object o, long offset, int x) {
//        UNSAFE.putInt(o, offset, x);
//    }
//
//    public static long getLong(Object o, long offset) {
//        return UNSAFE.getLong(o, offset);
//    }
//
//    public static void putLong(Object o, long offset, long x) {
//        UNSAFE.putLong(o, offset, x);
//    }
//
//    public static float getFloat(Object o, long offset) {
//        return UNSAFE.getFloat(o, offset);
//    }
//
//    public static void putFloat(Object o, long offset, float x) {
//        UNSAFE.putFloat(o, offset, x);
//    }
//
//    public static double getDouble(Object o, long offset) {
//        return UNSAFE.getDouble(o, offset);
//    }
//
//    public static void putDouble(Object o, long offset, double d) {
//        UNSAFE.putDouble(o, offset, d);
//    }
//
//    /**
//     * 在给定的内存块中设值
//     *
//     * @param o      对象, 地址
//     * @param offset 偏移量
//     * @param bytes  字节数
//     * @param value  值
//     * @since 1.7
//     */
//    public static void setMemory(Object o, long offset, long bytes, byte value) {
//        UNSAFE.setMemory(o, offset, bytes, value);
//    }
//
//    public static void copyMemory(Object srcBase, long srcOffset,
//                                  Object destBase, long destOffset,
//                                  long bytes) {
//        UNSAFE.copyMemory(srcBase, srcOffset, destBase, destOffset, bytes);
//    }
//
//    /**
//     * 返回指定静态 field 的内存地址偏移量,在这个类的其他方法中这个值只是被用作一个访问<br>
//     * 特定field的一个方式。这个值对于 给定的field是唯一的，并且后续对该方法的调用都应该<br>
//     * 返回相同的值。
//     *
//     * @param f 需要返回偏移量的 field
//     * @return 指定field的偏移量
//     * @see #getObject(Object, long)
//     * @see #getInt(long)
//     */
//    public static long staticFieldOffset(Field f) {
//        return UNSAFE.staticFieldOffset(f);
//    }
//
//    /**
//     * 获取一个静态类中给定字段的对象指针
//     *
//     * @param f 字段
//     * @return 指针
//     */
//    public static Object staticFieldBase(Field f) {
//        return UNSAFE.staticFieldBase(f);
//    }
//
//    public static long objectFieldOffset(Field f) {
//        return UNSAFE.objectFieldOffset(f);
//    }
//
//    /**
//     * 判断是否需要初始化一个类, 通常需要使用在获取一个类的静态属性的时候, <br>
//     * 因为一个类如果没有初始化, 它的静态属性也不会初始化.
//     *
//     * @param c 初始化类
//     * @return 当 ensureClassInitialized 方法不生效的时候才返回 false
//     * @see #ensureClassInitialized(Class)
//     */
//    public static boolean shouldBeInitialized(Class<?> c) {
//        try {
//            ensureClassInitialized(c);
//            return true;
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    /**
//     * 检测给定的类是否已经初始化.<br>
//     * 通常需要使用在获取一个类的静态属性的时候(因为一个类如果没初始化，它的静态属性也不会初始化)。
//     *
//     * @param c 类
//     */
//    public static void ensureClassInitialized(Class<?> c) throws IllegalAccessException {
//        MethodHandles.lookup().ensureInitialized(c);
//    }
//
//    /**
//     * 定义一个匿名类
//     */
//    public static Class<?> defineAnonymousClass(Class<?> hostClass, byte[] data, Object[] cpPatches) {
//        return UNSAFE.defineAnonymousClass(hostClass, data, cpPatches);
//    }
//
//    // ------ 数组 ------
//
//    /**
//     * 返回数组中第一个元素的偏移地址
//     *
//     * @param arrayClass 数组类型
//     * @return 第一个元素的偏移地址
//     */
//    public static int arrayBaseOffset(Class<?> arrayClass) {
//        return UNSAFE.arrayBaseOffset(arrayClass);
//    }
//
//    /**
//     * 返回数组中一个元素占用的大小
//     *
//     * @param arrayClass 数组类型
//     * @return 一个元素占用的大小
//     */
//    public static int arrayIndexScale(Class<?> arrayClass) {
//        return UNSAFE.arrayIndexScale(arrayClass);
//    }
//
//    // ------ 线程 ------
//
//    /**
//     * 线程挂起进入 TIMED_WAITING, 线程将一直阻塞直到超时或者中断等条件出现
//     *
//     * <pre>
//     *     long start = System.currentTimeMillis();
//     *     park(true, start + 3000L);
//     *     long end = System.currentTimeMillis();
//     *     // or
//     *     long start = System.currentTimeMillis();
//     *     park(false, 3000000000L);
//     *     long end = System.currentTimeMillis();
//     *
//     *     System.out.println(end - start);
//     * </pre>
//     *
//     * @param isAbsolute true 时 time 的单位为毫秒秒, false 时 time 的单位为纳秒
//     * @param time       时间
//     * @see java.util.concurrent.locks.LockSupport
//     */
//    public static void park(boolean isAbsolute, long time) {
//        UNSAFE.park(isAbsolute, time);
//    }
//
//    /**
//     * 最好不要在调用 park 前对当前线程调用 unpark<br>
//     * 多次调用 unpark 的效果和调用一次 unpark 的效果一样
//     *
//     * @param thread 线程
//     * @see #park(boolean, long)
//     */
//    public static void unpark(Object thread) {
//        UNSAFE.unpark(thread);
//    }
//
//    // ------ CAS ------
//
//    /**
//     * 比较 o 的 offset 处内存位置的值和期望的值, 如果相同则更新. <br>
//     * 此更新是不可中断的.<br>
//     * CAS 操作有 3 个操作数，内存值 M，预期值 E，新值 U，如果 M == E，则将内存值修改为 B，否则啥都不做。
//     *
//     * @param o        需要更新的对象
//     * @param offset   o 中整型 field 的偏移量
//     * @param expected 希望 field 中存在的值
//     * @param x        如果期待值 expect 与 field 的当前值相同, 设置 field 的值为这个新值
//     * @return 如果 field 的值被更改返回 true
//     */
//    public static boolean compareAndSwapInt(Object o, long offset, int expected, int x) {
//        return UNSAFE.compareAndSwapInt(o, offset, expected, x);
//    }
//
//    public static boolean compareAndSwapLong(Object o, long offset, long expected, long x) {
//        return UNSAFE.compareAndSwapLong(o, offset, expected, x);
//    }
//
//    public static boolean compareAndSwapObject(Object o, long offset, Object expected, Object x) {
//        return UNSAFE.compareAndSwapObject(o, offset, expected, x);
//    }
//}
