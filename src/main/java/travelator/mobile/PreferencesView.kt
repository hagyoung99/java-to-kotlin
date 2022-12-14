package travelator.mobile

import java.util.*

class PreferencesView(private val preferences: UserPreferences) : View() {
    private val greetingPicker = GreetingPicker()
    private val localePicker = LocalePicker()
    private val currencyPicker = CurrencyPicker()

    //override fun show() {
    fun showModal() : UserPreferences{
        greetingPicker.greeting = preferences.greeting
        localePicker.locale = preferences.locale
        currencyPicker.currency = preferences.currency
        //super.show()    //View 에 있는 메서드를 오버라이드하며 뷰를 눈에 보이게 만들고 뷰가 끝날 때 까지 호출하는 쪽 스레드를 불록시킨다.
        //상태 변경을 막기 위해서는 변경한 내용을 적용한 UserPreferences 복사본을 돌려주는 버전이 필요하다.
        show()
        return preferences
    }

    protected fun onGreetingChange() {
        preferences.greeting = greetingPicker.greeting!!
    }

    protected fun onLocaleChange() {
        preferences.locale = localePicker.locale!!
    }

    protected fun onCurrencyChange() {
        preferences.currency = currencyPicker.currency!!
    }
}

internal class GreetingPicker {
    var greeting: String? = null
}

internal class LocalePicker {
    var locale: Locale? = null
}

internal class CurrencyPicker {
    var currency: Currency? = null
}