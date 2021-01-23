import java.lang.reflect.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Class<?> aClass = Class.forName("Component");
        Field fields[] = aClass.getDeclaredFields();
        for (Field field : fields) {
            StringBuilder modifiers = new StringBuilder();

            int modifiersCodes = field.getModifiers();
            if (Modifier.isFinal(modifiersCodes)) {
                modifiers.append("final");
                modifiers.append(" ");
            }
            if (Modifier.isPublic(modifiersCodes)) {
                modifiers.append("public");
                modifiers.append(" ");
            }
            if (Modifier.isPrivate(modifiersCodes)) {
                modifiers.append("private");
                modifiers.append(" ");
            }
            if (Modifier.isStatic(modifiersCodes)) {
                modifiers.append("static");
                modifiers.append(" ");
            }

            System.out.println(modifiers.toString() + field.getType() + " " + field.getName());
        }

        Method methods[] = aClass.getDeclaredMethods();

        for (Method method : methods) {
            StringBuilder modifiers = new StringBuilder();

            int modifiersCodes = method.getModifiers();
            if (Modifier.isFinal(modifiersCodes)) {
                modifiers.append("final");
                modifiers.append(" ");
            }
            if (Modifier.isPublic(modifiersCodes)) {
                modifiers.append("public");
                modifiers.append(" ");
            }
            if (Modifier.isPrivate(modifiersCodes)) {
                modifiers.append("private");
                modifiers.append(" ");
            }
            if (Modifier.isStatic(modifiersCodes)) {
                modifiers.append("static");
                modifiers.append(" ");
            }

            StringBuilder parametersOfMethods = new StringBuilder();

            Parameter parameters[] = method.getParameters();

            for (Parameter parameter : parameters) {
                parametersOfMethods.append(parameter.getType().getSimpleName()).append(" ").append(parameter.getName()).append(", ");
            }
            System.out.println(modifiers.toString() + method.getReturnType().getSimpleName() + " " + method.getName()
                    + "(" + parametersOfMethods.toString() + ")");
        }

        Object object = aClass.newInstance();

        Constructor<Component> constructor = (Constructor<Component>) aClass.getConstructor(int.class, int.class);
        Component component = constructor.newInstance(15, 20);
        System.out.println(component.getPublicField());

        Method method = aClass.getMethod("getSumOfFields", int.class);
        Object result = method.invoke(component, 100);
        System.out.println(result);
    }
}
