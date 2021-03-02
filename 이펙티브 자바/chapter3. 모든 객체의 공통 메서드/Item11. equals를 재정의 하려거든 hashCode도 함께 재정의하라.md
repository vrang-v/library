## Item11. equalus를 재정의 하려거든 hashCode도 함께 재정의하라
`Item 10`에서 살펴봤던 `equals( )`를 재정의하는 과정에서 뜻하지 않게 `hashCode( )` 규약을 깨뜨리게 된다.

논리적으로 동치성을 가지는 두 객체에 대해서 다른 해시코드가 부여가 되고

이는 `HashMap`와 같이 해시코드를 이용하는 경우에 예상치 못한 오류를 불러 일으킨다.

아래 코드를 보며 알아보자

우선 `name`을 필드로 갖는 `Menu`클래스가 있다.
```java
@AllArgsConstructor
public class Menu { private String name; }
```
이때 `Restaurant`클래스에서는 메뉴와 가격을 키와 값으로 갖는`HashMap`타입의 `menuBoard`가 있다.

그리고 `Chicken Menu`와 가격을 해시맵에 등록하였다.
```java
public class Restaurant
{
    private Map<Menu, Integer> menuBoard = new HashMap<>( );
    
    public static void main(String[] args)
    {
        var app       = new Restaurant( );
        var menuBoard = app.menuBoard;
        menuBoard.put(new Menu("Chicken"), 20000);
    }
}
```
이때 `Chicken`가격을 알기 위한 `getChickenPrice`메서드도 만들어보자.
```java
public Integer getChickenPrice( )
{
    return menuBoard.get(new Menu("Chicken"));
}
```
얼마를 예상하는가? 우리의 기대는 20000을 반환하는 것이다.

아쉽지만 실제 값을 출력해보면 다른 값을 반환하는 것을 알게될 것이다.
```java
System.out.println(app.getChickenPrice( ));
> null
```
#### 왜 이런 현상이 발생한 것일까?
`menuBoard`에 `Chicken`을 넣을 때와 꺼낼 때 두 객체의 해시코드를 이용하게 되는데

두 객체의 해시코드가 다르기 때문에 해시맵에서 서로 다른 객체로 인식이 되기 때문이다.

#### 해시코드 재정의하기
가장 간단한 방법은 `Objects` 유틸 클래스에서 제공하는 `hash`를 이용하는 것이다.
```java
@Override
public int hashCode( ) { return Objects.hash(name); }
```
해시코드를 재정의하면 `Chicken`의 가격은 정상적으로 출력이 될 것이다.

`hashCode( )`의 성능을 조금 더 높히고 싶다면 직접 정의하는 방식도 있다. 방법은 아래와 같다.

- `primitive`타입의 경우 `Wrapper`타입의 `hashCode( )`유틸메서드를 호출하자.


- `reference`타입의 경우 그 타입의 `hashCode(`)`를 호출하자.


- 해시코드에 31을 곱한 뒤 새로운 필드의 해시코드 값을 더해주자.

주구장창 글로 쓰는 것보다 코드를 한 번 보는 것이 이해하기 빠를 것이다.
```java
// 각 필드의 타입은 long id, Integer point, String name 이다.
@Override
public int hashCode( )
{
    int hashCode;
    hashCode = Long.hashCode(id);
    hashCode = 31 * hashCode + point.hashCode( );
    hashCode = 31 * hashCode + name.hashCode( );
    return hashCode;
}
```

### 주의점

- `equals( )`를 재정의할 때 사용한 필드만으로 `hashCode`를 구성하라

당연하지만 `equals( )`에서 논리적 동치성을 보장하는 객체끼리만 같은 해시코드를 가져야 한다.

- API에 해시코드 구현방식을 드러내지 말자

클라이언트가 특정 로직에 의존하게 된다면 추후 해시코드 구현을 변경하는데 어려움이 생긴다.