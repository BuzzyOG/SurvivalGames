/**
 * Name: ChatCommand.java Created: 8 December 2013
 *
 * @version 1.0.0
 */

package co.q64.survivalgames.command.subcommands.party;

import java.util.UUID;

import org.bukkit.entity.Player;

import co.q64.survivalgames.command.subcommands.SubCommand;
import co.q64.survivalgames.locale.I18N;
import co.q64.survivalgames.managers.SGApi;

public class ChatCommand implements SubCommand {

	/**
	 * Switches chat mode from party chat to global chat and vice versa for
	 * player
	 *
	 * @param player The player executing the command
	 */
	@Override
	public void execute(String cmd, Player player, String args[]) {
		if (cmd.equalsIgnoreCase("chat")) {
			UUID id = SGApi.getPartyManager().getPlayers().get(player.getName());
			if (id != null) {
				if (SGApi.getPartyManager().getPlayers().containsKey(player.getName())) {
					SGApi.getPartyManager().getPlayers().remove(player.getName());
					player.sendMessage(org.bukkit.ChatColor.YELLOW + I18N.getLocaleString("NO_CHAT"));
				} else {
					SGApi.getPartyManager().getPartyChat().add(player.getName());
					player.sendMessage(org.bukkit.ChatColor.YELLOW + I18N.getLocaleString("CHAT"));
				}
			} else {
				player.sendMessage(org.bukkit.ChatColor.YELLOW + I18N.getLocaleString("PARTY_TO_CHAT"));
			}
		}

	}
}
