package wayc.backend.cart.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.common.domain.BaseEntity;

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

    public void addCartLineItem(CartLineItem lineItem) {

        //TODO 카트 라인 아이템과 상점의 옵션이 동일한지 검증하는 로직이 필요함.

        cartLineItems.add(lineItem);
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
}
