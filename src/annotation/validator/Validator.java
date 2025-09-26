package annotation.validator;

import java.lang.reflect.Field;

public class Validator {
    public static void validate(Object object) throws IllegalAccessException {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(NotEmpty.class)) {
                NotEmpty annotation = field.getAnnotation(NotEmpty.class);
                String value = (String) field.get(object);
                if (value == null || value.isEmpty()) {
                    throw new RuntimeException(annotation.message());
                }

            }

            if (field.isAnnotationPresent(Range.class)) {
                Range annotation = field.getAnnotation(Range.class);
                long value = (long) field.getLong(object);
                if (value < annotation.min() || value > annotation.max()) {
                    throw new RuntimeException(annotation.message());
                }
            }
        }
    }
}
