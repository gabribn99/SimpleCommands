package plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import plugin.core.Main;
import plugin.entities.TPBean;

public class TPA implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player playerSender = (Player) sender;
        Player playerReceiver = (args != null) ? Bukkit.getPlayer(args[0]) : null;
        if (playerReceiver == null) {
            sender.sendMessage(ChatColor.RED + "El comando esta incompleto [Falta el destinatario]");
            return false;
        }
        if (sender instanceof Player) {
            playerSender.sendMessage(ChatColor.GOLD + "Petición de tp enviada");
            playerReceiver.sendMessage(ChatColor.GOLD + playerSender.getName() + " quiere teletransportarse contigo.\n-" + ChatColor.GREEN + " \"tpaccept\" para aceptar\n- " + ChatColor.RED + "\"tpdeny\" para denegar");
            Main.mapTps.put(playerReceiver.getName(), new TPBean(playerSender, playerReceiver));
        }
        return false;
    }
}
