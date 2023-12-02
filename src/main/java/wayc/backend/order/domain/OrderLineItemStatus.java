package wayc.backend.order.domain;

public enum OrderLineItemStatus {
    ORDER_ACCEPTED,
    READY_FOR_PAYMENT,
    PAYMENT_COMPLETED,
    PREPARING_FOR_DELIVERY,
    PAYMENT_CANCELED,
    REFUND
}
