## Item9. try-with-resources를 사용하라

자바에서는 ```close( )```를 이용하여 직접적으로 닫아줘야 하는 자원들이 있다.

```Scanner```, ```InputStream```, ```Connection```이 그 대표적이 예시라고 볼 수 있다.

아래 데이터베이스 ```Connection```을 가져오는 코드를 살펴보자
```java
public void add(String string)
{
    Connection        connection        = null;
    PreparedStatement preparedStatement = null;
    
    try {
        connection        = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        preparedStatement = connection.prepareStatement(SQL);
        중략...
    }
    catch (SQLException exception) { exception.printStackTrace( ); }
    finally {
        preparedStatement.close( );
        connection.close( );
    }
}
```

```Connection```과 ```PreparedStatement``` 모두 ```finally```구문안에서 ```close( )```로 닫아줘야 한다.

그러나 위 코드에서 예기치 못한 문제점이 발생할 수 있다.

```PreparedStatement```를 닫는 과정에서 에러가 발생한다면 ```Connection```은 닫히지 않는다.

그러므로 아래 코드와 같이 예외처리를 추가적으로 해주어야 한다.
```java
finally {
    try { preparedStatement.close( ); }
    catch (SQLException e) { e.printStackTrace( ); }
    try { connection.close( ); }
    catch (SQLException e) { e.printStackTrace( ); }
}
```
---

코드를 보면서 느껴졌겠지만 매우 복잡하고 가독성이 좋지 않다. 또한 실수를 할 여지가 매우 커지게 된다.

이런 문제를 해결하기 위해 `Java 7`부터는 새로 추가된 `try-with-resources`를 사용할 것을 권장한다.

`AutoCloseable` 인터페이스를 구현하는 구현체를 `try`와 `{ ... }` 블럭사이에서 선언하게 되면 자동적으로 `close( )`가 호출된다.

```java
// resource는 자동적으로 닫히게 된다.
try (AutoCloseable resource = new Resource( )) {
    ...
}
```

그러면 `try-with-resources`를 사용해서 기존 코드를 리팩터링 해보자.
```java
public void add(String string)
{
    try ( // ;를 사용해서 여러개의 자원을 함께 정의할 수 있다.
         var connection        = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
         var preparedStatement = connection.prepareStatement(SQL)
    ) {
        preparedStatement.executeUpdate( );
    }
    catch (SQLException exception) {
        exception.printStackTrace( );
    }
}
```
에러처리를 위한 `catch`문 역시 사용 가능하며 자원을 한번에 여러개 정의할 수도 있다.

복잡하고 가독성을 해치는 코드블럭을 더 이상 보지 않아도 될 것이다.

---
#### Java9 - 향상된 try-with-resources
기존 `try-with-resources`는 `try`구문에서 선언과 초기화를 모두 해주어야 한다는 단점이 있었다.

`Java 9`부터는 미리 초기화 된 `final` 혹은 `effectively final` 변수를 이용하는 것도 가능해졌다.

```java
var bufferedReader1 = new BufferedReader(new FileReader("C:\\number.txt"));
var bufferedReader2 = new BufferedReader(new FileReader("C:\\abc.txt"));
try (bufferedReader1; bufferedReader2) {
    ...
}
```
