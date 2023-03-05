package plugin.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import plugin.core.Main;

public class SetHome implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            LivingEntity le = player;
            if (le.isOnGround()) {
                Main.mapHomes.put(player.getName(),player.getLocation());
                player.sendMessage(ChatColor.GREEN + "Casa establecida");
            } else player.sendMessage(ChatColor.RED + "Necesitas estar en el suelo para establecer tu casa");
        }
        return false;
    }
}
