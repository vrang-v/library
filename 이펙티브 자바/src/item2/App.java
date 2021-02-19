package item2;

import item2.pizza.ChicagoPizza;
import item2.pizza.NewYorkPizza;
import item2.pizza.NewYorkPizza.Size;
import item2.pizza.Pizza;

import static item2.pizza.Pizza.Topping.HAM;
import static item2.pizza.Pizza.Topping.MUSHROOM;

public class App
{
    public static void main(String[] args)
    {
        Character character = Character.builder( )
                                       .statStr(10)
                                       .statInt(5)
                                       .statDex(11)
                                       .statLuk(20)
                                       .build( );
        
        LombokCharacter lombok = LombokCharacter.builder( )
                                                .statStr(10)
                                                .statInt(5)
                                                .statDex(11)
                                                .statLuk(20)
                                                .build( );
        
        Pizza chicagoPizza = new ChicagoPizza.Builder( )
                .addTopping(HAM)
                .addTopping(MUSHROOM)
                .sauceInside( )
                .build( );
        
        Pizza newYorkPizza = new NewYorkPizza.Builder(Size.MEDIUM)
                .addTopping(HAM)
                .build( );
    }
}
