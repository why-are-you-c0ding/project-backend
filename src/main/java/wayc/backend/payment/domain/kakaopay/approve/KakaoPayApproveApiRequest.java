package wayc.backend.payment.domain.kakaopay.approve;


import lombok.Getter;

@Getter
public class KakaoPayApproveApiRequest {

    private final String adminKey;
    private final String cid;
    private final String cidSecret;
    private final String tid;
    private final String partnerOrderId;
    private final String partnerUserId;
    private final String pgToken;
    private final String payload;
    private final Integer totalAmount;

    private KakaoPayApproveApiRequest(String adminKey,
                                      String cid,
                                      String cidSecret,
                                      String tid,
                                      String partnerOrderId,
                                      String partnerUserId,
                                      String pgToken,
                                      String payload,
                                      Integer totalAmount) {
        this.adminKey = adminKey;
        this.cid = cid;
        this.cidSecret = cidSecret;
        this.tid = tid;
        this.partnerOrderId = partnerOrderId;
        this.partnerUserId = partnerUserId;
        this.pgToken = pgToken;
        this.payload = payload;
        this.totalAmount = totalAmount;
    }

    public static Builder Builder() {
        return new Builder();
    }

    static class Builder {

        private String adminKey;
        private String cid;
        private String cidSecret;
        private String tid;
        private String partnerOrderId;
        private String partnerUserId;
        private String pgToken;
        private String payload;
        private Integer totalAmount;

        public Builder setAdminKey(String adminKey) {
            this.adminKey = adminKey;
            return this;
        }

        public Builder setCid(String cid) {
            this.cid = cid;
            return this;
        }

        public Builder setCidSecret(String cidSecret) {
            this.cidSecret = cidSecret;
            return this;
        }

        public Builder setTid(String tid) {
            this.tid = tid;
            return this;
        }

        public Builder setPartnerOrderId(String partnerOrderId) {
            this.partnerOrderId = partnerOrderId;
            return this;
        }

        public Builder setPartnerUserId(String partnerUserId) {
            this.partnerUserId = partnerUserId;
            return this;
        }

        public Builder setPgToken(String pgToken) {
            this.pgToken = pgToken;
            return this;
        }

        public Builder setPayload(String payload) {
            this.payload = payload;
            return this;
        }

        public Builder setTotalAmount(Integer totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }

        public KakaoPayApproveApiRequest build() {
            return new KakaoPayApproveApiRequest(
                    adminKey,
                    cid,
                    cidSecret,
                    tid,
                    partnerOrderId,
                    partnerUserId,
                    pgToken,
                    payload,
                    totalAmount
            );
        }
    }
}
