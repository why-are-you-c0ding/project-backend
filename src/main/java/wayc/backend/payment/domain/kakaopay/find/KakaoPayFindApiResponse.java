package wayc.backend.payment.domain.kakaopay.find;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KakaoPayFindApiResponse {

    private String tid;
    private String cid;
    private String status;
    private String partnerOrderId;
    private String partnerUserId;
    private String paymentMethodType;
    private Amount amount;
    private CanceledAmount canceledAmount;
    private CancelAvailableAmount cancelAvailableAmount;
    private String itemName;
    private String itemCode;
    private int quantity;
    private String createdAt;
    private String approvedAt;
    private String canceledAt;
    private SelectedCardInfo selectedCardInfo;
    private List<PaymentActionDetails> paymentActionDetails;


    @Getter
    public static class Amount {
        private int total;
        private int taxFree;
        private int vat;
        private int point;
        private int discount;
        private int greenDeposit;
    }

    @Getter
    public static class CanceledAmount {
        private int total;
        private int taxFree;
        private int vat;
        private int point;
        private int discount;
        private int greenDeposit;
    }


    @Getter
    public static class CancelAvailableAmount {
        private int total;
        private int taxFree;
        private int vat;
        private int point;
        private int discount;
        private int greenDeposit;
    }

    @Getter
    public static class PaymentActionDetails {
        private String aid;
        private String paymentActionType;
        private String paymentMethodType;
        private int amount;
        private int pointAmount;
        private int discountAmount;
        private String approvedAt;
        private int greenDeposit;
    }

    @Getter
    public static class SelectedCardInfo {
        private String cardBin;
        private Integer installMonth;
        private String cardCorpName;
        private String interestFreeInstall;
    }
}
