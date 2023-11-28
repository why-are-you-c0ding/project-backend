package wayc.backend.order.presentation;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import wayc.backend.common.CommandSuccessResponse;
import wayc.backend.order.application.OrderProvider;
import wayc.backend.order.application.OrderService;
import wayc.backend.order.domain.repository.query.dto.FindPagingOrderResponseDto;

import wayc.backend.order.presentation.dto.request.UpdateOrderRequest;
import wayc.backend.order.presentation.dto.request.CreateOrderRequest;
import wayc.backend.order.presentation.dto.response.CreateOrderResponse;
import wayc.backend.security.UserPrincipal;


@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderService orderService;
    private final OrderProvider orderProvider;

    @PostMapping("/orders")
    public ResponseEntity createOrder(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                      @Validated @RequestBody CreateOrderRequest request){
        Long orderId = orderService.createOrder(request.toServiceDto(userPrincipal.getId()));
        return new ResponseEntity(new CreateOrderResponse(orderId), HttpStatus.CREATED);
    }

    @GetMapping("/order-line-items/customers")
    public ResponseEntity<FindPagingOrderResponseDto> findCustomerOrders(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                                         @RequestParam Long lastLookUpOrderLineItemId){
        FindPagingOrderResponseDto res = orderProvider.findCustomerOrderLineItems(userPrincipal.getId(), lastLookUpOrderLineItemId);
        return ResponseEntity.ok(res);
    }


    @GetMapping("/order-line-items/sellers")
    public ResponseEntity findSellerOrders(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                           @RequestParam Long lastLookUpOrderLineItemId){
        return new ResponseEntity(orderProvider.findSellerOrderLineItems(userPrincipal.getId(), lastLookUpOrderLineItemId), HttpStatus.OK);
    }


    @GetMapping("/order-line-items/{orderLineItemId}")
    public ResponseEntity findOrder(@PathVariable Long orderLineItemId){
        return new ResponseEntity(orderProvider.findDetailOrderLineItem(orderLineItemId) , HttpStatus.OK);
    }

    @PatchMapping("/orders")
    public ResponseEntity updateOrder(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                      @RequestBody UpdateOrderRequest request) {
        orderService.updateOrder(userPrincipal.getId(), request.toServiceDto());
        return ResponseEntity.ok(new CommandSuccessResponse("주문 상품 수정을 성공했습니다."));
    }
}
