package wayc.backend.shop.domain;

import lombok.*;

import wayc.backend.common.domain.BaseEntity;
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

    @JoinColumn(name = "shop_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
    private List<OptionGroup> optionGroups = new ArrayList<>();

    private String name;

    private String imageUrl;

    private String information;

    private String category;

    @Builder
    public Item(Shop shop,
                List<OptionGroup> optionGroups,
                String name,
                String imageUrl,
                String information,
                String category) {
        this.shop = shop;
        this.optionGroups = optionGroups;
        this.name = name;
        this.imageUrl = imageUrl;
        this.information = information;
        this.category = category;
        optionGroups.forEach(optionGroup ->  optionGroup.add(this));
    }

    public void add(Shop shop) {
        this.shop = shop;
    }

    public void registered() {
        Events.raise(new ItemRegisteredEvent(optionGroups));
    }
}
