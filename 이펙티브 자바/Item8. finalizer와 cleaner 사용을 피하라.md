## Item8. finalizer와 cleaner 사용을 피하라
자바에서 객체가 소멸하는 ```Destructor``` 상황에는 ```finalize( )``` 자동으로 호출된다.

이를 이용해서 ```ThreadPool```과 같은 회수가 필요한 자원들을 반환하는 코드를 ```finalize( )``` 내부에 넣고 객체가 소멸할 때 알아서 자원이 반환되는 방식의 코드를 작성할 수도 있다.

그러나 위 같은 방법에는 **치명적인 부작용**이 따른다.

우선, 자바의 ```GC``` 시점은 아무도 예측할 수 없으므로 ```finalize( )``` 호출의 시점 역시 예상할 수 없다.

게다가 ```finalize( )```가 진행되는 백그라운드 스레드의 우선순위가 높지 않으므로 제때 자원 회수가 기대하는 것은 어렵다.

물론 ```System.gc( )```를 통해 수동적으로 가비지 컬렉터를 작동시킬 수 있으나, 이 방식에도 여전히 문제점이 생긴다.

첫번째로, ```System.gc( )```은 항상 동작하는 메서드가 아니다. 시스템 내부에서 이 메서드를 처리하지 않을 수도 있다.

두번째로, 빈번한 가비지 컬렉팅은 프로그램의 성능 저하로 이어지기 쉽다. 가비지 컬렉팅 방식에 따라서 모든 스레드가 멈추는 일명 ```Stop the world```가 계속해서 발생하게 된다.

위 같은 이유로 ```finalize( )```의 사용은 자제해야 하며, 실제로 ```finalize( )```가 명시되어 있는 ```Object```클래스를 보면 아래 내용을 확인할 수 있다.
```java
public class Object
{
    ...
    @Deprecated(since = "9") // java 9이상에서는 사용이 권장되지 않는다.
    protected void finalize() throws Throwable { }
    ...
}
```

---
### 자원 회수에는 ```try-with-resources```를 이용하자
```AutoClosable``` 인터페이스를 구현한 클래스를 ```try-with-resources```에서 사용하게 되면 자동적으로 ```close( )```가 호출되므로 이를 이용하자.

```java
public class Room implements AutoCloseable
{
    private static final Cleaner cleaner = Cleaner.create( );
    private final State state;
    private final Cleaner.Cleanable cleanable;
    
    public Room(int numJunkPiles)
    {
        state     = new State(numJunkPiles);
        cleanable = cleaner.register(this, state);
    }
    
    @Override
    public void close( )
    {
        cleanable.clean( );
    }
    
    private static class State implements Runnable
    {
        int numJunkPiles;
        
        State(int numJunkPiles)
        {
            this.numJunkPiles = numJunkPiles;
        }
        
        @Override
        public void run( )
        {
            System.out.println("방 청소");
            numJunkPiles = 0;
        }
    }
}
```
객체를 반환하기 전에 항상 방 청소(```numJunkPiles```를 0으로 만드는 작업)를 해야하는 ```Room``` 클래스가 있다.

```AutoCloseable```을 구현한 ```close( )```에서 ```Cleaner.clean( )```을 호출하여 자원을 회수할 수 있다.

클라이언트는 이 클래스를 아래와 같이 사용하면 올바르게 작동할 것을 기대할 수 있다.

```java
public class Adult
{
    public static void main(String[] args)
    {
        try (Room room = new Room(7)) {
            System.out.println("Hello");
        }
    }
}
```
그러나, 클라이언트에서 ```try-with-resources```를 사용하지 않거나 명시적으로 ```close( )```를 호출하지 않으면 제대로 작동하지 않는다.

가령 아래와 같은 예시에서 말이다.

```java
public class Teenager
{
    public static void main(String[] args)
    {
        Room room = new Room(17);
        System.out.println("Hello");
    }
}
```

위 같은 상황을 대비하기 위해서 안전책으로 ```finalize( )```를 사용하기도 한다.

뒤늦게나마 자원의 회수가 일어날 수 있도록 ```finalize( )```를 작성하게 되면 클라이언트의 실수를 방지할 수 있다.
