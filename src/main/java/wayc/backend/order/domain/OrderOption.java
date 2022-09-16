package wayc.backend.order.domain;

import lombok.Getter;
import wayc.backend.common.base.BaseEntity;

import javax.persistence.*;

@Getter
@Embeddable
public class OrderOption extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer money;
}
