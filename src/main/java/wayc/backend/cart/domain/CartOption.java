package wayc.backend.cart.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.common.base.BaseEntity;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class CartOption extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "cart_option_group_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CartOptionGroup cartOptionGroup;

    private String name;

    private Integer price;

    public CartOption(CartOptionGroup cartOptionGroup, String name, Integer price) {
        this.cartOptionGroup = cartOptionGroup;
        this.name = name;
        this.price = price;
    }
}

