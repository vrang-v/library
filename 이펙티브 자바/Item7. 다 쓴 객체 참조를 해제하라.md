## Item7. 다 쓴 객체 참조를 해제하라

```Java```에서는 가비지 컬렉터에 의해서 더 이상 참조되지 않는 자원은 회수가 된다.

그러나 가비지 컬렉터에 관리되지 않는 객체들은 메모리 상에 계속 남아 메모리 누수를 만든다.

아래 간단하게 스택을 구현한 코드를 살펴보자. 이 코드에서 메모리 누수를 만드는 치명적인 부분을 찾아보라.
```java
public class Stack
{
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    
    private Object[] elements;
    private int      size = 0;
    
    public Stack( )
    {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }
    
    public void push(Object e)
    {
        ensureCapacity( );
        elements[size++] = e;
    }
    
    public Object pop( )
    {
        if (size == 0) {
            throw new EmptyStackException( );
        }
        return elements[--size];
    }
    
    private void ensureCapacity( )
    {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }
}
```
```push( )```, ```pop( )``` 모두 정상적으로 작동하고 용량이 부족한 경우 크기도 자동적으로 늘어난다.

겉보기에 특별한 문제가 보이지는 않는다. 위 스택에서는 메모리 누수를 부르는 문제가 발생하게 된다.

스택에서 ```pop( )```을 하는 과정에서 단순히 ```size```값만 변경 시켜서 가리키는 데이터의 값만 반환하게 되는데

이럴 경우 이미 제거되어야 했을 데이터들은 여전히 스택내부에서 참조중인 상태로 남아있고 가비지 컬렉터는 이 값들은 수집하지 않는다.

필요없어진 데이터가 메모리상에서 사라지지 않고 계속 남이있게 되는 것이다.

그렇기 때문에 스택내부에서 메모리 관리를 해주어야 하고, 이를 바탕으로 ```pop( )``` 메서드를 수정해보자
```java
public Object pop( )
{
    if (size == 0) {
        throw new EmptyStackException( );
    }
    Object result = elements[--size];
    elements[size] = null;
    return result;
}
```
제거되어야 할 데이터를 더 이상 참조하지 않도록 만들어 가비지 컬렉터에 의해 메모리 회수가 되도록 만들었다.

---

다 쓴 객체 참조를 해제하라는 것은 모든 객체에 적용되는 이야기가 아니다.

위 ```Stack```과 같이 객체 내부에서 메모리 관리를 자체적으로 하는 경우에 객체들이 가비지 컬렉팅 대상에서 벗어나게 되므로

```null```을 대입해주는 방법으로 가비지 컬렉터에게 명시적으로 알려주어야 한다.