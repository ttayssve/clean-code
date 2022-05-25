import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Dimension {
    private double height;
    private double width;
    private double length;

    public double getVolume() {
        return this.height/100 * this.width/100 * this.length/100;
    }
}
