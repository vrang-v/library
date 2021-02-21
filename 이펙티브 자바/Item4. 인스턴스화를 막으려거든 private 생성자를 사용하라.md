## item4. 인스턴스화를 막으려거든 private 생성자를 사용하라

---

클래스 내부에 정적 메서드 및 정적 필드만을 만들고 싶은 경우에 ```private``` 생성자를 이용하자.
#### ▼ private 생성자를 사용해 인스턴스 생성을 제한한 클래스
```java
public class PrivateUtil
{
    // Prevent object creation
    private PrivateUtil( )
    {
        throw new AssertionError("This class cannot be instantiated.");
    }
    
    private static int numberOfCalls = 0;
    
    public static int plus(int first, int second) { ... }
    
    public static int minus(int first, int second) { ... }
    
    public static void printNumberOfCalls( ) { ... }
}
```
위에서 ```private``` 생성자가 호출되는 경우 ```AssertionError```가 발생하는 것을 확인할 수 있다.

에러를 발생시키는 것이 필수적이지는 않지만 리플렉션을 통한 생성자 접근이나, 클래스내부에서 인스턴스를 만드는 것을 방지할 수 있다.

(주석이나 에러 메세지 등으로 인스턴스화가 불가능한 클래스라는 점을 명시하자.)

### 안티 패턴 - 추상 클래스를 이용한 인스턴스 생성 제어
추상 클래스의 경우에도 클래스의 인스턴스화가 불가능하나 이런 방법으로 인스턴스 생성을 통제한다면 문제점이 발생한다.

- 상속을 통해서 인스턴스를 생성할 수 있다.


- ```absrtact```의 원래의 의미를 벗어나므로 API 사용자에게 잘못된 사용법을 예상하게 할 수 있다.

#### ▼ 인스턴스화를 금지하기 위한 추상 클래스
```java
public abstract class AbstractUtil
{
    public static int numberOfCalls = 0;
    
    public static int plus(int first, int second) { ... } 
    
    생략...
}
```
위처럼 추상 클래스를 이용하는 방법은 아래와 같이 상속을 통해 인스턴스 생성을 하는 것을 막을 수 없다.
```java
public class MyUtil extends AbstractUtil { ... }
```
```java
MyUtil myUtil = new MyUtil( );
```