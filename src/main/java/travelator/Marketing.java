package travelator;

import travelator.EmailAddress;

public class Marketing {
    public static boolean isHotmailAddress(EmailAddress address){
        //코틀린 코드에서 getter 가 보이지는 않지만 생성되어있는걸 알 수 있다.
        return address.getDomain().equalsIgnoreCase("hotmail.com");
    }
}
