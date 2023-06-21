package wayc.backend.stock.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import wayc.backend.common.domain.BaseEntity;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Stock extends BaseEntity { //옵션과 Stock은 N:M 인듯

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    public Stock(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * 테스트에서만 사용할 것
     */
    public Stock(Long id, Integer quantity) {
        this.id = id;
        this.quantity = quantity;
    }
}
