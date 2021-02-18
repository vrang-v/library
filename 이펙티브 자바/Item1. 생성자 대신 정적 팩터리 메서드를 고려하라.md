## Item1. 생성자 대신 정적 팩터리 메서드를 고려하라

---

#### 클래스의 생성자를 ```private```으로 설정하여  ```new```를 사용한 객체 생성을 제한하는 대신,
####```static``` 팩터리 메서드를 제공하여 객체를 생성할 수 있도록 하자.

---

### 장점 1. 시그니처가 같아 생성자를 만들 수 없는 문제를 해결할 수 있다.

생성자를 이용해 객체를 만들 때, 시그니처가 같은 2개의 생성자를 만들경우 오버로딩이 불가능한 문제가 발생한다..
```java
public class User 
{
    private String id;
    private String name;
}
```
```User```클래스에서, ```id```만 입력받는 생성자와 ```name```만 입력받는 생성자를 동시에 만들 수 없는 문제점이 발생한다..

```java
// 아래 생성자는 중복으로 사용이 불가능하고 하나만 선택가능하다.

public User(String id)
{
    this.id = id;
}

public User(String name)
{
    this.name = name;
}
```

정적 팩터리 메서드를 이용하여 객체를 생성하면 이런 문제점을 해결할 수 있다.

```java
// 팩터리 메서드를 사용한다면 생성자는 접근하지 만들자.
// 객체의 일관성이 깨지고 클라이언트에게 혼란을 주게 된다.

private User( ) { /* Provide clients only static factory methods instead of constructors */ }

public static User newUserById(String id)
{
    User user = new User( );
    user.id = id;
    return user;
}

public static User newUserByName(String name)
{
    User user = new User( );
    user.name = name;
    return user;
}
```
```id```를 입력받아 ```user```를 만들 수 있고, ```name```을 입력받아도 ```user``` 를 만들 수 있다.

### 장점 2. 생성자에 이름을 붙일 수 있다.
생성자의 경우 ~~IDE의 도움을 받을 수는 있지만~~ 생성자의 매개변수가 무엇을 의미하는지 확인하기 어렵다.

```java
User user1 = User.newUserById("abc123");
User user2 = User.newUserByName("John");
```
팩터리 메서드를 통해 위와 같이 객체를 생성할 때 매개변수의 의미를 정확히 나타낼 수 있다.

### 장점 3. 호출될 때마다 인스턴스를 새로 생성하지 않아도 된다.
매번 새로운 객체를 만들지 않아도 되는 경우에(ex. 싱글턴) 미리 만들어 놓은 인스턴스를 재활용 할 수 있다.

```java
public static final Boolean TRUE  = new Boolean(true);
public static final Boolean FALSE = new Boolean(false);

public static Boolean valueOf(boolean b) { return (b ? TRUE : FALSE); }
```
자바 API의 ```Boolean```클래스는 ```TRUE```, ```FALSE``` 두개의 객체를 미리 만들어 놓고

```valueOf( )```팩터리 메서드를 이용해 인스턴스를 재활용한다.

### 장점 4. 하위 타입의 객체를 반환할 수 있다.
클라이언트가 인터페이스의 구현체를 모르더라도 팩터리 메서드를 이용해 객체(구현체)를 생성할 수 있다.

```java
public class Apple implements Company { ... }
```
```java
public class Amazon implements Company { ... }
```
```java
public interface Company
{
    static Apple apple( ) { return new Apple( ); }
    
    static Amazon amazon( ) { return new Amazon( ); }
}
```
클라이언트는 ```Company``` 인터페이스만 이용하여 구현체인 ```Apple```, ```Amazon```을 생성할 수 있다.
```java
Company apple  = Company.apple( );
Company amazon = Company.amazon( );
```

### 장점 5. 매개변수에 따라 다른 클래스의 객체를 반환할 수 있다.
```java
public class Amazon implements Company { ... }
```
```java
public class AmazonWebService implements Company { }
```
```java
public interface Company
{
    static Company amazon( ) { return new Amazon( ); }
    
    static Company amazon(int i) { return new AmazonWebService( ); }
}
```
위 예시에서는, 팩터리 메서드 ```amazon```의 시그니처에 따라 서로 다른 ```Amazon```, ```AmazonWebService``` 타입을 반환한다.

```java
public interface Company
{
    static Company amazon(boolean isAws)
    {
        if (isAws) { return new AmazonWebService( ); }
        else { return new Amazon( ); }
    }   
}
```
또한, 매개변수의 값에 따라서 다른 타입의 객체를 반환할 수 도 있다.

```java
// 메서드 시그니처에 따라 다른 객체가 반환 된다.
Company amazon = Company.amazon( );
Company aws    = Company.amazon(1);

// 매개변수의 값에 따라 다른 객체가 반환 된다.
Company amazon = Company.amazon(false);
Company aws    = Company.amazon(true);
```

### 장점 6. 정적 팩터리 메서드를 작성하는 시점에는 반환할 객체의 클래스가 없어도 된다.

~~추가 예정~~

---
### 단점 1. 정적 팩터리 메서드만을 제공한다면 상속이 불가능하다.
자바에서 클래스의 생성자가 모두 ```private```인 경우 서브 클래스를 생성할 수 없기 때문에

팩터리 메서드만 제공하는 경우 서브 클래스를 만드는 것이 불가능하다.

(~~팩터리 메서드와 생성자를 동시에 제공하는 경우에는 서브 클래스 생성이 가능하지만~~

~~객체의 품질이 나빠질 수 있음을 고려해야 한다.~~)

### 단점 2. 정적 팩터리 메서드는 찾기 어렵다.
API 사용자는 객체를 생성할 때 ```new```를 통해 객체가 생성 되는 것을 기대하기 때문에 

API의 사용법을 모르는 경우에는 혼란을 겪을 수 있다.

또한, Javadoc에 일반 생성자와 달리 팩터리 메서드는 명확히 드러나지 않는 한계점을 가지고 있다.

(~~이런 문제점을 해결한 라이브러리 및 애너테이션이 있을까?~~)

널리 알려진 규약과 명확한 이름으로 메서드 이름을 지어야 단점을 조금이라도 개선할 수 있다.

|이름|설명|예시|
|:---:|:---|:---|
|from|매개변수를 하나 받아서 해당 타입으로 반환|Time time = Time.from(10);|
|of|매개변수를 여러개 받아서 해당 타입으로 반환|Time time = Time.of(10, 30);|
|valueOf|from과 of의 더 자세한 버전|Time time = Time.valueOf(10, 30);|
|instance, getInstance|(싱글턴) 인스턴스를 반환|Service service = Service.getInstance( );|
|create, newInstance|새로운 인스턴스를 반환|User user = User.newUser( );|
|get *TYPE*|getInstance와 같으나 다른 타입을 반환할 때 사용|Company apple = Company.getApple( );|
|new *TYPE*|newInstance와 같으나 다른 타입을 반활할 때 사용|Company amazon = Company.newAmazon( );|
|*TYPE*|get *TYPE*과 new *TYPE*의 축약형|Company tesla = Company.tesla( );|