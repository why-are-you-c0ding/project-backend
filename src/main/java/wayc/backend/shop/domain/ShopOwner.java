package wayc.backend.shop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Getter
@Embeddable
public class ShopOwner {

    private Long memberId;

    public ShopOwner(Long memberId) {
        this.memberId = memberId;
    }

    protected ShopOwner(){}
}
