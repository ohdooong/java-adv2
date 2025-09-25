package annotation.basic;

public class ElementData1Main {
    public static void main(String[] args) {
        Class<ElementData1> elementData1Class = ElementData1.class;
        AnnoElemnt annotation = elementData1Class.getAnnotation(AnnoElemnt.class);

        String value = annotation.value();
        System.out.println("value = " + value);

        int count = annotation.count();
        System.out.println("count = " + count);

        String[] tags = annotation.tags();
        System.out.println("tags = " + tags);


    }
}
