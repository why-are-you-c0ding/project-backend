package wayc.backend.common.argumentresolver;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import wayc.backend.shop.presentation.dto.request.GetOptionIdRequestDto;
import wayc.backend.shop.presentation.dto.request.GetStockRequestDto;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.stream.Collectors;

public class GetStockArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterAnnotation(GetStock.class) != null;
    }

    @Override
    public Object resolveArgument(
            MethodParameter methodParameter,
            ModelAndViewContainer modelAndViewContainer,
            NativeWebRequest nativeWebRequest,
            WebDataBinderFactory webDataBinderFactory) throws Exception {

        HttpServletRequest request = (HttpServletRequest) nativeWebRequest.getNativeRequest();
        Map<String, String[]> parameterMap = request.getParameterMap();

        return new GetStockRequestDto(
                parameterMap
                        .keySet()
                        .stream()
                        .map(key ->
                                new GetOptionIdRequestDto(parameterMap.get(key)))
                        .collect(Collectors.toList())
        );
    }
}
