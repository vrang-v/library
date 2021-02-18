package item1;

public class App
{
    public static void main(String[] args)
    {
        User user1 = User.newUserById("abc123");
        User user2 = User.newUserByName("John");
        
        System.out.println(user1.getId( ));
        System.out.println(user2.getName( ));
        
        Company apple  = Company.apple( );
        Company amazon1 = Company.amazon( );
        Company aws1    = Company.amazon(1);
    
        Company amazon2 = Company.amazon(false);
        Company aws2 = Company.amazon(true);
    }
}