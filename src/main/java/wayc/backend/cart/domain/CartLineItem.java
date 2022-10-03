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
public class CartLineItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long itemId;

    @JoinColumn(name = "card_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Cart cart;

    private String name;

    private Integer count;

    @OneToMany(mappedBy = "cartLineItem")
    private List<CartOptionGroup> cartOptionGroups = new ArrayList<>();

    public CartLineItem(Long itemId, Cart cart, String name, Integer count, List<CartOptionGroup> cartOptionGroups) {
        this.itemId = itemId;
        this.cart = cart;
        this.name = name;
        this.count = count;
        this.cartOptionGroups = cartOptionGroups;
    }
}
