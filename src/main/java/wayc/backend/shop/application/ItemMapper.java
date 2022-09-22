package wayc.backend.shop.application;

import org.springframework.stereotype.Component;

import wayc.backend.shop.application.dto.request.CreateItemRequestDto;
import wayc.backend.shop.application.dto.request.CreateOptionGroupRequestDto;

import wayc.backend.shop.application.dto.request.CreateOptionRequestDto;
import wayc.backend.shop.domain.*;

import java.util.stream.Collectors;

@Component
public class ItemMapper {

    public Item mapFrom(
            CreateItemRequestDto dto,
            Shop shop
    ){
        return Item.builder()
                .name(dto.getItemName())
                .shop(shop)
                .optionGroupSpecifications(
                        dto.getOptionGroups()
                                .stream()
                                .map(this::toOptionGroupSpecification)
                                .collect(Collectors.toList())
                )
                .build();
    }

    private OptionGroupSpecification toOptionGroupSpecification(
            CreateOptionGroupRequestDto optionGroups
    ){
        return new OptionGroupSpecification(
                optionGroups
                        .getOptionRequests()
                        .stream()
                        .map(this::toOptionSpecification)
                        .collect(Collectors.toList())
        );
    }

    private OptionSpecification toOptionSpecification(CreateOptionRequestDto option){
        return new OptionSpecification(option.getOptionName(), option.getPrice());
    }
}
