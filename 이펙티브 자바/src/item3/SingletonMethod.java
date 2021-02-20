package item3;

import java.util.Objects;

public class SingletonMethod
{
    private static SingletonMethod instance;
    
    private SingletonMethod( ) { /* private constructor for singleton pattern */ }
    
    public static SingletonMethod getInstance( )
    {
        if (Objects.isNull(instance)) {
            instance = new SingletonMethod( );
        }
        return instance;
    }
}
