package wayc.backend.order.presentation;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import wayc.backend.order.application.OrderService;
import wayc.backend.order.application.PayService;
import wayc.backend.order.application.dto.response.ShowOrderResponseDto;
import wayc.backend.order.application.dto.response.ShowTotalOrderResponseDto;
import wayc.backend.order.application.dto.response.UpdateOrderResponseDto;

import wayc.backend.order.presentation.dto.request.PatchOrderRequestDto;
import wayc.backend.order.presentation.dto.request.PostOrderRequestDto;
import wayc.backend.order.presentation.dto.response.PostOrderResponseDto;
import wayc.backend.pay.application.dto.request.CreatePayRequestDto;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity registerOrder(@AuthenticationPrincipal Long memberId,
                                    @Validated @RequestBody List<PostOrderRequestDto> request){
       orderService.createOrder(
               memberId,
               request
                       .stream()
                       .map(dto -> dto.toServiceDto())
                       .collect(Collectors.toList())
                );

        return new ResponseEntity(new PostOrderResponseDto(), HttpStatus.CREATED);
    }

    @GetMapping("/customers")
    public ResponseEntity<ShowTotalOrderResponseDto> getCustomerOrders(
            @RequestParam Integer page,
            @AuthenticationPrincipal Long id
    ){
        ShowTotalOrderResponseDto res = orderService.showCustomerOrders(id, page);
        return ResponseEntity.ok(res);
    }


    @GetMapping("/sellers")
    public ResponseEntity getSellerOrders(@RequestParam Integer page,
                                          @AuthenticationPrincipal Long id){
        return new ResponseEntity(orderService.showSellerOrders(id, page), HttpStatus.OK);
    }


    @GetMapping("/{orderId}")
    public ResponseEntity getOrder(@PathVariable Long orderId,
                                                         @AuthenticationPrincipal Long id){
        return new ResponseEntity(orderService.showOrder(id, orderId) , HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity updateOrder(@RequestBody PatchOrderRequestDto request,
                                      @AuthenticationPrincipal Long id) {
        orderService.updateOrder(id, request.toServiceDto());
        return ResponseEntity.ok(new UpdateOrderResponseDto());
    }
}
