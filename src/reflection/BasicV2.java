package reflection;

import reflection.data.BasicData;

import java.lang.reflect.Modifier;

public class BasicV2 {
    public static void main(String[] args) {
        Class<BasicData> aClass = BasicData.class;
        System.out.println("aClass.getName() = " + aClass.getName());
        System.out.println("aClass.getSimpleName() = " + aClass.getSimpleName());
        System.out.println("aClass.getPackage() = " + aClass.getPackage());

        System.out.println("aClass.getSuperclass() = " + aClass.getSuperclass());
        System.out.println("aClass.getInterfaces() = " + aClass.getInterfaces());
        System.out.println("aClass.isInterface() = " + aClass.isInterface());

        System.out.println("aClass.isEnum() = " + aClass.isEnum());
        System.out.println("aClass.isAnnotation() = " + aClass.isAnnotation());


        System.out.println("aClass.getModifiers() = " + aClass.getModifiers());
        System.out.println("is Public " + Modifier.isPublic(aClass.getModifiers()));
        System.out.println("Modifier.toString(aClass.getModifiers()) = " + Modifier.toString(aClass.getModifiers()));
    }
}
