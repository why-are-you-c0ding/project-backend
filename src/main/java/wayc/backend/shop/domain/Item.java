package wayc.backend.shop.domain;

import lombok.*;

import wayc.backend.common.base.BaseEntity;

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

    @JoinColumn(name = "item_id")
    @OneToMany(cascade = CascadeType.ALL)
    private List<OptionGroupSpecification> optionGroupSpecifications = new ArrayList<>();

    private String name;

    private String imageUrl;

    private String information;

    private String category;

    @Builder
    public Item(Shop shop, List<OptionGroupSpecification> optionGroupSpecifications, String name, String imageUrl, String information, String category) {
        this.shop = shop;
        this.optionGroupSpecifications = optionGroupSpecifications;
        this.name = name;
        this.imageUrl = imageUrl;
        this.information = information;
        this.category = category;
    }
}
