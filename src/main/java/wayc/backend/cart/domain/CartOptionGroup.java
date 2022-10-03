package wayc.backend.cart.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.common.base.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class CartOptionGroup extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "cart_line_item_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CartLineItem cartLineItem;

    @OneToMany(mappedBy = "cartOptionGroup")
    private List<CartOption> cartOptions = new ArrayList<>();

    private String name;

    public CartOptionGroup(CartLineItem cartLineItem, List<CartOption> cartOptions, String name) {
        this.cartLineItem = cartLineItem;
        this.cartOptions = cartOptions;
        this.name = name;
    }
}
