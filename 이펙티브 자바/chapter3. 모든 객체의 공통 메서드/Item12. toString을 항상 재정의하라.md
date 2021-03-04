## Item12. toString을 항상 재정의하라
`Object`에 기본적으로 정의되어있는 `toString( )`을 통해 객체를 출력하면 아래와 같이 해시코드를 출력하게 된다.
```java
public class User
{
    private Long id;
    private String name;
    private Integer age;
}
```
이 3개의 필드가 있는 `User`클래스를 출력해보자.
```java
var oldUser = new OldUser(1L, "Harry", 17);
System.out.println("oldUser = " + oldUser);

// Result
> oldUser = chapter3.item12.OldUser@7f690630
```
결과를 보면 알 수 있지만 그닥 유용한 정보를 주지 않는다.

이제 `toString( )`을 재정의하여 출력해보자.

```java
@Override
public String toString( )
{
    return "User{id=" + id +", name='" + name + "', age=" + age +'}';
}
```
```java
var user = new User(1L, "Harry", 17);
System.out.println("user = " + user);

result
> user = User{id=1, name='Harry', age=17}
```

대부분의 경우에서 아마도 재정의된 `toString( )`이 쓸모있을 것이고

이렇게 인간이 읽기 쉽고 자세한 정보를 출력정보는 디버깅을 쉽게 만든다.

직접 `getter`를 이용해 값을 꺼내는 작업을 통해 출력할 수도 있지만,

`toString( )`은 `Object`에 정의된 공용 API로서 다른 클래스 내부에서 사용되는 경우가 많다.

매번 `toString( )`을 재정의하는 것은 좋은 습관이다.

다른 라이브러리를 사용할 때 양질의 출력로그를 얻을 수 있고 디버깅 시간을 줄여줄 것이다.

### Lombok toString 이용하기
롬복 라이브러리를 사용하는 경우 `Class`위에 `@ToString` 애너테이션을 추가하면 간편하게 `toString( )`을 재정의할 수 있다.
```java
@ToString
class LombokUser
{
    private Long id;
    private String name;
    private Integer age;
}
```
```java
var lombokUser = new LombokUser(1L, "Harry", 17);
System.out.println("lombokUser = " + lombokUser);

result
> lombokUser = LombokUser(id=1, name=Harry, age=17)
```