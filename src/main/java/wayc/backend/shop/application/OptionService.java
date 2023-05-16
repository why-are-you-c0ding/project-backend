package wayc.backend.shop.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wayc.backend.shop.domain.Option;
import wayc.backend.shop.exception.NotExistsOptionSpecificationException;
import wayc.backend.shop.domain.command.OptionRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OptionService {

    private final OptionRepository optionSpecificationRepository;

    public List<Option> getOptions(List<Long> idList) {
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
