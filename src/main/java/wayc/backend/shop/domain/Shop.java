package wayc.backend.shop.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import wayc.backend.common.base.BaseEntity;

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

    private Long ownerId;

    private String shopName;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<>();

    @Builder
    public Shop(Long ownerId, String shopName) {
        this.ownerId = ownerId;
        this.shopName = shopName;
        this.items = new ArrayList<>();;
    }
}
