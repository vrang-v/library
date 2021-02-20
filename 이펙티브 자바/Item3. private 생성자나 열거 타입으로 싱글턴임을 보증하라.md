## Item3. private 생성자나 열거 타입으로 싱글턴임을 보증하라.

---

객체의 인스턴스가 하나만 존재하는 것을 보장하기 위해 싱글턴 패턴이 사용된다.

싱글턴 패턴을 사용하면 생성자를 ```private```으로 설정하여 객체의 인스턴스가 하나임을 보증해야한다.

우선 가장 대표적인 싱글턴 패턴 구현은 ```static``` ```final``` 필드의 인스턴스를 만들어 객체를 관리하는 방법이다.

#### ▼ ```static``` ```final``` 필드를 이용한 싱글턴 클래스
```java
public class SingletonField
{
    public static final SingletonField INSTANCE = new SingletonField( );
    
    private SingletonField( ) { /* private constructor for singleton pattern */ }
}
```

싱글턴 패턴임을 보증하기 위한 ```private``` 생성자와 클래스 사용자가 사용할 수 있는 ```INSTANCE```를 확인할 수 있다.

```java
SingletonField singletonField1 = SingletonField.INSTANCE;
SingletonField singletonField2 = SingletonField.INSTANCE;
System.out.println(singletonField1 == singletonField2); // result = true
```
---
또 다른 방법으로 ```Item1```에서 다뤘던 정적 팩터리 메서드를 이용해 싱글턴 인스턴스를 제공할 수 있다.

#### ▼ 정적 팩터리 메서드를 이용한 싱글턴 클래스
```java
public class SingletonMethod
{
    private static final SingletonMethod instance = new SingletonMethod( );
    
    private SingletonMethod( ) { /* private constructor for singleton pattern */ }
    
    public static SingletonMethod getInstance( ) { return instance; }
}
```
이 방식의 장점으로는 ```getInstance( )```에서 인스턴스를 넘겨주는 방식을 마음대로 정할 수 있기 때문에

언제든지 싱글턴 패턴을 싱글턴 패턴이 아니게 바꿀 수 있다는 장점이 있다.

또 람다식에서 메서드 래퍼런스를 사용할 수 있다.

가령 ```SingletonMethod:::getInstance```와 방식을 통해 ```Supplier```로 사용할 수 있다.
```java
SingletonMethod singletonMethod1 = SingletonMethod.getInstance( );
SingletonMethod singletonMethod2 = SingletonMethod.getInstance( );
System.out.println(singletonMethod1 == singletonMethod2); // result = true
```

---
그러나 위 2가지 방식에는 자바의 리플렉션을 이용한다면 싱글턴 패턴이 깨뜨릴 수 있는 위험이 있다.

리플렉션을 이용하여 ```private``` 생성자로 인스턴스를 만든다면 싱글턴 패턴이 깨지게 된다.

이때, 자바의 열거형 타입인 ```enum```을 사용하면 싱글턴을 쉽게 보장할 수 있다.

```java
public enum SingletonEnum 
{
    INSTANCE; 이하 생략...
}
```

그러나 ```enum```을 사용한 싱글턴 패턴의 경우에 클래스의 상속이 불가능하다는 단점이 있다.
