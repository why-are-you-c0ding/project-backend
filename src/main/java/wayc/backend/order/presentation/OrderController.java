package wayc.backend.order.presentation;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import wayc.backend.order.application.OrderService;
import wayc.backend.order.application.dto.response.ShowOrderResponseDto;
import wayc.backend.order.presentation.dto.request.PostOrderRequestDto;
import wayc.backend.order.presentation.dto.response.PostOrderResponseDto;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<PostOrderResponseDto> createOrder(
            @AuthenticationPrincipal Long id,
            @Validated @RequestBody PostOrderRequestDto request
    ){
        orderService.createOrder(request.toServiceDto(), id);
        return ResponseEntity.ok(new PostOrderResponseDto());
    }

    @GetMapping
    public ResponseEntity<List<ShowOrderResponseDto> > showOrders(
            @AuthenticationPrincipal Long id
    ){
        List<ShowOrderResponseDto> res = orderService.showOrders(id);
        return ResponseEntity.ok(res);
    }
}
