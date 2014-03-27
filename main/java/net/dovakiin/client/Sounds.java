package net.dovakiin.client;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class Sounds {
	
	public static final String eggHatch = "eggHatching";

	public static String playSound(String name, World world, Entity entity, float volume, float pitch){
		world.playSoundAtEntity(entity, name, (float)volume, (float)pitch); 
		return name;
	}

	public static String playSound(String name, World world, Entity entity){
		world.playSoundAtEntity(entity, name, 1, 1); 
		return name;
	}
	
}