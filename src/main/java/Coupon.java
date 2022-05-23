import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.Objects;

@Getter
@Setter
@Builder
public class Coupon {
    private String code;
    private double percentage;
    private ZonedDateTime couponExpirationDate;

    public boolean isExpired(ZonedDateTime dateUse) {
        if (Objects.isNull(this.couponExpirationDate)) return false;
        return couponExpirationDate.isBefore(dateUse);
    }
}
