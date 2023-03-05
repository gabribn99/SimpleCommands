package plugin.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import plugin.core.Main;

public class DelHome implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            try {
                Main.mapHomes.remove(player.getName());
                player.sendMessage(ChatColor.BLUE + "Has borrado tu casa asignada");
            } catch (Exception e) {
                player.sendMessage(ChatColor.RED + "No tienes un home asignado");
            }
        }
        return false;
    }
}
