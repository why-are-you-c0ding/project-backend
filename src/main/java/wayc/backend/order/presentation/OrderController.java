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


@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderService orderService;
    private final OrderProvider orderProvider;

    @PostMapping("/orders")
    public ResponseEntity createOrder(@AuthenticationPrincipal Long memberId,
                                      @Validated @RequestBody CreateOrderRequest request){
       orderService.createOrder(request.toServiceDto(memberId));
        return new ResponseEntity(new CreateOrderResponse(), HttpStatus.CREATED);
    }

    @GetMapping("/order-line-items/customers")
    public ResponseEntity<FindPagingOrderResponseDto> findCustomerOrders(@RequestParam Long lastLookUpOrderLineItemId,
                                                                         @AuthenticationPrincipal Long id){
        FindPagingOrderResponseDto res = orderProvider.findCustomerOrderLineItems(id, lastLookUpOrderLineItemId);
        return ResponseEntity.ok(res);
    }


    @GetMapping("/order-line-items/sellers")
    public ResponseEntity findSellerOrders(@RequestParam Long lastLookUpOrderLineItemId,
                                           @AuthenticationPrincipal Long id){
        return new ResponseEntity(orderProvider.findSellerOrderLineItems(id, lastLookUpOrderLineItemId), HttpStatus.OK);
    }


    @GetMapping("/order-line-items/{orderLineItemId}")
    public ResponseEntity findOrder(@PathVariable Long orderLineItemId,
                                    @AuthenticationPrincipal Long id){
        return new ResponseEntity(orderProvider.findDetailOrderLineItem(orderLineItemId) , HttpStatus.OK);
    }

    @PatchMapping("/orders")
    public ResponseEntity updateOrder(@RequestBody UpdateOrderRequest request,
                                      @AuthenticationPrincipal Long id) {
        orderService.updateOrder(id, request.toServiceDto());
        return ResponseEntity.ok(new CommandSuccessResponse("주문 상품 수정을 성공했습니다."));
    }
}
