package wayc.backend.order.domain;

//일단은 디비에 저장하지 않아서 생명주기가 동일함.

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {
    private String major;
    private String detail;
    private String zipcode;

    protected Address() {
    }

    public Address(String major, String detail, String zipcode) {
        this.major = major;
        this.detail = detail;
        this.zipcode = zipcode;
    }
}

