package wayc.backend.shop.domain;

import lombok.*;

import wayc.backend.common.domain.BaseEntity;
import wayc.backend.common.domain.Money;
import wayc.backend.common.event.Events;

import wayc.backend.shop.domain.event.ItemRegisteredEvent;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Item extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "shop_id", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
    private List<OptionGroup> optionGroups = new ArrayList<>();

    private String name;

    private String imageUrl;

    private String information;

    private String category;

    private Money price;

    @Builder
    public Item(Long id,
                Shop shop,
                List<OptionGroup> optionGroups,
                String name,
                String imageUrl,
                String information,
                String category,
                Integer price) {
        this.id = id;
        this.shop = shop;
        this.optionGroups = optionGroups;
        this.name = name;
        this.imageUrl = imageUrl;
        this.information = information;
        this.category = category;
        this.price = Money.from(price);
        optionGroups.forEach(optionGroup ->  optionGroup.add(this));
    }

    public void add(Shop shop) {
        this.shop = shop;
    }

    public void registered() {
        Events.raise(new ItemRegisteredEvent(optionGroups));
    }
}
