package item6;

import java.util.regex.Pattern;
import java.util.stream.LongStream;

public class App
{
    public static final Pattern ROMAN = Pattern.compile("^(?=.)M*(C[MD]|D?C{0,3})(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
    
    public static void main(String[] args)
    {
        String james1 = new String("james");
        String james2 = new String("james");
        
        Boolean trueValue  = new Boolean("true");
        Boolean falseValue = Boolean.FALSE;
    
        timeCheck(( ) -> System.out.println(sum2( )));
        timeCheck(( ) -> System.out.println(sum1( )));
    }
    
    public static long sum1( )
    {
        return LongStream.range(0, Integer.MAX_VALUE).sum( );
    }
    
    public static Long sum2( )
    {
        Long sum = 0L;
        for (long i = 0; i < Integer.MAX_VALUE; i++) {
            sum += i;
        }
        return sum;
    }
    
    public static void timeCheck(Runnable runnable)
    {
        double start = System.currentTimeMillis( );
        runnable.run( );
        double end = System.currentTimeMillis( );
        System.out.println(end - start);
    }
    
    public static boolean isRomanNumeral(String s)
    {
        return ROMAN.matcher(s).matches( );
    }
}
