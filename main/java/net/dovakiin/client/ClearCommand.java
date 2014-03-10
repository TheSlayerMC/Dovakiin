package net.dovakiin.client;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandGive;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.server.CommandStop;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;

public class ClearCommand extends CommandBase{

	@Override
	public String getCommandName() {
		return "Dovakiin";
	}

	@Override
	public String getCommandUsage(ICommandSender var1) {
		return "/Dovakiin Clear";
	}

	@Override
	public void processCommand(ICommandSender var1, String[] var2) {
		if(var2[0].equalsIgnoreCase("Clear")){
			EntityPlayer p = Minecraft.getMinecraft().thePlayer;
			p.inventory.clearInventory(new Item(), -1);
		}
	}

	@Override
	public int getRequiredPermissionLevel() {
		return 1;
	}

	@Override
	public List addTabCompletionOptions(ICommandSender par1ICommandSender, String[] par2) {
		return par2.length == 1 ? getListOfStringsMatchingLastWord(par2, new String[] {"Clear"}) : null;
	}

}