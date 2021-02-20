package item3;

public class SingletonField
{
    public static final SingletonField INSTANCE = new SingletonField( );
    
    private SingletonField( ) { /* private constructor for singleton pattern */ }
}
