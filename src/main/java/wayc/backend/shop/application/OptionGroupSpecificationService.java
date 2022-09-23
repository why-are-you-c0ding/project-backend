package wayc.backend.shop.application;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import wayc.backend.exception.shop.NotExistsOptionGroupSpecificationException;
import wayc.backend.shop.application.dto.response.show.ShowOptionGroupResponseDto;
import wayc.backend.shop.application.dto.response.show.ShowOptionResponseDto;
import wayc.backend.shop.dataaccess.OptionGroupSpecificationRepository;
import wayc.backend.shop.domain.OptionGroupSpecification;
import wayc.backend.shop.domain.OptionSpecification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OptionGroupSpecificationService {

    private final OptionGroupSpecificationRepository optionGroupSpecificationRepository;

    public List<ShowOptionGroupResponseDto> get(List<Long> optionGroupSpecificationIdList) {

        List<ShowOptionGroupResponseDto> result = new ArrayList<>();

        for (Long id : optionGroupSpecificationIdList) {
            OptionGroupSpecification optionGroup = optionGroupSpecificationRepository.findByIdAndStatus(id)
                    .orElseThrow(NotExistsOptionGroupSpecificationException::new);
            result.add(toOptionGroupDto(optionGroup));
        }
        return result;
    }

    private ShowOptionGroupResponseDto toOptionGroupDto(OptionGroupSpecification optionGroup) {
        return new ShowOptionGroupResponseDto(
                optionGroup.getId(),
                optionGroup.getName(),
                optionGroup
                        .getOptionSpecifications()
                        .stream()
                        .map(this::toOptionDto)
                        .collect(Collectors.toList())
        );
    }

    public ShowOptionResponseDto toOptionDto(OptionSpecification optionSpecification) {
        return new ShowOptionResponseDto(
                optionSpecification.getId(),
                optionSpecification.getName(),
                optionSpecification.getPrice()
        );
    }
}
