package xianzhan.java.base.lang;

/**
 * @author xianzhan
 * @since 2020-09-19
 */
public class Str {

    public static void main(String[] args) {
        String str = "这是字符串, 编译时放在 .class 文件的常量池里, 加载到虚拟机后放在运行时常量池里";
        System.out.println(str);

        str = new String("这也是字符串, 创建在堆上");
        System.out.println(str);

        // @release 15 https://openjdk.java.net/jeps/378
        str = """
                我是文本块, 
                可以换行, 相关符号
                """;
        System.out.println(str);
    }
}
