package wayc.backend.shop.domain;

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
public class Shop extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private ShopOwner owner;

    private String name;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<>();

    @Builder
    public Shop(Long ownerId, String name) {
        this.owner = new ShopOwner(ownerId);
        this.name = name;
        this.items = new ArrayList<>();;
    }

    public void addItem(Item item) {
        this.items.add(item);
        item.add(this);
    }
}
