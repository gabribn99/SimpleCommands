package plugin.entities;

import lombok.Data;
import org.bukkit.entity.Player;

@Data
public class TPBean{
    Player sender;
    Player receiver;

    public TPBean(Player sender, Player receiver) {
        this.sender = sender;
        this.receiver = receiver;
    }
}
