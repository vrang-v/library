package item2.pizza;

import java.util.EnumSet;
import java.util.Set;

import static java.util.Objects.requireNonNull;

public abstract class Pizza
{
    protected final Set<Topping> toppings;
    
    Pizza(Builder<?> builder)
    {
        toppings = builder.toppings.clone( );
    }
    
    public enum Topping
    { HAM, MUSHROOM, ONION, PEPPER, SAUSAGE }
    
    abstract static class Builder <T extends Builder<T>>
    {
        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);
        
        public T addTopping(Topping topping)
        {
            toppings.add(requireNonNull(topping));
            return self( );
        }
        
        abstract Pizza build( );
        
        protected abstract T self( );
    }
}
