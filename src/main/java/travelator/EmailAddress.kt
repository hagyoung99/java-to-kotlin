package travelator

import java.lang.IllegalArgumentException
import java.util.*

//val 이 붙으면 프로퍼티로 취급한다.
//생성자를 자동으로 생성한다.
//getter 와 setter도 자동으로 지원해준다.
data class EmailAddress(    //data 를 붙이게되면 컴파일러가 equals 와 hashCode, toString 메서드를 자동으로 생성해준다.
    val localPart: String,
    val domain: String
) {

    /* 데이터 클래스를 사용하게 되면서 생략해도 되는 코드가 된다.
    override fun equals(o: Any?): Boolean {   //5
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val that = o as EmailAddress
        return localPart == that.localPart && domain == that.domain
    }

    override fun hashCode(): Int {             //5
        return Objects.hash(localPart, domain)
    }
    */

    override fun toString(): String = "$localPart@$domain" //단일식 함수


    companion object {  //동반객체: static 을 대신해 최상위 함수와 객체 선언을 사용할 수 있게 해준다.
        /*@JvmStatic  //java 의 static 처럼 사용할 수 있게 해준다.
        fun parse(value: String): EmailAddress { //2
            val atIndex = value.lastIndexOf('@')    //대입문
            require(!(atIndex < 1 || atIndex == value.length - 1)) {    //require 호출도 문이다.
                "EmailAddress must be two parts separated by @" }
            return EmailAddress(        //EmaillAdress 생성은 단일식이며 value 와 atIntex 에 의존한다.
                value.substring(0, atIndex),
                value.substring(atIndex + 1)
            )
        }*/

        //9.1 atIndex 의 대입문은 계산으로 모두 같은결과가 나오기 때문에 해당 부분을 인라인으로 바꾼다.
        // 결과는 동일하지만 require 호출을 처리해야하고, 코드가 어려워지기 때문에 사용하지 않는다.
        /*@JvmStatic  //java 의 static 처럼 사용할 수 있게 해준다.
        fun parse(value: String): EmailAddress { //2
            require(!(value.lastIndexOf('@') < 1 || value.lastIndexOf('@') == value.length - 1)) {    //require 호출도 문이다.
                "EmailAddress must be two parts separated by @" }
            return EmailAddress(        //EmaillAdress 생성은 단일식이며 value 와 atIntex 에 의존한다.
                value.substring(0, value.lastIndexOf('@')),
                value.substring(value.lastIndexOf('@') + 1)
            )
        }*/

        /*
        9.2 테이크 2: 새 함수 도입하기
        1) atIndex 이후를 새 함수로 만들면서 EmailAddress 자체는 단일식이 된다. 하지만 emailAddress 가 단일식이 아니다.
        2) require (값이 false 인 경우 lazyMessage 를 호출한 결과와 함께 IllegalArgumentException 을 발생시킨다.)를 인라이닝 한다.
        3) emailAddress 함수는 atIndex 를 파라미터로 받는 것 외에는 가치를 더해주지 않기 때문에 이 리팩터링도 성공적이지는 않다.
         */
        /*@JvmStatic
        fun parse(value: String): EmailAddress = emailAddress(value, value.lastIndexOf('@'))
        private fun emailAddress(value: String, atIndex: Int): EmailAddress{
            when {
                !(atIndex < 1 || atIndex == value.length - 1) -> throw IllegalArgumentException("EmailAddress must be two parts separated by @")
                else -> return EmailAddress(
                    value.substring(0, atIndex),
                    value.substring(atIndex + 1)
                )
            }
        }*/


        /*
        9.3 테이크 3:let
        1) atIndex 를 사용해 비교하는 부분을 let 으로 감싸고, return 을 let 밖으로 빼낸다.
        2) let 블록 안의 atIndex 는 제거하고자하는 지역변수이고, 람다 파라미터로 같은 이름을 지정하면 지역변수 대신 람다 파라미터 값을 쓰게 된다.
        3) 그럼에도.. 아직 미완성..
         */
        /*@JvmStatic
        fun parse(value: String): EmailAddress =
            //val atIndex = value.lastIndexOf('@')    //이게 있을 때 람다 파라미터 명에 발생하는 경고는 이름 가림! 해당 부분을 인라인 처리한다!
            value.lastIndexOf('@').let{ atIndex ->
                require(!(atIndex < 1 || atIndex == value.length - 1)) {
                    "EmailAddress must be two parts separated by @" }
                EmailAddress(
                    value.substring(0, atIndex),
                    value.substring(atIndex + 1)
                )
            }*/


        /*
        9.4 테이크 4. 한 걸음 물러서기
        1) return 에 substring 을 별도의 변수로 만들어낸다.
        2) 반환문을 제외한 부분을 함수로 추출한다. Pair 로 묶어 반환 값 두개를 리턴한다.
        3) split 을 파라미터화 한다.
        4) EmailAddress 에 let 을 적용해 단일식 함수로 만들고, split 함수도 let 을 적용해 단일식으로 만든다. (그러면서 표준 라이브러리인 split 과의 충돌을 피하기 위해 이름을 변경한다.)
         */
        @JvmStatic  //java 의 static 처럼 사용할 수 있게 해준다.
        fun parse(value: String): EmailAddress =
            value.splitAroundLast('@').let{ (leftPart, rightPart) ->
                EmailAddress(leftPart, rightPart)
            }
    }
}

private fun String.splitAroundLast(divider: Char): Pair<String, String> =
    lastIndexOf(divider).let{ index ->
        require(!(index < 1 || index == length - 1)) {
            "EmailAddress must be two parts separated by $divider" }
        substring(0, index) to substring(index + 1)
    }