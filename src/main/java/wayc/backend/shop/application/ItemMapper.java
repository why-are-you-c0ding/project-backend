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
                .imageUrl(dto.getImageUrl())
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
                        .collect(Collectors.toList()),
                optionGroups.getOptionGroupName(),
                optionGroups.getBasic()
        );
    }

    private OptionSpecification toOptionSpecification(CreateOptionRequestDto option){
        return new OptionSpecification(option.getOptionName(), option.getPrice());
    }
}

//OneToMany 단방향 vs 세터.
//세터를 쓸 것인가 아니면, update 쿼리 한 번 더 날릴까. 사실 업데이트 쿼리 한 번 더 나간다고 완전 손해는 아니라고 함.
//대신 쿼리를 보는 순간 헷갈린다는 부분.. 이러면 운영이 힘들다 사실.
//아마 이때가 드물게 oneToMany 단방향이 유용한 게 아닐까..?!?!?

//결론 : 그래도 본인은 학습 중이고 깔끔한 코드를 원하므로 한번 일대다 단방향으로 해보자. 맨날 양방향만 해봤음