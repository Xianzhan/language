package xianzhan.java.base.beans;

import java.beans.IntrospectionException;
import java.beans.Introspector;

/**
 * @author xianzhan
 * @since 2020-10-05
 */
public class BeanInfo {

    private static class Person {
        private String name;
        private int    age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    public static void main(String[] args) throws IntrospectionException {
        var beanInfo = Introspector.getBeanInfo(Person.class, Object.class);
        for (var info : beanInfo.getPropertyDescriptors()) {
            System.out.println(info);
        }
    }
}
