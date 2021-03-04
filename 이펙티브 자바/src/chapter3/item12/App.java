package chapter3.item12;

public class App
{
    public static void main(String[] args)
    {
        var oldUser = new OldUser(1L, "Harry", 17);
        System.out.println("oldUser = " + oldUser);
        
        var user = new User(1L, "Harry", 17);
        System.out.println("user = " + user);
    
        var lombokUser = new LombokUser(1L, "Harry", 17);
        System.out.println("lombokUser = " + lombokUser);
    }
}
