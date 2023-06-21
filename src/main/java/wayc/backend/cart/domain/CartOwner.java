package wayc.backend.cart.domain;

import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Getter
@Embeddable
public class CartOwner {

    private Long memberId;

    public CartOwner(Long memberId) {
        this.memberId = memberId;
    }

    protected CartOwner() {}
}
