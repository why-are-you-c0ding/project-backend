package wayc.backend.common.domain;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Function;

@Getter
public class Money {

    private final BigDecimal amount;

    public static final Money ZERO = Money.from(0);

    public static Money from(long amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    public static Money from(Integer amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    public static Money from(double amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    /**
     * T를 Money로 매핑하고 더 한다.
     */
    public static <T> Money sum(Collection<T> bags, Function<T, Money> monetary) {
        return bags
                .stream()
                .map(bag -> monetary.apply(bag))
                .reduce(Money.ZERO, Money::plus);
    }

    Money(BigDecimal amount) {
        this.amount = amount;
    }

    public Money plus(Money amount) {
        return new Money(this.amount.add(amount.amount));
    }

    public Money minus(Money amount) {
        return new Money(this.amount.subtract(amount.amount));
    }

    public Money times(double percent) {
        return new Money(this.amount.multiply(BigDecimal.valueOf(percent)));
    }

    public Money divide(double divisor) {
        return new Money(amount.divide(BigDecimal.valueOf(divisor)));
    }

    public Long longValue() {
        return amount.longValue();
    }

    public Double doubleValue() {
        return amount.doubleValue();
    }

    public Integer intValue() {
        return amount.intValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Money)) return false;

        Money money = (Money) o;

        return Objects.equals(amount.doubleValue(), money.amount.doubleValue());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(amount);
    }
}
