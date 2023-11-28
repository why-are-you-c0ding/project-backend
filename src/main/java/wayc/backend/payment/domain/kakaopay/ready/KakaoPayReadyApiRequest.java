package wayc.backend.payment.domain.kakaopay.ready;


import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class KakaoPayReadyApiRequest {

    private String adminKey;
    private String cid;
    private String cidSecret;
    private String partnerOrderId;
    private String partnerUserId;
    private String itemName;
    private String itemCode;
    private Integer quantity;
    private Integer totalAmount;
    private Integer taxFreeAmount;
    private Integer vatAmount;
    private Integer greenDeposit;
    private String approvalUrl;
    private String cancelUrl;
    private String failUrl;
    private List<String> availableCards;
    private String paymentMethodType;
    private Integer installMonth;
    private Map<String, String> customJson;

    private KakaoPayReadyApiRequest(String adminKey,
                                    String cid,
                                    String cidSecret,
                                    String partnerOrderId,
                                    String partnerUserId,
                                    String itemName,
                                    String itemCode,
                                    Integer quantity,
                                    Integer totalAmount,
                                    Integer taxFreeAmount,
                                    Integer vatAmount,
                                    Integer greenDeposit,
                                    String approvalUrl,
                                    String cancelUrl,
                                    String failUrl,
                                    List<String> availableCards,
                                    String paymentMethodType,
                                    Integer installMonth,
                                    Map<String, String> customJson) {
        this.adminKey = adminKey;
        this.cid = cid;
        this.cidSecret = cidSecret;
        this.partnerOrderId = partnerOrderId;
        this.partnerUserId = partnerUserId;
        this.itemName = itemName;
        this.itemCode = itemCode;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.taxFreeAmount = taxFreeAmount;
        this.vatAmount = vatAmount;
        this.greenDeposit = greenDeposit;
        this.approvalUrl = approvalUrl;
        this.cancelUrl = cancelUrl;
        this.failUrl = failUrl;
        this.availableCards = availableCards;
        this.paymentMethodType = paymentMethodType;
        this.installMonth = installMonth;
        this.customJson = customJson;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String adminKey;
        private String cid;
        private String cidSecret;
        private String partnerOrderId;
        private String partnerUserId;
        private String itemName;
        private String itemCode;
        private Integer quantity;
        private Integer totalAmount;
        private Integer taxFreeAmount;
        private Integer vatAmount;
        private Integer greenDeposit;
        private String approvalUrl;
        private String cancelUrl;
        private String failUrl;
        private List<String> availableCards;
        private String paymentMethodType;
        private Integer installMonth;
        private Map<String, String> customJson;

        public Builder setCid(String cid) {
            this.cid = cid;
            return this;
        }

        public Builder setCidSecret(String cidSecret) {
            this.cidSecret = cidSecret;
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

        public Builder setItemName(String itemName) {
            this.itemName = itemName;
            return this;
        }

        public Builder setItemCode(String itemCode) {
            this.itemCode = itemCode;
            return this;
        }

        public Builder setQuantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder setTotalAmount(Integer totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }

        public Builder setTaxFreeAmount(Integer taxFreeAmount) {
            this.taxFreeAmount = taxFreeAmount;
            return this;
        }

        public Builder setVatAmount(Integer vatAmount) {
            this.vatAmount = vatAmount;
            return this;
        }

        public Builder setGreenDeposit(Integer greenDeposit) {
            this.greenDeposit = greenDeposit;
            return this;
        }

        public Builder setApprovalUrl(String approvalUrl) {
            this.approvalUrl = approvalUrl;
            return this;
        }

        public Builder setCancelUrl(String cancelUrl) {
            this.cancelUrl = cancelUrl;
            return this;
        }

        public Builder setFailUrl(String failUrl) {
            this.failUrl = failUrl;
            return this;
        }

        public Builder setAvailableCards(List<String> availableCards) {
            this.availableCards = availableCards;
            return this;
        }

        public Builder setPaymentMethodType(String paymentMethodType) {
            this.paymentMethodType = paymentMethodType;
            return this;
        }

        public Builder setInstallMonth(Integer installMonth) {
            this.installMonth = installMonth;
            return this;
        }

        public Builder setCustomJson(Map<String, String> customJson) {
            this.customJson = customJson;
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

        public KakaoPayReadyApiRequest build() {
            return new KakaoPayReadyApiRequest(
                    adminKey,
                    cid,
                    cidSecret,
                    partnerOrderId,
                    partnerUserId,
                    itemName,
                    itemCode,
                    quantity,
                    totalAmount,
                    taxFreeAmount,
                    vatAmount,
                    greenDeposit,
                    approvalUrl,
                    cancelUrl,
                    failUrl,
                    availableCards,
                    paymentMethodType,
                    installMonth,
                    customJson
            );
        }
    }
}
