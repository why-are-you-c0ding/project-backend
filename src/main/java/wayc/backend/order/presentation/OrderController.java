package wayc.backend.order.presentation;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wayc.backend.order.application.OrderService;
import wayc.backend.order.presentation.dto.request.PostOrderRequestDto;
import wayc.backend.order.presentation.dto.response.PostOrderResponseDto;

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
}
