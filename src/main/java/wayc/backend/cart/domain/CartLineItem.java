package wayc.backend.cart.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import wayc.backend.common.domain.BaseEntity;
import wayc.backend.shop.domain.valid.ItemComparator;
import wayc.backend.shop.domain.valid.OptionGroupComparator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class CartLineItem extends BaseEntity implements ItemComparator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long itemId;

    @JoinColumn(name = "cart_id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    @ManyToOne(fetch = FetchType.LAZY)
    private Cart cart;

    private String name;

    private Integer count;

    private String imageUrl;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cartLineItem")
    private List<CartOptionGroup> cartOptionGroups = new ArrayList<>();

    @Builder
    public CartLineItem(Long itemId,
                        Cart cart,
                        String name,
                        Integer count,
                        List<CartOptionGroup> cartOptionGroups,
                        String imageUrl) {
        this.itemId = itemId;
        this.cart = cart;
        this.name = name;
        this.count = count;
        this.cartOptionGroups = cartOptionGroups;
        this.imageUrl = imageUrl;
        cartOptionGroups.forEach(optionGroup -> optionGroup.addCartLineItem(this));
    }

    public void delete(){
        cart = null; //이거 빼 먹어서 고생함. 양방향 연관관계 잘 체크하자.
        super.delete();;
    }

    public void update(Integer count) {
        this.count = count;
    }

    public void addCart(Cart cart) {
        this.cart = cart;
    }

    @Override
    public List<OptionGroupComparator> getComparisonOrderOptionGroups() {
        return cartOptionGroups.stream()
                .map(cartOptionGroup ->  (OptionGroupComparator) cartOptionGroup)
                .collect(Collectors.toList());
    }
}
