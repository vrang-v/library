package item2.pizza;

public class ChicagoPizza extends Pizza
{
    private final boolean sauceInside;
    
    private ChicagoPizza(Builder builder)
    {
        super(builder);
        sauceInside = builder.sauceInside;
    }
    
    public static class Builder extends Pizza.Builder<Builder>
    {
        private boolean sauceInside = false;
        
        public Builder sauceInside( )
        {
            sauceInside = true;
            return this;
        }
        
        @Override
        public ChicagoPizza build( )
        {
            return new ChicagoPizza(this);
        }
        
        @Override
        protected Builder self( )
        {
            return this;
        }
    }
}
