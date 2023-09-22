package wayc.backend.order.domain.repository.query.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class FindOrderOptionGroupResponseDto {

    private String optionGroupName;
    private String optionName;

    @QueryProjection
    public FindOrderOptionGroupResponseDto(String optionGroupName, String optionName) {
        this.optionGroupName = optionGroupName;
        this.optionName = optionName;
    }
}
