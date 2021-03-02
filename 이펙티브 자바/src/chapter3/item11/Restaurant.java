package chapter3.item11;

import java.util.HashMap;
import java.util.Map;

public class Restaurant
{
    private Map<Menu, Integer> menuBoard = new HashMap<>( );
    
    public static void main(String[] args)
    {
        var app       = new Restaurant( );
        var menuBoard = app.menuBoard;
        menuBoard.put(new Menu("Chicken"), 20000);
        System.out.println(app.getChickenPrice( ));
    }
    
    public Integer getChickenPrice( )
    {
        return menuBoard.get(new Menu("Chicken"));
    }
}
