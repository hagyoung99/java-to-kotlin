package java.travelator.mobile;

import travelator.mobile.UserPreferences;
import travelator.mobile.View;

public class WelcomeViewJava extends View {

    private final UserPreferencesJava preferences;

    public WelcomeViewJava(UserPreferencesJava preferences) {
        this.preferences = preferences;
    }
}