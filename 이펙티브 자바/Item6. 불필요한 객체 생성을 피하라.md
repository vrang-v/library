## Item6. 불필요한 객체 생성을 피하라

---
똑같은 기능을 수행하는 객체를 매번 생성하는 것은 대부분의 경우 불필요한 자원 낭비로 이어진다.

아래 코드는 불필요한 객체 생성을 하는 극단적인 예시이다. 한번 살펴보자.
```java
String james1 = new String("james");
String james2 = new String("james");
```
자바의 경우 ```String```을 생성하는 방법에는 크게 2가지가 있다.
- 적절한 생성 : ```String``` 리터럴로 생성


- 잘못된 생성 : ```new``` 키워드를 사용한 생성

```String``` 리터럴로 생성되는 문자열은 힙 영역 내부에 있는 ```String Constant Pool```에 생성이 되고 같은 문자열은 같은 객체를 가리킨다.

반면, ```new```로 문자열을 만드는 경우 매번 새로운 ```String``` 객체를 생성하므로 자원의 낭비로 이어지게 된다.

따라서 위의 코드는 아래처럼 바뀌는 것이 적절할 것이다.
```java
String james1 = "james";
String james2 = "james";
```
---
이와 비슷한 사례로 ```Boolean``` 객체를 생성하는 경우가 있다.

```Boolean```은 정적 필드 멤버로 ```TRUE```, ```FALSE```를 관리하고 있으므로 

매번 새로운 객체를 생성하는 것이 아니라 정적 멤버를 재사용하는 것이 바람직하다.

```java
Boolean trueValue  = new Boolean("true");   // (x)
Boolean falseValuse = Boolean.FALSE;        // (o)
```
---
연산량이 많은 경우에 ```primitive```타입(원시 타입) 대신 ```wrapper```타입을 사용하는 경우도 이에 해당한다고 볼 수 있다.

```java
public static Long sum( )
{
    Long sum = 0L;
    for (long i = 0; i < Integer.MAX_VALUE; i++) {
        sum += i;
    }
    return sum;
}
```
위 경우에 불필요한 ```Long``` 인스턴스가 ```Integer.MAX_VAULE```만큼이 생성되게 된다.

---
마지막으로 값이 비싼 객체의 생성을 할 때도 매번 객체를 생성하는 것은 피하자.
```java
public static boolean isRomanNumeral(String s)
{
    return s.matches("^(?=.)M*(C[MD]|D?C{0,3})(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
}
```
위 코드에서 입력받은 정규표현식에 해당하는 부분은 유한 상태 머신을 만들기 때문에 인스턴스 생성 비용이 높다.

그러나 위 함수가 호출될 때마다 새로 생성하고 버려지는 일이 발생하게 된다.

```java
public static final Pattern ROMAN = Pattern.compile("^(?=.)M*(C[MD]|D?C{0,3})(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
```
```java
public static boolean isRomanNumeral(String s)
{
    return ROMAN.matcher(s).matches( );
}
```
이때 값이 비싼 객체를 직접 생성해 캐싱해두고 재사용하게 되면 불필요한 객체의 생성을 피할 수 있다.