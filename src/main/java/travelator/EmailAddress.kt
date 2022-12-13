package travelator

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

    override fun toString(): String {//데이터 클래스에서 지원해주지만 리턴을 설정하고 싶기 때문에 따로 정의한다.
        return "$localPart@$domain"
    }


    companion object {  //동반객체: static 을 대신해 최상위 함수와 객체 선언을 사용할 수 있게 해준다.
        @JvmStatic  //java 의 static 처럼 사용할 수 있게 해준다.
        fun parse(value: String): EmailAddress { //2
            val atIndex = value.lastIndexOf('@')
            require(!(atIndex < 1 || atIndex == value.length - 1)) {
                "EmailAddress must be two parts separated by @" }
            return EmailAddress(
                value.substring(0, atIndex),
                value.substring(atIndex + 1)
            )
        }
    }
}