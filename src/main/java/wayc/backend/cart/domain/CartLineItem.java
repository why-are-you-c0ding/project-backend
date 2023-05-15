package wayc.backend.cart.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import wayc.backend.common.domain.BaseEntity;

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

    @JoinColumn(name = "cart_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Cart cart;

    private String name;

    private Integer count;

    private String imageUrl;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cartOptions")
    private List<CartOptionGroup> cartOptionGroups = new ArrayList<>();

    @Builder
    public CartLineItem(Long itemId, Cart cart, String name, Integer count, List<CartOptionGroup> cartOptionGroups, String imageUrl) {
        this.itemId = itemId;
        this.cart = cart;
        this.name = name;
        this.count = count;
        this.cartOptionGroups = cartOptionGroups;
        this.imageUrl = imageUrl;
    }

    public void delete(){
        cart = null; //이거 빼 먹어서 고생함. 양방향 연관관계 잘 체크하자.
        super.delete();;
    }

    public void update(Integer count) {
        this.count = count;
    }
}
