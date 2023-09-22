package wayc.backend.order.presentation;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity createOrder(@AuthenticationPrincipal Long memberId,
                                      @Validated @RequestBody CreateOrderRequest request){
       orderService.createOrder(request.toServiceDto(memberId));
        return new ResponseEntity(new CreateOrderResponse(), HttpStatus.CREATED);
    }

    @GetMapping("/orders/customers")
    public ResponseEntity<FindPagingOrderResponseDto> findCustomerOrders(@RequestParam Integer page,
                                                                         @AuthenticationPrincipal Long id){
        FindPagingOrderResponseDto res = orderProvider.findCustomerOrders(id, page);
        return ResponseEntity.ok(res);
    }


    @GetMapping("/orders/sellers")
    public ResponseEntity findSellerOrders(@RequestParam Integer page,
                                           @AuthenticationPrincipal Long id){
        return new ResponseEntity(orderProvider.findSellerOrders(id, page), HttpStatus.OK);
    }


    @GetMapping("/order-line-item/{orderLineItemId}")
    public ResponseEntity findOrder(@PathVariable Long orderLineItemId,
                                    @AuthenticationPrincipal Long id){
        return new ResponseEntity(orderProvider.findDetailOrderLineItem(id, orderLineItemId) , HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity updateOrder(@RequestBody UpdateOrderRequest request,
                                      @AuthenticationPrincipal Long id) {
        orderService.updateOrder(id, request.toServiceDto());
        return ResponseEntity.ok("");
    }
}
