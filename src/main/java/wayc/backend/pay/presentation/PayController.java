//package wayc.backend.pay.presentation;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import wayc.backend.pay.application.PayService;
//import wayc.backend.pay.application.dto.response.CreatePayResponseDto;
//import wayc.backend.pay.presentation.dto.request.PostPayRequestDto;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@RequiredArgsConstructor
//@RestController
//@RequestMapping("/pays")
//public class PayController {
//
//    private final PayService payService;
//
//    @PostMapping
//    public ResponseEntity<CreatePayResponseDto> postPay(
//            @AuthenticationPrincipal Long memberId,
//            @RequestBody List<PostPayRequestDto> request
//    ){
//        payService.createPay(
//                memberId,
//                request
//                        .stream()
//                        .map(dto -> dto.toServiceDto())
//                        .collect(Collectors.toList())
//        );
//        return ResponseEntity.ok(new CreatePayResponseDto());
//    }
//}
