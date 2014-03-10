package net.dovakiin.client;

import net.dovakiin.api.ContainerEmpty;
import net.dovakiin.client.gui.GuiStats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	public static int gui = 0, statsGUI = gui++;
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch(ID) {
		case (0):
			return new ContainerEmpty();
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch(ID) {
		case (0):
			return new GuiStats();
		}
		return null;
	}

}
