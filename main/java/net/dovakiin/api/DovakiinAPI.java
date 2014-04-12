package net.dovakiin.api;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.dovakiin.Dovakiin;
import net.dovakiin.network.PacketOpenGui;
import net.dovakiin.util.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandHandler;
import net.minecraft.command.ICommand;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;
import net.minecraftforge.fluids.FluidRegistry;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry.IVillageCreationHandler;

public class DovakiinAPI {

	public static Logger logger = Logger.getLogger(Utils.MOD_ID);
	public static Random rand = new Random();
	private static EntityPlayer clientPlayer = Minecraft.getMinecraft().thePlayer;
	
	public static EntityPlayer getClientPlayer(){
		return clientPlayer;
	}
	
	public static String getClientPlayerName(){
		return clientPlayer.getDisplayName();
	}
	
	public static void addBucket(Fluid fluid, ItemStack modBucket){
		FluidContainerRegistry.registerFluidContainer(new FluidContainerData(FluidRegistry.getFluidStack(fluid.getName(), FluidContainerRegistry.BUCKET_VOLUME), modBucket, new ItemStack(Items.bucket)));
	}

	public static void registerMob(Class entityClass, String entityName) {
		int entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(entityClass, entityName, entityID, 0x152678, 0x1534864);
	}

	public static void registerEntity(Class entityClass, String entityName) {
		int entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(entityClass, entityName, entityID);
	}
	
	public static void registerEggEntity(Class entityClass, String colour) {
		int entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(entityClass, colour + " Egg", entityID);
	}


	public static ChatComponentTranslation addChatMessage(String colour, String str, Object... args) {
		ChatComponentTranslation ret = new ChatComponentTranslation(colour + str, args);
		return ret;
	}

	public static void addVillagePiece(Class c, String s){
		try{
			MapGenStructureIO.func_143031_a(c, s);
		}catch(Exception e){
			logger.log(Level.WARNING, "[Dovakiin] Failed To Spawn The Extra Village Pieces With The ID: " + s);
		}
	}
	
	public static void addVillageCreationHandler(IVillageCreationHandler v){
		VillagerRegistry.instance().registerVillageCreationHandler(v);
	}

	public static void registerCommand(ICommand o){
		if (MinecraftServer.getServer().getCommandManager() instanceof ServerCommandManager) {
			((CommandHandler) MinecraftServer.getServer().getCommandManager()).registerCommand(o);
		}
	}

	public static ChatComponentTranslation addChatMessage(String str, Object... args) {
		ChatComponentTranslation ret = new ChatComponentTranslation(str, args);
		return ret;
	}
	
	public static void sendChatMessage(EntityPlayer player, String message){
		player.addChatMessage(addChatMessage(message));
	}

	public static void openGui(int id){
		Dovakiin.packetHandler.sendToServer(new PacketOpenGui().setID(id));
	}
	
	public static void sendMessageToAll(String message){
		FMLClientHandler.instance().getClient().ingameGUI.getChatGUI().printChatMessage(addChatMessage(AQUA + "[" + BLUE + "Dovakiin" + AQUA + "] " + AQUA + message));
	}
	
	private static ChatComponentTranslation sendMessageToAll(String m, Object... arg){
		ChatComponentTranslation ret = new ChatComponentTranslation(m, arg);
		Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(ret);
		return ret;
	}

	public static int getWaterDepth(int posX, int posY, int posZ, World worldObj){
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
