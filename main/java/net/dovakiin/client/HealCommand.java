package net.dovakiin.client;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;

public class HealCommand extends CommandBase{

	@Override
	public String getCommandName() {
		return "Dovakiin";
	}

	@Override
	public String getCommandUsage(ICommandSender var1) {
		return "/Dovakiin Heal";
	}

	@Override
	public void processCommand(ICommandSender var1, String[] var2) {
		if(var2[0].equalsIgnoreCase("Heal")){
			EntityPlayer p = Minecraft.getMinecraft().thePlayer;
			heal(p);
			System.out.println(p.getFoodStats().getFoodLevel());
			if(p.getFoodStats().needFood())
				p.getFoodStats().addStats(20, 1);
			System.out.println(p.getFoodStats().getFoodLevel());
		}
	}
	
	public float heal(EntityPlayer p){
		float health = p.getHealth();
		return health =+ 20;
	}

	@Override
	public int getRequiredPermissionLevel() {
		return 1;
	}

	@Override
	public List addTabCompletionOptions(ICommandSender par1ICommandSender, String[] par2) {
		return par2.length == 1 ? getListOfStringsMatchingLastWord(par2, new String[] {"Heal"}) : null;
	}

}