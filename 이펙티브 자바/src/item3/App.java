package item3;

public class App
{
    public static void main(String[] args)
    {
        SingletonMethod singletonMethod1 = SingletonMethod.getInstance( );
        SingletonMethod singletonMethod2 = SingletonMethod.getInstance( );
        System.out.println(singletonMethod1 == singletonMethod2);
    
        SingletonField singletonField1 = SingletonField.INSTANCE;
        SingletonField singletonField2 = SingletonField.INSTANCE;
        System.out.println(singletonField1 == singletonField2); // result = true
    
        SingletonEnum singletonEnum1 = SingletonEnum.INSTANCE;
        SingletonEnum singletonEnum2 = SingletonEnum.INSTANCE;
        System.out.println(singletonEnum1 == singletonEnum2);
    }
}
