package wayc.backend.common.domain;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true) //글로벌 설정
public class MoneyConverter implements AttributeConverter<Money, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Money money) {
        return money.getAmount().intValue();
    }

    @Override
    public Money convertToEntityAttribute(Integer amount) {
        return Money.from(amount);
    }
}
