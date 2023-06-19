package wayc.backend.cart.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wayc.backend.common.domain.BaseEntity;
import wayc.backend.shop.domain.port.OptionGroupComparator;
import wayc.backend.shop.domain.port.OptionGroupValidator;

import javax.persistence.*;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class CartOptionGroup extends BaseEntity implements OptionGroupComparator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "cart_line_item_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CartLineItem cartLineItem;

    @Embedded
    private CartOption cartOption;

    private String name;

    @Builder
    public CartOptionGroup(CartOption cartOption, String name) {
        this.cartOption = cartOption;
        this.name = name;
    }

    public void addCartLineItem(CartLineItem cartLineItem){
        this.cartLineItem = cartLineItem;
    }

    public OptionGroupValidator convertToOptionGroupValidator() {
        return new OptionGroupValidator(name, cartOption.convertToOptionValidator());
    }
}
