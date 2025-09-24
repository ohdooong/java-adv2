package reflection;

import reflection.data.BasicData;
import reflection.data.User;

import java.lang.reflect.Field;

public class FieldV2 {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        User user = new User("id1", "hi", 20);
        System.out.println("user = " + user);

        Class<? extends User> aClass = user.getClass();
        Field nameField = aClass.getDeclaredField("name");
        nameField.setAccessible(true);
        nameField.set(user,"haha");

        System.out.println("user = " + user);

    }
}
