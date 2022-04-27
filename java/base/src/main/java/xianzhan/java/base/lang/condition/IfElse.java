//package xianzhan.java.base.lang.condition;
//
//import xianzhan.java.base.unsafe.Unsafe;
//
//import java.lang.reflect.Field;
//import java.util.Random;
//
///**
// * if else
// *
// * @author xianzhan
// * @since 2020-09-28
// */
//public class IfElse {
//
//    private static boolean b;
//
//    public static void main(String[] args) throws Exception {
//        b = new Random().nextBoolean();
//
//        printB();
//
//        ifMethod();
//        ifElseMethod();
//        ifElseIfElseMethod();
//    }
//
//    private static void printB() throws NoSuchFieldException {
//        Field bField = IfElse.class.getDeclaredField("b");
//        bField.setAccessible(true);
//        long bAddr = Unsafe.staticFieldOffset(bField);
//        System.out.println(b + " is " + Unsafe.getInt(IfElse.class, bAddr));
//    }
//
//    private static void ifMethod() {
//        if (b) {
//            System.out.println(b);
//        }
//    }
//
//    private static void ifElseMethod() {
//        if (b) {
//            System.out.println(b);
//        } else {
//            System.out.println(b);
//        }
//    }
//
//    private static void ifElseIfElseMethod() {
//        if (b) {
//            System.out.println(b);
//        } else if (b) {
//            System.out.println(b);
//        } else {
//            System.out.println(b);
//        }
//    }
//}
