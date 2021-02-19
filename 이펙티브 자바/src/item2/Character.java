package item2;

import lombok.Setter;

@Setter
public abstract class Character
{
    private int statStr;
    private int statInt;
    private int statDex;
    private int statLuk;
    
    public static Builder builder( )
    {
        return new Builder( );
    }
    
    static class Builder
    {
        private final Character character = new Character( ) { };
        
        public Builder statStr(int stat)
        {
            character.setStatStr(stat);
            return this;
        }
        
        public Builder statInt(int stat)
        {
            character.setStatInt(stat);
            return this;
        }
        
        public Builder statDex(int stat)
        {
            character.setStatDex(stat);
            return this;
        }
        
        public Builder statLuk(int stat)
        {
            character.setStatLuk(stat);
            return this;
        }
        
        public Character build( )
        {
            return character;
        }
    }
}
