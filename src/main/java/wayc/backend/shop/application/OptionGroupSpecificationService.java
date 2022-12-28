package wayc.backend.shop.application;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import wayc.backend.exception.shop.NotExistsOptionGroupSpecificationException;
import wayc.backend.shop.application.dto.response.show.ShowOptionGroupResponseDto;
import wayc.backend.shop.application.dto.response.show.ShowOptionResponseDto;
import wayc.backend.shop.domain.command.OptionGroupSpecificationRepository;
import wayc.backend.shop.domain.OptionGroupSpecification;
import wayc.backend.shop.domain.OptionSpecification;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OptionGroupSpecificationService {

    private final OptionGroupSpecificationRepository optionGroupSpecificationRepository;

    public List<ShowOptionGroupResponseDto> get(List<Long> optionGroupSpecificationIdList) {
        return  optionGroupSpecificationIdList
                .stream()
                .map(id -> toOptionGroupDto(
                        optionGroupSpecificationRepository
                                .findByIdAndStatus(id)
                                .orElseThrow(NotExistsOptionGroupSpecificationException::new)))
                .collect(Collectors.toList());
    }

    private ShowOptionGroupResponseDto toOptionGroupDto(OptionGroupSpecification optionGroup) {
        return new ShowOptionGroupResponseDto(
                optionGroup.getId(),
                optionGroup.getName(),
                optionGroup
                        .getOptionSpecifications()
                        .stream()
                        .map(this::toOptionDto)
                        .collect(Collectors.toList()),
                optionGroup.getBasic()
        );
    }

    private ShowOptionResponseDto toOptionDto(OptionSpecification optionSpecification) {
        return new ShowOptionResponseDto(
                optionSpecification.getId(),
                optionSpecification.getName(),
                optionSpecification.getPrice()
        );
    }
}
