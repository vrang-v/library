package item4;

public class PrivateUtil
{
    // Prevent object creation
    private PrivateUtil( )
    {
        throw new AssertionError("This class cannot be instantiated.");
    }
    
    private static int numberOfCalls = 0;
    
    public static int plus(int first, int second)
    {
        numberOfCalls += 1;
        return first + second;
    }
    
    public static int minus(int first, int second)
    {
        numberOfCalls += 1;
        return first - second;
    }
    
    public static void printNumberOfCalls( )
    {
        System.out.println(numberOfCalls);
    }
}
