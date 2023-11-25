package wayc.backend.payment.infrastructure.kakaopay.find;


import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class KakaoPayFindApiRequest {

    private String adminKey;
    private String cid;
    private String cidSecret;
    private String tid;

    private KakaoPayFindApiRequest(String adminKey,
                                   String cid,
                                   String cidSecret,
                                   String tid) {
        this.adminKey = adminKey;
        this.cid = cid;
        this.cidSecret = cidSecret;
        this.tid = tid;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String adminKey;
        private String cid;
        private String cidSecret;
        private String tid;

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

        public Builder setCustomJson(String adminKey) {
            this.adminKey = adminKey;
            return this;
        }

        public Builder setTid(String tid) {
            this.tid = tid;
            return this;
        }

        public KakaoPayFindApiRequest build() {
            return new KakaoPayFindApiRequest(
                    adminKey,
                    cid,
                    cidSecret,
                    tid
            );
        }
    }
}
