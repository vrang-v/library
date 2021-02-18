package item1;

public interface Company
{
    static Company apple( )
    {
        return new Apple( );
    }
    
    static Company amazon( )
    {
        return new Amazon( );
    }
    
    static Company amazon(int i)
    {
        return new AmazonWebService( );
    }
    
    static Company amazon(boolean isAws)
    {
        if (isAws) { return new AmazonWebService( ); }
        else { return new Amazon( ); }
    }
}
