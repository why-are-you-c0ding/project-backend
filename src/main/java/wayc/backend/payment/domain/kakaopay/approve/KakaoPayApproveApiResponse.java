package wayc.backend.payment.domain.kakaopay.approve;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.ToString;
import org.joda.time.DateTime;

@Getter
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KakaoPayApproveApiResponse {

    private String aid;
    private String tid;
    private String cid;
    private String sid;
    private String partnerOrderId;
    private String partnerUserId;
    private String paymentMethodType;
    private Amount amount;
    private CardInfo cardInfo;
    private String itemName;
    private String itemCode;
    private Integer quantity;
    private String createdAt;
    private String approvedAt;
    private String payload;

    @Getter
    static class Amount {
        private Integer total;
        private Integer taxFree;
        private Integer vat;
        private Integer point;
        private Integer discount;
        private Integer greenDeposit;
    }

    @Getter
    static class CardInfo {
        private String purchaseCorp;
        private String purchaseCorpCode;
        private String issuerCorp;
        private String issuerCorpCode;
        private String kakaopayPurchaseCorp;
        private String kakaopayPurchaseCorpcode;
        private String kakaopayIssuerCorp;
        private String kakaopayIssuerCorpCode;
        private String bin;
        private String cardType;
        private String installMonth;
        private String approvedId;
        private String cardMid;
        private String interestFreeInstall;
        private String cardItemCode;
    }
}
