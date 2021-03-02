## Item10. equals는 일반 규약을 지켜 재정의하라

두 객체의 논리적인 동등성을 비교하기 위해서 `equals( )`를 사용하게 된다.

그러나 재정의되지 않은 `equals( )`는 주소값을 통한 비교만을 하게되므로 논리적인 동등성 비교를 보장해주지 않는다.

그러므로 두 객체 사이의 논리적인 동등성을 비교하고 싶다면 `equals( )`를 재정의해주도록 하자.

---
### equals 재정의 시에 따라야 할 일반 규약
`equals( )`가 항상 예상하는 대로 작동하고 또한 리스코프 원칙을 만족시키기 위해서 `Object`클래스에는 아래와 같은 재정의 시에 따라야 할 규약 몇가지를 정해놓았다.

- **반사성** : `null`이 아닌 모든 참조 값에 대하여, `x.equals(x)`는 `true`다.


- **대칭성** : `null`이 아닌 모든 참조 값에 대하여, `x.equals(y)`가 `true`면 `y.equals(x)`도 `true`다.


- **추이성** : `null`이 아닌 모든 참조 값에 대하여, `x.equals(y)`가 `true`고 `y.equals(z)`도 `true`면 `x.equals(z)`도 `true`다


- **일관성** : `null`이 아닌 모든 참조 값에 대하여, `x.equals(y)`를 반복해서 호출하면 항상 같은 결과를 반환한다.


- **null-아님** : `null`이 아닌 모든 참조 x에 대하여, `x.equals(null)`은 `false`다.


---
### 양질의 equals를 얻기 위한 방법

1. `==`연산자를 사용해 입력이 자기 자신의 참조인지 확인하기


2. `instanceof`연산자로 입력이 올바른 타입인지 확인하기


3. 타입캐스팅으로 입력을 올바른 타입으로 변환하기


4. 입력 객체와 자기 자신의 대응되는 비교하고자 하는 모든 필드들이 일치하는지 하나씩 검사하기

---
### equals 만들어 보기
아래 데이터를 담고 있는 `User`클래스가 있다. 
```java
public class User
{
    private Long    id;
    private String  name;
    private Integer age;
    private String  email;
    private Double  score;
}
```
같은 값을 가지고 있는 객체라면 같은 객체로 검사하는 `equals( )`메서드를 재정의해보자.

```java
@Override
public boolean equals(Object o)
{   
    // 1단계 : 자기 자신의 참조인지 확인하기
    if (this == o) { return true; }
    
    // 2단계 : 올바른 타입인지 확인하기
    if (!(o instanceof User)) { return false; }
    
    // 3단계 : 타입캐스팅
    User user = (User)o;
    
    // 4단계 : 모든 필드의 값을 하나씩 비교하기
    return id.equals(user.id)
           && name.equals(user.name)
           && age.equals(user.age)
           && email.equals(user.email)
           && Double.compare(score, user.score) == 0;
}
```
---

### equals 재정의 할 때 팁과 주의점

- 재정의 시에 `equals(Object o)`와 같이 파라미터는 **항상** `Object`타입이어야 한다.

다른 파라미터를 사용하는 경우 **오버로딩**이 되므로 정상적인 작동을 기대하기 어려워진다.


- 필드의 비교는 비용이 저렴한 연산, 다를 가능성이 높은 필드부터 하자.

자바에서는 `Lazy Evaluation`을 지원하므로 불필요한 연산을 피하기 위해서 다를 가능성이 높은 필드를 먼저 비교하고, 또한 비용이 비싼 연산은 후순위로 미루자.

- 재정의에 사용하는 필드들은 논리적 동치성을 비교하는데 사용하는 필드만 사용하자


- 테스트 코드를 작성하여 `equals( )`를 검증 하도록 하자.