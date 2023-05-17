package wayc.backend.order.presentation;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import wayc.backend.order.application.OrderProvider;
import wayc.backend.order.application.OrderService;
import wayc.backend.order.application.dto.response.FindPagingOrderResponseDto;
import wayc.backend.order.application.dto.response.UpdateOrderResponseDto;

import wayc.backend.order.presentation.dto.request.UpdateOrderRequest;
import wayc.backend.order.presentation.dto.request.CreateOrderRequest;
import wayc.backend.order.presentation.dto.response.CreateOrderResponse;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderProvider orderProvider;

    @PostMapping
    public ResponseEntity createOrder(@AuthenticationPrincipal Long memberId,
                                        @Validated @RequestBody List<CreateOrderRequest> request){
       orderService.createOrder(
               memberId,
               request
                       .stream()
                       .map(dto -> dto.toServiceDto())
                       .collect(Collectors.toList())
                );
        return new ResponseEntity(new CreateOrderResponse(), HttpStatus.CREATED);
    }

    @GetMapping("/customers")
    public ResponseEntity<FindPagingOrderResponseDto> findCustomerOrders(@RequestParam Integer page,
                                                                        @AuthenticationPrincipal Long id){
        FindPagingOrderResponseDto res = orderProvider.showCustomerOrders(id, page);
        return ResponseEntity.ok(res);
    }


    @GetMapping("/sellers")
    public ResponseEntity findSellerOrders(@RequestParam Integer page,
                                          @AuthenticationPrincipal Long id){
        return new ResponseEntity(orderProvider.showSellerOrders(id, page), HttpStatus.OK);
    }


    @GetMapping("/{orderId}")
    public ResponseEntity findOrder(@PathVariable Long orderId,
                                   @AuthenticationPrincipal Long id){
        return new ResponseEntity(orderProvider.showOrder(id, orderId) , HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity updateOrder(@RequestBody UpdateOrderRequest request,
                                      @AuthenticationPrincipal Long id) {
        orderService.updateOrder(id, request.toServiceDto());
        return ResponseEntity.ok(new UpdateOrderResponseDto());
    }
}
