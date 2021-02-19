package item2.pizza;

import static java.util.Objects.requireNonNull;

public class NewYorkPizza extends Pizza
{
    private final Size size;
    
    private NewYorkPizza(Builder builder)
    {
        super(builder);
        size = builder.size;
    }
    
    public enum Size
    { SMALL, MEDIUM, LARGE }
    
    public static class Builder extends Pizza.Builder<Builder>
    {
        private final Size size;
        
        public Builder(Size size)
        {
            this.size = requireNonNull(size);
        }
        
        @Override
        public NewYorkPizza build( )
        {
            return new NewYorkPizza(this);
        }
        
        @Override
        protected Builder self( )
        {
            return this;
        }
    }
}
