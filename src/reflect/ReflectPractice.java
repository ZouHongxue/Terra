package reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author: coffee
 */
public class ReflectPractice {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> person = Class.forName("reflect.Person");
        Person person1 = (Person) person.newInstance();

        Class<?> personClass = Person.class;
        Person person2 = (Person) personClass.newInstance();

        Constructor<?> constructor = personClass.getConstructor();
        Person person3 = (Person) constructor.newInstance();

        //验证方法
        System.out.println(personClass.isInstance(person1));
        System.out.println(person2 instanceof Person);
        System.out.println(person3 instanceof Person);

        Method[] methods = person.getMethods();
        System.out.println(methods.length);
        Method[] declaredMethods = person.getDeclaredMethods();
        System.out.println(declaredMethods.length);
        printMethods(methods);
        printMethods(declaredMethods);

        Field[] fields = person.getFields();
        System.out.println(fields.length);
        Field[] declaredFields = person.getDeclaredFields();
        System.out.println(declaredFields.length);

        Method getMethod = person.getMethod("setName", String.class);
        getMethod.invoke(person1, "str");
        System.out.println(person1.getName());
    }

    private static void printMethods(Method[] methods) {
        for (Method method : methods) {
            System.out.println(method.getName() + "\t" + method.getAnnotatedReturnType());
            Annotation[] annotations = method.getAnnotations();
            printAnnotations(annotations);
        }
    }

    private static void printAnnotations(Annotation[] annotations) {
        for (Annotation annotation : annotations) {
            System.out.println(annotation.annotationType());
        }
    }
}
