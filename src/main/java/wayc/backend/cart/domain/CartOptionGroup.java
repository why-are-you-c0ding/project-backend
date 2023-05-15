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
public class CartOptionGroup extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cartOptionGroup")
    private List<CartOption> cartOptions = new ArrayList<>();

    private String name;

    @Builder
    public CartOptionGroup(List<CartOption> cartOptions, String name) {
        this.cartOptions = cartOptions;
        this.name = name;
    }
}
