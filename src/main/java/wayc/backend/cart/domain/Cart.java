package wayc.backend.cart.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.common.domain.BaseEntity;
import wayc.backend.shop.domain.port.ItemComparator;
import wayc.backend.shop.domain.port.ItemComparisonValidator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Cart extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartLineItem> cartLineItems = new ArrayList<>();

    public Cart(Long memberId) {
        this.memberId = memberId;
    }

    public void addCartLineItem(CartLineItem cartLineItem) {
        cartLineItems.add(cartLineItem);
        cartLineItem.addCart(this);
    }

    public void deleteCartLineItem(CartLineItem cartLineItem) {
        cartLineItems.remove(cartLineItem);
        cartLineItem.delete();
    }

    public void update(CartLineItem cartLineItem, Integer count) {
        if(cartLineItems.contains(cartLineItem)){
            cartLineItem.update(count);
        }
    }

    public void place(ItemComparisonValidator<CartLineItem> itemComparisonValidator, CartLineItem lineItem) {
        itemComparisonValidator.validate(lineItem);
    }
}
