package wayc.backend.order.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Embeddable
public class OrderOption{

    private String optionName;
    private Integer price;

    public OrderOption(String optionName, Integer price) {
        this.optionName = optionName;
        this.price = price;
    }
}

//수정할 사항이 없을것이므로 값 타입으로 구현.