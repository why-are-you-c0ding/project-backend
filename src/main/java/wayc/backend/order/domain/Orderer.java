package wayc.backend.order.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Getter
@Embeddable
public class Orderer {

    private Long memberId;

    public Orderer(Long memberId) {
        this.memberId = memberId;
    }

    protected Orderer() {}
}
