package item2;

public class ConstructorCharacter
{
    private int statStr;
    private int statInt;
    private int statDex;
    private int statLuk;
    
    public ConstructorCharacter(int statStr)
    {
        this.statStr = statStr;
    }
    
    public ConstructorCharacter(int statStr, int statInt)
    {
        this.statStr = statStr;
        this.statInt = statInt;
    }
    
    public ConstructorCharacter(int statStr, int statInt, int statLuk)
    {
        this.statStr = statStr;
        this.statInt = statInt;
        this.statLuk = statLuk;
    }
    
    public ConstructorCharacter(int statStr, int statInt, int statDex, int statLuk)
    {
        this.statStr = statStr;
        this.statInt = statInt;
        this.statDex = statDex;
        this.statLuk = statLuk;
    }
}
