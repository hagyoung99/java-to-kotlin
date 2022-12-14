package java.travelator.mobile;

import travelator.mobile.PreferencesView;
import travelator.mobile.UserPreferences;
import travelator.mobile.WelcomeView;

public class ApplicationJava {
    private final UserPreferences preferences;

    public ApplicationJava(UserPreferences preferences){
        this.preferences = preferences;
    }

    public void showWelcome(){
        new WelcomeView(preferences).show();
    }
    public void editPreferences(){
        new PreferencesView(preferences).show();
    }
}
