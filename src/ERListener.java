package com.gmail.tclunan;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class ERListener implements Listener {

	@EventHandler
	public void onPlayerCommandPreprocessEvent(PlayerCommandPreprocessEvent event) {
		String n;
		Player p = event.getPlayer();
		n = p.getName();
		String[] label = event.getMessage().split(" ");
		if ((label[0].equalsIgnoreCase("/region") || label[0].equalsIgnoreCase("/rg"))) {StringBuilder builder = new StringBuilder();
			for (final String value : label) {builder.append(value + " ");
			}
			String eregion = builder.toString();
			Date now = new Date();
			SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
			try {
				String filename = "regionlog.txt";
				FileWriter fw = new FileWriter(filename, true);
				fw.write(("[" + format.format(now) + "] " + eregion + "Staff: "+ n + System.getProperty("line.separator")));
				fw.close();
			} catch (final IOException e) {
				e.printStackTrace();
			}
		}
	}
}
