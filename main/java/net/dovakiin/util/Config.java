package net.dovakiin.util;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config {

	private static Configuration cfg;
	
	public static boolean canShowWelcome;

	public static void init() {
		cfg = new Configuration(new File("./" + Utils.MOD_NAME + "/" + Utils.MOD_NAME + ".cfg"));
		cfg.load();
		misc();
		cfg.save();
	}
	
	public static void misc(){
		canShowWelcome = cfg.get("Misc", "Show the welcome message", true).getBoolean(true);
	}
}