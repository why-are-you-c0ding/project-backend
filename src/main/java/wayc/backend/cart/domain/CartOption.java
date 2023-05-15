package wayc.backend.cart.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.common.domain.BaseEntity;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class CartOption extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer price;

    @JoinColumn(name = "cart_option_group_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CartOptionGroup cartOptionGroup;

    @Builder
    public CartOption(String name, Integer price) {
        this.name = name;
        this.price = price;
    }
}

