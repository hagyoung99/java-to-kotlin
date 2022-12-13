package travelator

import java.math.BigDecimal
import java.util.*

class Money //데이터 클래스로 하게 되면 데이터 클래스에 있는 copy 메서드가 항상 공개이기 때문에 불변 조건을 지키지 않는 새 Money 값을 만들 수 있게 된다.
//따라서 Money 객체는 코틀린 데이터 클래스를 사용해 구현할 수 없다. => 프로퍼티 사이에 불변 조건을 유지해야 하는 값 타입에는 데이터 클래스를 사용해 정의하지 말라.
private constructor(//2
    val amount: BigDecimal, //2
    val currency: Currency
) {

    override fun equals(o: Any?): Boolean {   //3
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val money = o as Money
        return amount == money.amount && currency == money.currency
    }

    override fun hashCode(): Int {             //3
        return Objects.hash(amount, currency)
    }

    override fun toString(): String {          //4
        return amount.toString() + " " + currency.currencyCode
    }

    fun add(that: Money): Money {       //5
        require(currency == that.currency) { "cannot add Money values of different currencies" }
        return Money(amount.add(that.amount), currency)
    }

    companion object {
        @JvmStatic
        fun of(amount: BigDecimal, currency: Currency): Money {   //2
            return Money(
                amount.setScale(currency.defaultFractionDigits),
                currency
            )
        }
    }
}