package item4;

public class App
{
    public static void main(String[] args)
    {
        var plus1  = AbstractUtil.plus(1, 2);
        var minus1 = AbstractUtil.minus(3, 4);
        System.out.println(plus1);
        System.out.println(minus1);
        AbstractUtil.printNumberOfCalls( );
        
        var plus2  = PrivateUtil.plus(10, 20);
        var minus2 = PrivateUtil.minus(30, 40);
        System.out.println(plus2);
        System.out.println(minus2);
        PrivateUtil.printNumberOfCalls( );
        
        MyUtil myUtil = new MyUtil( );
    }
}

class MyUtil extends AbstractUtil
{

}
