package plugin.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import plugin.core.Main;
import plugin.entities.TPBean;

public class TPDeny implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            TPBean tp = Main.mapTps.get(sender.getName());
            Main.mapTps.remove(sender.getName());
            tp.getSender().sendMessage(ChatColor.RED + player.getName() + " ha rechazado tu petición de tp");
            player.sendMessage(ChatColor.DARK_AQUA + "Has rechazado la petición de tp");
        }
        return false;
    }
}
