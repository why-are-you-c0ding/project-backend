package wayc.backend.shop.application.service;

import org.springframework.stereotype.Component;

import wayc.backend.shop.application.dto.request.RegisterItemRequestDto;
import wayc.backend.shop.application.dto.request.RegisterOptionGroupRequestDto;

import wayc.backend.shop.application.dto.request.RegisterOptionRequestDto;
import wayc.backend.shop.domain.*;

import java.util.stream.Collectors;

@Component
public class ItemMapper {

    public Item mapOf(RegisterItemRequestDto dto,
                        Shop shop){
        Item item = Item.builder()
                .name(dto.getItemName())
                .shop(shop)
                .optionGroups(
                        dto.getOptionGroups()
                                .stream()
                                .map(this::toOptionGroupSpecification)
                                .collect(Collectors.toList())
                )
                .imageUrl(dto.getImageUrl())
                .information(dto.getInformation())
                .category(dto.getCategory())
                .build();
        shop.addItem(item);
        return item;
    }

    private OptionGroup toOptionGroupSpecification(
            RegisterOptionGroupRequestDto optionGroups
    ){
        return new OptionGroup(
                optionGroups
                        .getOptionRequests()
                        .stream()
                        .map(this::toOptionSpecification)
                        .collect(Collectors.toList()),
                optionGroups.getOptionGroupName(),
                optionGroups.getBasic()
        );
    }

    private Option toOptionSpecification(RegisterOptionRequestDto option){
        return new Option(option.getOptionName(), option.getPrice());
    }
}