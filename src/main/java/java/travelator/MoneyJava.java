package java.travelator;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

public class MoneyJava {
    private final BigDecimal amount;
    private final Currency currency;

    private MoneyJava(BigDecimal amount, Currency currency){    //1 : 비공개 생성자.
        // 다른 클래스들은 정적인 Money.of 메서드를 호출해서 Money 의 값을 얻어야한다.
        this.amount = amount;
        this.currency = currency;
    }

    //해당 통화의 보조 통화 단위와 일치하도록 보장한다.
    public static MoneyJava of(BigDecimal amount, Currency currency){   //2 : 자바빈 관습에 따라 amount 와 currency 프로퍼티를 노출한다.
        return new MoneyJava(
                amount.setScale(currency.getDefaultFractionDigits()),
                currency
        );
    }

    public BigDecimal getAmount() { //2
        return amount;
    }

    public Currency getCurrency() { //2
        return currency;
    }

    @Override
    public boolean equals(Object o) {   //3
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoneyJava money = (MoneyJava) o;
        return amount.equals(money.amount) &&
                currency.equals(money.currency);
    }

    @Override
    public int hashCode() {             //3
        return Objects.hash(amount, currency);
    }

    @Override
    public String toString() {          //4
        return amount.toString() + " " + currency.getCurrencyCode();
    }
    
    public MoneyJava add(MoneyJava that){       //5 : 통화 값을 계산할 수 있는 연산을 제공한다.
        // BigDecimal.add 를 통한 연산 결과가 이미 최소 단위를 만족해 add 메서드는 생성자를 직접 호출해서
        // 새로운 Money 객체를 만든다.
        if(!this.currency.equals(that.currency)){
            throw new IllegalArgumentException(
                    "cannot add Money values of different currencies"
            );
        }
        return new MoneyJava(this.amount.add(that.amount), this.currency);
    }
}
