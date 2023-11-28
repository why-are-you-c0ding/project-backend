package wayc.backend.payment.domain.kakaopay.refund;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KakaoPayCancelApiResponse {

    private String aid;
    private String tid;
    private String cid;
    private String status;
    private String partnerOrderId;
    private String partnerUserId;
    private String paymentMethodType;
    private Amount amount;
    private Amount approvedCancelAmount;
    private Amount canceledAmount;
    private Amount cancelAvailableAmount;
    private String itemName;
    private String itemCode;
    private int quantity;
    private String createdAt;
    private String approvedAt;
    private String canceledAt;
    private String payload;

    @ToString
    @Getter
    public static class Amount {
        private int total;
        private int taxFree;
        private int vat;
        private int point;
        private int discount;
        private int greenDeposit;
    }
}