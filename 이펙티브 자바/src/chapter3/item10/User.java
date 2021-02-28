package chapter3.item10;

public class User
{
    /* -------------------- field -------------------- */
    private Long id;
    
    private String name;
    
    private Integer age;
    
    private String email;
    
    private Double score;
    /* ----------------------------------------------- */
    
    @Override
    public boolean equals(Object o)
    {
        if (this == o) { return true; }
        if (!(o instanceof User)) { return false; }
        User user = (User)o;
        return id.equals(user.id)
               && name.equals(user.name)
               && age.equals(user.age)
               && email.equals(user.email)
               && Double.compare(score, user.score) == 0;
    }
}
