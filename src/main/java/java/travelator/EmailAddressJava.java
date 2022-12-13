package java.travelator;

import java.util.Objects;

public class EmailAddressJava {
    private final String localPart; /*1 : 값은 불변, 따라서 필드를 final 로 선언한다.*/
    private final String domain;    //1

    public static EmailAddressJava parse(String value){ //2 : parse[문자열을 파싱해 EmailAddress 를 만드는 정적 패터리 메서드] 으로 주 생성자를 호출한다.
        var atIndex = value.lastIndexOf('@');
        if(atIndex < 1 || atIndex == value.length() -1)     //메일 주소가 올바르지 않다면 예외처리한다.
            throw new IllegalArgumentException(
                "EmailAddress must be two parts separated by @"
        );
        return new EmailAddressJava(
                value.substring(0, atIndex),
                value.substring(atIndex + 1)
        );
    }

    public EmailAddressJava(String localPart, String domain){   //3 : 필드는 생성자에서 초기화된다.
        this.localPart = localPart;
        this.domain = domain;
    }

    public String getLocalPart() {  //4 : 클래스의 프로퍼티를 구성하는 접근자 메서드
        return localPart;
    }

    public String getDomain() {     //4
        return domain;
    }

    @Override
    public boolean equals(Object o) {   //5 : equals 와 hashCode 메서드는 구현해서 모든 필드가 같을 때 두 주소가 같다고 판정되도록 보장한다.
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailAddressJava that = (EmailAddressJava) o;
        return Objects.equals(localPart, that.localPart) && Objects.equals(domain, that.domain);
    }

    @Override
    public int hashCode() {             //5
        return Objects.hash(localPart, domain);
    }

    @Override
    public String toString() {          //6 : 기존 이메일 주소 형식을 돌려준다.
        return localPart +"@"+ domain;
    }
}
