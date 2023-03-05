package plugin.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationBean {
    String worldName;
    String playerName;
    private double x;
    private double y;
    private double z;
}
