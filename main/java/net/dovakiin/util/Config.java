package net.dovakiin.util;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config {

	private static Configuration cfg;
	
	public static boolean canShowWelcome;
	
	
	public static void init() {
		cfg = new Configuration(new File("./" + Utils.MOD_NAME + "/Dovakiin.cfg"));
		cfg.load();
		canShowWelcome 			= cfg.get("Misc", "Can show the welcome messages"  , true).getBoolean(true);
		
		
		cfg.save();
	}
}