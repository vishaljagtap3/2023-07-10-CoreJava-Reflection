import in.bitcode.emps.Student;

import java.lang.reflect.*;

public class Main {
    public static void main(String[] args) {

        Class<String> classString = String.class;
        try {

            String s = classString.newInstance();

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        Class c = Student.class;

        System.out.println("Name: " + c.getName());
        System.out.println("Simple Name: " + c.getSimpleName());
        Class superC = c.getSuperclass();
        System.out.println("Super class: " + superC.getName());
        System.out.println("is interface: " + c.isInterface());
        System.out.println("is anonymous: " + c.isAnonymousClass());
        System.out.println("package name: " + c.getPackageName());

        System.out.println("Fields -->");
        Field []  fields = c.getFields();
        for(Field field : fields) {
            System.out.println(field.getType().getName() + " " + field.getName());
        }

        System.out.println();
        System.out.println("Methods --> ");
        Method [] methods = c.getMethods();
        for(Method m : methods) {
            System.out.print(m.getReturnType().getName() + " " + m.getName());
            Parameter [] parameters = m.getParameters();
            System.out.print( "( ");
            for(Parameter p : parameters) {
                System.out.print(p.getType().getName() + " " + p.getName() + ", ");
            }
            System.out.println( " )");
        }
        System.out.println();

        System.out.println("Constructors --> ");
        Constructor [] constructors = c.getConstructors();
        for(Constructor cons : constructors) {

            System.out.print(cons.getName() );

            Parameter [] parameters = cons.getParameters();
            System.out.print( "( ");
            for(Parameter p : parameters) {
                System.out.print(p.getType().getName() + " " + p.getName() + ", ");
            }
            System.out.println( " )");
        }

        System.out.println("---------------------------------");

        try {
            Student newEmp = (Student) c.newInstance();
            newEmp.display();

            Constructor defCons = c.getConstructor(null);
            Student s1 = (Student) defCons.newInstance(null);
            s1.display();

            Class [] paramsTypeInfo = { int.class, String.class, int.class};
            Constructor paramCons = c.getConstructor(paramsTypeInfo);
            Object [] actualParams = {999, "Vishal", 80};
            Student s2 = (Student) paramCons.newInstance(actualParams);
            s2.display();

            Method mSetRoll = c.getMethod("setRoll", new Class[] {int.class});
            mSetRoll.invoke(s2, new Object[] {888} );

            Method mDisplay = c.getMethod("display", null);
            mDisplay.invoke(s2, null);
            System.out.println();

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


        System.out.println();
        ISO iso = new ISO() {
            @Override
            public void quality() {
            }

            @Override
            public void hr() {
            }
        };
        System.out.println("iso class name " + iso.getClass().getName());
        System.out.println("iso is Anonymous " + iso.getClass().isAnonymousClass());
    }
}
