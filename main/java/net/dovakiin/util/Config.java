package net.dovakiin.util;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config {

	private static Configuration cfg;
	private static File file;
	
	public static boolean canShowWelcome, canShowInformation;
	
	
	public static void init() {
		file = new File("./Dovakiin/Dovakiin.cfg");
		cfg = new Configuration(file);
		
		canShowWelcome 			= cfg.get("Misc", "Can show the welcome messages"  , true).getBoolean(true);
		canShowInformation 		= cfg.get("Misc", "Can show the ingame information", true).getBoolean(true);
	}
}