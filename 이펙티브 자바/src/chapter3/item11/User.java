package chapter3.item11;

import java.util.Objects;

public class User
{
    private long id;
    
    private String name;
    
    private String email;
    
    private int age;
    
    private double point;
    
    @Override
    public boolean equals(Object o)
    {
        if (this == o) { return true; }
        if (!(o instanceof User)) { return false; }
        User user = (User)o;
        return Objects.equals(id, user.id)
               && Objects.equals(name, user.name)
               && Objects.equals(email, user.email)
               && Objects.equals(age, user.age)
               && Objects.equals(point, user.point);
    }
    
    @Override
    public int hashCode( )
    {
        int hashCode;
        hashCode = Long.hashCode(id);
        hashCode = 31 * hashCode + name.hashCode( );
        hashCode = 31 * hashCode + Double.hashCode(point);
        return hashCode;
    }
}
