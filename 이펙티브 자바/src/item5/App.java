package item5;

public class App
{
    public static void main(String[] args)
    {
        SpellChecker2 spellChecker1 = new SpellChecker2(new Dictionary( ));
        SpellChecker2 spellChecker2 = new SpellChecker2(new MyDictionary( ));
        
        SpellChecker2 spellChecker3 = new SpellChecker2(( ) -> new Dictionary( ));
        SpellChecker2 spellChecker4 = new SpellChecker2(( ) -> new MyDictionary( ));
    
        SpellChecker koreanSpellChecker = new SpellChecker(new KoreanDictionary( ));
        SpellChecker englishSpellChecker = new SpellChecker(new EnglishDictionary( ));
    }
}

class MyDictionary extends Dictionary
{

}
