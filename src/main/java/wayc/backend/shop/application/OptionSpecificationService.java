package wayc.backend.shop.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wayc.backend.shop.exception.NotExistsOptionSpecificationException;
import wayc.backend.shop.domain.command.OptionSpecificationRepository;
import wayc.backend.shop.domain.OptionSpecification;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OptionSpecificationService {

    private final OptionSpecificationRepository optionSpecificationRepository;

    public List<OptionSpecification> getList(List<Long> idList) {
        return idList.stream()
                .map(id ->
                        optionSpecificationRepository
                                .findByIdAndStatus(id)
                                .orElseThrow(NotExistsOptionSpecificationException::new
                        )
                )
                .collect(Collectors.toList());
    }
}
