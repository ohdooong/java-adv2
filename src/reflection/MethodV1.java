package reflection;

import reflection.data.BasicData;

import java.lang.reflect.Method;

public class MethodV1 {
    public static void main(String[] args) {
        Class<BasicData> helloClass = BasicData.class;
        System.out.println("======== methods ===========");
        // 모든메서드
        Method[] methods = helloClass.getMethods();
        for (Method method : methods) {
            System.out.println("method = " + method);
        }

        System.out.println("======== declaredMethods ===========");
        // 상속받은거 제외하고 내가 선언한 모든 메서드 private, public, protected
        Method[] declaredMethods = helloClass.getDeclaredMethods();
        for (Method method : declaredMethods) {
            System.out.println("method = " + method);
        }




    }
}
