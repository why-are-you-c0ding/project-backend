package wayc.backend.shop.application;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import wayc.backend.shop.exception.NotExistsOptionGroupSpecificationException;
import wayc.backend.shop.application.dto.response.find.FindOptionGroupResponseDto;
import wayc.backend.shop.application.dto.response.find.FindOptionResponseDto;
import wayc.backend.shop.domain.command.OptionGroupRepository;
import wayc.backend.shop.domain.OptionGroup;
import wayc.backend.shop.domain.Option;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OptionGroupService {

    private final OptionGroupRepository optionGroupRepository;

    public List<FindOptionGroupResponseDto> findOptionGroups(List<Long> optionGroupSpecificationIdList) {
        return  optionGroupSpecificationIdList
                .stream()
                .map(id ->
                        toOptionGroupDto(
                        optionGroupRepository
                                .findByIdAndStatus(id)
                                .orElseThrow(NotExistsOptionGroupSpecificationException::new))
                )
                .collect(Collectors.toList());
    }

    private FindOptionGroupResponseDto toOptionGroupDto(OptionGroup optionGroup) {
        return new FindOptionGroupResponseDto(
                optionGroup.getId(),
                optionGroup.getName(),
                optionGroup
                        .getOptions()
                        .stream()
                        .map(this::toOptionDto)
                        .collect(Collectors.toList()),
                optionGroup.getBasic()
        );
    }

    private FindOptionResponseDto toOptionDto(Option optionSpecification) {
        return new FindOptionResponseDto(
                optionSpecification.getId(),
                optionSpecification.getName(),
                optionSpecification.getPrice()
        );
    }
}
