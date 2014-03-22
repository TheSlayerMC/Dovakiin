package net.dovakiin.client;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandClearInventory;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandGive;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.server.CommandStop;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;

public class DovakiinCommands extends CommandBase{

	@Override
	public String getCommandName() {
		return "Dovakiin";
	}

	@Override
	public String getCommandUsage(ICommandSender var1) {
		return new String("/Dovakiin");
	}

	@Override
	public void processCommand(ICommandSender var1, String[] var2) {
		EntityPlayerMP p = getCommandSenderAsPlayer(var1);
		if(var2[0].equalsIgnoreCase("Heal")){
			if (p.getHealth() < p.getMaxHealth()) 
	    		p.heal(20);
	        
			if(p.getFoodStats().needFood())
				p.getFoodStats().addStats(20, 1);
		}
	}

	@Override
	public int getRequiredPermissionLevel() {
		return 1;
	}
	
	@Override
	public List addTabCompletionOptions(ICommandSender par1ICommandSender, String[] par2) {
		return par2.length == 1 ? getListOfStringsMatchingLastWord(par2, new String[] {"Clear", "Heal"}) : null;
	}

}