package wayc.backend.order.presentation;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import wayc.backend.order.application.OrderService;
import wayc.backend.order.application.dto.response.ShowOrdersResponseDto;
import wayc.backend.order.application.dto.response.ShowTotalOrderResponseDto;
import wayc.backend.order.presentation.dto.request.PostOrderRequestDto;
import wayc.backend.order.presentation.dto.response.PostOrderResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<PostOrderResponseDto> postOrder(
            @AuthenticationPrincipal Long id,
            @Validated @RequestBody List<PostOrderRequestDto> request
    ){
        orderService.createOrder(
                request
                        .stream()
                        .map(dto -> dto.toServiceDto())
                        .collect(Collectors.toList()),
                id
        );
        return ResponseEntity.ok(new PostOrderResponseDto());
    }

    @GetMapping("/customers")
    public ResponseEntity<ShowTotalOrderResponseDto> getCustomerOrders(
            @RequestParam Integer page,
            @AuthenticationPrincipal Long id
    ){
        ShowTotalOrderResponseDto res = orderService.showCustomerOrders(id, page);
        return ResponseEntity.ok(res);
    }




}
