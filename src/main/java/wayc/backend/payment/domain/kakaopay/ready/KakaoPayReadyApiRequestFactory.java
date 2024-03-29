package wayc.backend.payment.domain.kakaopay.ready;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import wayc.backend.order.domain.Order;
import wayc.backend.order.domain.OrderLineItem;
import wayc.backend.payment.domain.kakaopay.ready.KakaoPayReadyApiRequest;
import wayc.backend.payment.domain.kakaopay.KakaoPayProperties;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class KakaoPayReadyApiRequestFactory {

    private final KakaoPayProperties kakaoPayProperties;

    public KakaoPayReadyApiRequest getKakaoPayReadyApiRequest(Order order) {
        return KakaoPayReadyApiRequest
                .builder()
                .setAdminKey(kakaoPayProperties.getAdminKey())
                .setCid(kakaoPayProperties.getCid())
                .setCidSecret(kakaoPayProperties.getCidSecret())
                .setPartnerOrderId(String.valueOf(order.getId()))
                .setPartnerUserId(String.valueOf(order.getOrderer().getMemberId()))
                .setItemName(makeName(order.getOrderLineItems()))
                .setItemCode(null)
                .setQuantity(order.getOrderLineItems().size())
                .setTotalAmount(order.getTotalPayment())
                .setTaxFreeAmount(order.getTotalPayment())
                .setVatAmount(null)
                .setGreenDeposit(0)
                .setApprovalUrl(kakaoPayProperties.getReadyApi().getApprovalUrl() + "?partner_order_id=" + order.getId())
                .setCancelUrl(kakaoPayProperties.getReadyApi().getCancelUrl() + "?partner_order_id=" + order.getId())
                .setFailUrl(kakaoPayProperties.getReadyApi().getFailUrl())
                .setAvailableCards(null)
                .setPaymentMethodType(null)
                .setInstallMonth(null)
                .setCustomJson(Map.of())
                .build();
    }

    private String makeName(List<OrderLineItem> orderLineItems) {
        if(orderLineItems.size() == 1) {
            return orderLineItems.get(0).getName();
        }

        String firstName = orderLineItems.get(0).getName();
        return firstName + " 외 " + orderLineItems.size() + "개";
    }
}
