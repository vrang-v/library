package item1;

import lombok.Getter;

@Getter
public class User
{
    private String id;
    
    private String name;
    
    private User( ) { /* Provide clients only static factory methods instead of constructors */ }
    
    public static User newUserById(String id)
    {
        User user = new User( );
        user.id = id;
        return user;
    }
    
    public static User newUserByName(String name)
    {
        User user = new User( );
        user.name = name;
        return user;
    }
    
    /* 시그니처가 같은 생성자를 사용할 수 없다.
    public User(String id)
    {
        this.id = id;
    }
    
    public User(String name)
    {
        this.name = name;
    }
    */
}
