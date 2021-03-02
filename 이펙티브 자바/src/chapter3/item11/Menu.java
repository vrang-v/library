package chapter3.item11;

import java.util.Objects;

public class Menu
{
    private String name;
    
    public Menu(String name)
    {
        this.name = name;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (this == o) { return true; }
        if (!(o instanceof Menu)) { return false; }
        Menu menu = (Menu)o;
        return Objects.equals(name, menu.name);
    }
    
    @Override
    public int hashCode( )
    {
        return Objects.hash(name);
    }
}
