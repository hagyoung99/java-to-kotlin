package travelator.mobile

class Application(private val preferences: UserPreferences) {
    fun showWelcome() {
        WelcomeView(preferences).show()
    }

    fun editPreferences() {
        PreferencesView(preferences).show()
    }
}
/*
가변 객체에 대한 불변 참조를 불변 객체에 대한 가변 참조로 변경한다. 그러기 위해서 PreferencesView 에서 작업한다.
 */