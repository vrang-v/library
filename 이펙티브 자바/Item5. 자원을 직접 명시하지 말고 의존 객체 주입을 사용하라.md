## Item5. 자원을 직접 명시하지 말고 의존 객체 주입을 사용하라

---
많은 클래스들은 서로 다른 클래스에 의존하는 경우가 많다. 이때, 의존하는 자원을 직접적으로 명시하지 않도록 하자.

가령 맞춤법 검사기는 사전에 의존한다. 이러한 사실을 바탕으로 아래와 같은 코드를 작성할 수 있다.
```java
public class SpellChecker
{
    private static final Dictionary dictionary = new Dictionary( );
    
    생략...
}
```
하지만 이런 방식의 설계로는 유연하지 못한 객체를 만들게 된다.

사전이 개정되었다면?, 다른 언어로 된 사전을 사용하고 싶다면?, 간이용 사전으로 테스트를 해보고 싶다면?

위와 같은 요구사항이 들어오게 되면 아래와 같은 번거로운 작업이 필요해진다.

- 클래스 작성자는 매번 내부 코드를 수정해주어야 한다.


- 클래스 사용자는 상황에 맞게 다른 클래스를 사용해야 할지도 모른다.

위와 같은 이유로 직접 명시하는 방식대신 다른 방법으로 의존성을 관리할 것을 추천한다.

### 의존 객체를 주입하라
객체를 생성할 때 의존하는 자원들은 외부로부터 주입받을 수 있도록 만들어보자.

가장 일반적으로 생성자의 매개변수로 사전을 주입받는 방법이 있을 것이다.
```java
public class SpellChecker
{
    private final Dictionary dictionary;
    
    public SpellChecker(Dictionary dictionary)
    {
        this.dictionary = Objects.requireNonNull(dictionary);
    }
    
    생략...
}
```
이렇게 되면 클래스 사용자가 주입하는 ```Dictionary``` 객체에 따라 다른 결과를 기대할 수 있다.
```java
class MyDictionary extends Dictionary { ... }
```
```java
SpellChecker spellChecker1 = new SpellChecker2(new Dictionary( ));
SpellChecker spellChecker2 = new SpellChecker2(new MyDictionary( ));
```
또한 직접적으로 객체를 넘겨주는 방식 대신 ```Supplier```를 이용한 자원 팩터리를 주입하는 방법도 가능하다.
```java
public class SpellChecker2
{
    private final Dictionary dictionary;
    
    public SpellChecker2(Supplier<? extends Dictionary> dictionarySupplier)
    {
        this.dictionary = Objects.requireNonNull(dictionarySupplier.get( ));
    }
    
    생략...
}
```
```java
SpellChecker2 spellChecker3 = new SpellChecker2(( ) -> new Dictionary( ));
SpellChecker2 spellChecker4 = new SpellChecker2(( ) -> new MyDictionary( ));
```

### 인터페이스를 이용한 더 유연할 설계
의존 객체를 주입 받는 방식으로 더 나은 ```SpellChecker``` 객체를 얻게 되었지만 아직 부족한 부분이 보인다.

위 같은 방식으로 객체를 생성한다면 **생성자에 명시한 클래스에만 묶이게 된다.**

```Dictionary``` 클래스를 상속받는 방식으로만 확장할 수 있게 되며 클래스 내부에서도

사전 자체가 아니라 ```Dictionary``` 클래스 내부 구현에 강하게 의존하게 될 것이다.

이런 결합을 인터페이스를 이용해서 끊어주자.

```java
public interface Dictionary { ... }
```
```java
public class SpellChecker
{
    private final Dictionary dictionary;
    
    public SpellChecker(Dictionary dictionary)
    {
        this.dictionary = dictionary;
    }
}
```

코드만 봤을때는 크게 달라지지 않은 것처럼 보이지만 사실 많은 것들이 변했다.

이제 ```SpellChecker```는 ```Dictionary```의 내부 구현이나 상태에 의존하는 것이 아니라 역할에만 의존하게 된다.

아래와 같이 내부 구현 방식은 전혀 다르지만 같은 기능을 제공하는 두 클래스가 있다.
```java
public class KoreanDictionary implements Dictionary { ... }
```
```java
public class EnglishDictionary implements Dictionary { ... }
```

두 객체는 모두 ```Dictionary``` 인터페이스를 구현하므로 같은 기능을 제공해 줄 것을 기대할 수 있다.

그러므로 ```SpellChecker```가 ```Dictionary``` 구현체를 직접적으로 알지 못해도 인터페이스를 통해 상호작용하는 방식의 유연한 설계를 얻을 수 있다.

```java
SpellChecker koreanSpellChecker  = new SpellChecker(new KoreanDictionary( ));
SpellChecker englishSpellChecker = new SpellChecker(new EnglishDictionary( ));
```

