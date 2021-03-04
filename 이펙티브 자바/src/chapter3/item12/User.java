package chapter3.item12;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
public class User
{
    private Long id;
    
    private String name;
    
    private Integer age;
    
    @Override
    public String toString( )
    {
        return "User{id=" + id + ", name='" + name + "', age=" + age + '}';
    }
}

@AllArgsConstructor
class OldUser
{
    private Long id;
    
    private String name;
    
    private Integer age;
}

@ToString
@AllArgsConstructor
class LombokUser
{
    private Long id;
    
    private String name;
    
    private Integer age;
}