package com.gmail.tclunan;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class RegionLogger extends JavaPlugin {
	String str;

	@Override
	public void onEnable() {
		getLogger().info("RegionLogger has been enabled! ");
		getServer().getPluginManager().registerEvents(new ERListener(), this);
	}

	@Override
	public void onDisable() {
		getLogger().info("RegionLogger has been disabled! ");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,String label, String[] args) {
		Player player = (Player) sender;
		if (command.getName().equalsIgnoreCase("er")) {
			if (args.length >= 1) {
				if ((sender instanceof Player)) {
					if (player.hasPermission("log.er")) {
						try {
							String u = args[0];
							Reader reader = new FileReader("regionlog.txt");
							BufferedReader in = new BufferedReader(reader);

							while ((str = in.readLine()) != null) {
								Pattern p = Pattern.compile(" " + u + " ");
								Matcher m = p.matcher(str);
								if (m.find()) {sender.sendMessage(ChatColor.YELLOW + str);
								}
							}
							reader.close();
							in.close();
							return true;
						} catch (final IOException e) {
							e.printStackTrace();
						}
					}
				}
				return true;
			}
		}
		return false;
	}
}