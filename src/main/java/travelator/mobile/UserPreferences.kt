package travelator.mobile

import java.util.*

class UserPreferences @JvmOverloads constructor(    // @JvmOverloads 애너테이션은 컴파일러가 디폴트 값들을 서로 조합한 생성자를 만들어내게 한다.
    @JvmField var greeting: String = "Hello",   //var 프로퍼티는 변경가능한 가변 데이터라는 의미이다.
    @JvmField var locale: Locale = Locale.UK,
    @JvmField var currency: Currency = Currency.getInstance(Locale.UK)
)
/*
UserPreference 를 어떻게 불변으로 만들 수 있을까? => 변경이 일어나는 위치 바꾸기

 */