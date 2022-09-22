package wayc.backend.shop.domain;

import lombok.*;

import wayc.backend.common.base.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@ToString
public class Item extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "shop_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<OptionGroupSpecification> optionGroupSpecifications = new ArrayList<>();

    private String name;

    @Builder
    public Item(Shop shop, List<OptionGroupSpecification> optionGroupSpecifications, String name) {
        this.shop = shop;
        this.optionGroupSpecifications = optionGroupSpecifications;
        this.name = name;
    }
}
