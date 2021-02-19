## Item2. 생성자에 매개변수가 많다면 빌더를 고려하라

---
객체를 생성할때 선택적인 매개변수가 많다면 생성자를 통한 객체 생성은 소스 코드의 복잡도를 매우 증가시킨다.

아래와 같이 4개의 필드를 모두 선택적으로 만들기 위해서는 총 16개의 생성자가 필요하다.

#### ▼ 일반적인 생성자를 통한 객체 생성 방식
```java
public class Character
{
    private int statStr;
    private int statInt;
    private int statDex;
    private int statLuk;
    
    public Character(int statStr)
    {
        this.statStr = statStr;
    }
    
    public Character(int statStr, int statInt)
    {
        this.statStr = statStr;
        this.statInt = statInt;
    }
    
    public Character(int statStr, int statInt, int statLuk)
    {
        this.statStr = statStr;
        this.statInt = statInt;
        this.statLuk = statLuk;
    }
    
    public Character(int statStr, int statInt, int statDex, int statLuk)
    {
        this.statStr = statStr;
        this.statInt = statInt;
        this.statDex = statDex;
        this.statLuk = statLuk;
    }
    
    생략...
}
```

이때, 빌더를 사용하면 소스코드의 복잡도를 줄일 수 있을 뿐 아니라

클래스 사용자에게도 더 가독성 좋고 사용하기 쉬운 객체 생성방식을 제공하게 된다.

위 소스 코드에 빌더를 도입해보자.

#### ▼ 빌더를 이용한 객체 생성 방식
```java
@Setter
public abstract class Character
{
    private int statStr;
    private int statInt;
    private int statDex;
    private int statLuk;
    
    // Inner class인 Builder 생성(호출)
    public static Builder builder( )
    {
        return new Builder( );
    }
    
    static class Builder
    {
        private final Character character = new Character( ) { };
        
        // Character타입의 프로퍼티 설정
        public Builder statStr(int stat)
        {
            character.setStatStr(stat);
            return this;
        }
        public Builder statInt(int stat) { ... }
        public Builder statDex(int stat) { ... }
        public Builder statLuk(int stat) { ... }
        
        
        // Character 인스턴스 리턴
        public Character build( )
        {
            return character;
        }
    }
}
```

빌더는 보통 클래스 내부에 정적 클래스로 선언되어 ```builder( )```를 통해 호출되고,

객체의 프로퍼티를 설정할 수 있는 메서드를 제공하며 ```build( )```를 통해 객체 생성을 완료할 수 있다.

클래스 사용자는 아래와 같이 ```Character``` 객체를 생성할 수 있다.
```java
Character character = Character.builder( )
                               .statStr(10)
                               .statInt(5)
                               .statDex(11)
                               .statLuk(20)
                               .build( );
```

```Item1``` 에서 다뤘던 정적 팩터리 메서드와 비슷한 방식으로 객체 생성을 제공하며,

각각 입력된 값들이 어떤 프로퍼티를 나타내는지 알 수 있어 가독성 좋은 코드를 얻을 수 있다.

### Lombok 사용하기
빌더를 사용할 때마다 클래스 내부에 빌더 클래스를 만들어 주는 것은 손이 많이 간다.

이러한 문제점은 ```Lombok``` 라이브러리를 활용해서 해결할 수 있다.

```@Builder``` 애너테이션을 추가하면 기본적인 빌더를 사용할 수 있다.

(```Lombok```이 만능은 아니다! 복잡한 추가적인 설정이 필요한 경우 빌더를 직접 구현해야한다.)

```Lombok```을 사용한 코드는 아래와 같다. ~~매우 짧아졌다 그것도 아주 매우,,,~~
```java
@Builder
public class LombokCharacter
{
    private int statStr;
    private int statInt;
    private int statDex;
    private int statLuk;
}
```
