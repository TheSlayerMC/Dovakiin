package net.dovakiin.api;

import net.dovakiin.Dovakiin;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityList.EntityEggInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;
import net.minecraftforge.fluids.FluidRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class DovakiinAPI {

	public static EntityPlayer player = Minecraft.getMinecraft().thePlayer;
	
	public static void addBucket(Fluid fluid, ItemStack modBucket){
		FluidContainerRegistry.registerFluidContainer(new FluidContainerData(FluidRegistry.getFluidStack(fluid.getName(), FluidContainerRegistry.BUCKET_VOLUME), modBucket, new ItemStack(Items.bucket)));
	}
	
	public static void registerMob(Class entityClass, String entityName) {
		int entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(entityClass, entityName, entityID);
		EntityList.IDtoClassMapping.put(entityID, entityClass);
		EntityList.entityEggs.put(entityID, new EntityEggInfo(entityID, 0x152678, 0x1534864));
	}
	
	public static ChatComponentTranslation addChatMessage(String colour, String str, Object... args) {
		ChatComponentTranslation ret = new ChatComponentTranslation(colour + str, args);
		return ret;
	}
	
	public static ChatComponentTranslation addChatMessage(String str, Object... args) {
		ChatComponentTranslation ret = new ChatComponentTranslation(str, args);
		return ret;
	}
	
	public static void openGui(int id){
		EntityPlayer p = Minecraft.getMinecraft().thePlayer;
		p.openGui(Dovakiin.instance, id, p.worldObj, (int)p.posX, (int)p.posY, (int)p.posZ);
	}
	
	public static int getWaterDepth(int posX,int posY, int posZ, World worldObj){
		int count = 0;
		while (worldObj.getBlock(posX, posY, posZ) == Blocks.water){
			posY++;
			count++;
		}
		return count;
	}

	public static String 
	BLACK = "\u00a70", DARK_BLUE = "\u00a71", DARK_GREEN = "\u00a72", DARK_AQUA = "\u00a73", DARK_RED = "\u00a74", DARK_PURPLE = "\u00a75", GOLD = "\u00a76", GRAY = "\u00a77",
	DARK_GRAY = "\u00a78", BLUE = "\u00a79", GREEN = "\u00a7a", AQUA = "\u00a7b", RED = "\u00a7c", LIGHT_PURPLE = "\u00a7d", YELLOW = "\u00a7e", WHITE = "\u00a7f";
	
}
