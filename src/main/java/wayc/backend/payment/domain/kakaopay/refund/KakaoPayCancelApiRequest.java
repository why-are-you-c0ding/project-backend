package wayc.backend.payment.infrastructure.kakaopay.refund;


import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class KakaoPayCancelApiRequest {

    private String adminKey;
    private String cid;
    private String cidSecret;
    private String tid;
    private Integer cancelAmount;
    private Integer cancelTaxFreeAmount;
    private Integer cancelVatAmount;
    private Integer cancelAvailableAmount;
    private String payload;


    public KakaoPayCancelApiRequest(String adminKey, String cid,
                                    String cidSecret, String tid,
                                    Integer cancelAmount,
                                    Integer cancelTaxFreeAmount,
                                    Integer cancelVatAmount,
                                    Integer cancelAvailableAmount,
                                    String payload) {
        this.adminKey = adminKey;
        this.cid = cid;
        this.cidSecret = cidSecret;
        this.tid = tid;
        this.cancelAmount = cancelAmount;
        this.cancelTaxFreeAmount = cancelTaxFreeAmount;
        this.cancelVatAmount = cancelVatAmount;
        this.cancelAvailableAmount = cancelAvailableAmount;
        this.payload = payload;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String adminKey;
        private String cid;
        private String cidSecret;
        private String tid;
        private Integer cancelAmount;
        private Integer cancelTaxFreeAmount;
        private Integer cancelVatAmount;
        private Integer cancelAvailableAmount;
        private String payload;

        public Builder setCid(String cid) {
            this.cid = cid;
            return this;
        }

        public Builder setCidSecret(String cidSecret) {
            this.cidSecret = cidSecret;
            return this;
        }

        public Builder setAdminKey(String adminKey) {
            this.adminKey = adminKey;
            return this;
        }

        public Builder setTid(String tid) {
            this.tid = tid;
            return this;
        }

        public Builder setCancelAmount(Integer cancelAmount) {
            this.cancelAmount = cancelAmount;
            return this;
        }

        public Builder setCancelTaxFreeAmount(Integer cancelTaxFreeAmount) {
            this.cancelTaxFreeAmount = cancelTaxFreeAmount;
            return this;
        }

        public Builder setCancelVatAmount(Integer cancelVatAmount) {
            this.cancelVatAmount = cancelVatAmount;
            return this;
        }

        public Builder setCancelAvailableAmount(Integer cancelAvailableAmount) {
            this.cancelAvailableAmount = cancelAvailableAmount;
            return this;
        }

        public Builder setPayload(String payload) {
            this.payload = payload;
            return this;
        }

        public KakaoPayCancelApiRequest build() {
            return new KakaoPayCancelApiRequest(
                    adminKey,
                    cid,
                    cidSecret,
                    tid,
                    cancelAmount,
                    cancelTaxFreeAmount,
                    cancelVatAmount,
                    cancelAvailableAmount,
                    payload
            );
        }
    }
}
