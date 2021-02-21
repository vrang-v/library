package item4;

public abstract class AbstractUtil
{
    public static int numberOfCalls = 0;
    
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
