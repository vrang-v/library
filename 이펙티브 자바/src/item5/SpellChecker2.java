package item5;

import java.util.Objects;
import java.util.function.Supplier;

public class SpellChecker2
{
    private final Dictionary dictionary;
    
    public SpellChecker2(Dictionary dictionary)
    {
        this.dictionary = Objects.requireNonNull(dictionary);
    }
    
    public SpellChecker2(Supplier<? extends Dictionary> dictionarySupplier)
    {
        this.dictionary = Objects.requireNonNull(dictionarySupplier.get( ));
    }
}