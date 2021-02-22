package item5;

import java.util.function.Supplier;

public class SpellChecker
{
    private final IDictionary dictionary;
    
    public SpellChecker(IDictionary dictionary)
    {
        this.dictionary = dictionary;
    }
    
    public SpellChecker(Supplier<? extends IDictionary> dictionarySupplier)
    {
        this.dictionary = dictionarySupplier.get( );
    }
}
