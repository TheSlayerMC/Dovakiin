package net.dovakiin.client;

import net.dovakiin.api.ContainerEmpty;
import net.dovakiin.client.gui.GuiMerchant;
import net.dovakiin.client.gui.GuiStartingBook;
import net.dovakiin.client.gui.GuiStats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	public static int gui = 0, stats = gui++, startingBook = gui++, greenDragon = gui++, merchent = gui++;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == stats)
			return new ContainerEmpty();
		if(ID == startingBook)
			return new ContainerEmpty();
		if(ID == merchent)
			return new ContainerEmpty();
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == stats)
			return new GuiStats();
		if(ID == startingBook)
			return new GuiStartingBook();
		if(ID == merchent)
			return new GuiMerchant();
		return null;
	}

}
