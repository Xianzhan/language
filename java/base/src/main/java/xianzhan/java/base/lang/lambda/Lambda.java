package xianzhan.java.base.lang.lambda;

/**
 * Java 8 Lambda 表达式
 *
 * @author xianzhan
 * @since 2020-11-14
 */
public class Lambda {

    /**
     * <pre>
     *  0 invokedynamic #7 <run, BootstrapMethods #0>
     *  5 astore_1
     *  6 aload_1
     *  7 invokeinterface #11 <java/lang/Runnable.run> count 1
     * 12 return
     * </pre>
     *
     * invokedynamic 字节码是 Java 7 引入,
     * 主要是用于基于 JVM 的动态语言实现,
     * Java 8 根据此字节码实现 Lambda 表达式
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        Runnable run = () -> System.out.println("run");
        run.run();
    }
}
