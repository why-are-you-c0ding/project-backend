package wayc.backend.payment.domain.nicepayment.approve;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

import java.util.List;

@Getter
public class NicePaymentApproveApiResponse {

    private String resultCode;
    private String resultMsg;
    private String tid;
    private String cancelledTid;
    private String orderId;
    private String ediDate;
    private String signature;
    private String status;
    private String paidAt;
    private String failedAt;
    private String cancelledAt;
    private String payMethod;
    private int amount;
    private int balanceAmt;
    private String goodsName;
    private String mallReserved;
    private boolean useEscrow;
    private String currency;
    private String channel;
    private String approveNo;
    private String buyerName;
    private String buyerTel;
    private String buyerEmail;
    private String receiptUrl;
    private String mallUserId;
    private boolean issuedCashReceipt;
    private Coupon coupon;
    private Card card;
    private VBank vbank;
    private String cancels;
    private List<CashReceipt> cashReceipts;

    @Getter
    public static class Coupon {
        private Integer couponAmt;
    }

    @Getter
    public static class Card {
        private String cardCode;
        private String cardName;
        private String cardNum;
        private int cardQuota;
        private boolean isInterestFree;
        private String cardType;
        private boolean canPartCancel;
        private String acquCardCode;
        private String acquCardName;
    }

    @Getter
    public static class CashReceipt {
        private String receiptTid;
        private String orgTid;
        private String status;
        private Integer amount;
        private Integer taxFreeAmt;
        private String receiptType;
        private String issueNo;
        private String receiptUrl;
    }

    @Getter
    public static class VBank {
        private String vbankCode;
        private String vbankName;
        private String vbankNumber;
        private String vbankExpDate;
        private String vbankHolder;
    }
}
