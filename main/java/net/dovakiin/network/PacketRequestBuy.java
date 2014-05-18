package net.dovakiin.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.dovakiin.api.DovakiinAPI;
import net.dovakiin.event.level_system.LevelHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.GameData;

public class PacketRequestBuy extends AbstractPacket {

	public boolean item, metadata, metadataBlock;
	public String name;
	public int amount, cost, meta;

	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		buffer.writeBoolean(item);
		buffer.writeBoolean(metadata);
		buffer.writeBoolean(metadataBlock);
		ByteBufUtils.writeUTF8String(buffer, name);
		buffer.writeInt(amount);
		buffer.writeInt(cost);
		buffer.writeInt(meta);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		item = buffer.readBoolean();
		metadata = buffer.readBoolean();
		metadataBlock = buffer.readBoolean();
		name = ByteBufUtils.readUTF8String(buffer);
		amount = buffer.readInt();
		cost = buffer.readInt();
		meta = buffer.readInt();
	}

	@Override
	public void handleClientSide(EntityPlayer player) { }

	@Override
	public void handleServerSide(EntityPlayer player) {
		ItemStack is = null;
		if(item) {
			is = new ItemStack(GameData.getItemRegistry().getObject(name));
		} if(metadata){
			is = new ItemStack(GameData.getItemRegistry().getObject(name), 1, meta);
		} if(metadataBlock){	
			is = new ItemStack(GameData.getBlockRegistry().getObject(name), 1, meta);
		} if(!item) {
			is = new ItemStack(GameData.getBlockRegistry().getObject(name));
		} 
		if(player.capabilities.isCreativeMode) {
			player.inventory.addItemStackToInventory(is);
		} else if(LevelHelper.getCoins() >= cost) {
			player.inventory.addItemStackToInventory(is);
			useCoins(player, cost);
		} else { 
			int more = cost - LevelHelper.getCoins();
			player.addChatMessage(DovakiinAPI.addChatMessage(EnumChatFormatting.RED + "You need " + EnumChatFormatting.GOLD + more + EnumChatFormatting.RED + " more coins!"));
		}
	}

	public static int useCoins(EntityPlayer player, int coins) {
		int playerCoins = LevelHelper.getCoins();
		if(playerCoins > 0) {
			LevelHelper.setCoins(playerCoins - coins);
			return LevelHelper.getCoins();
		} else if(-1 > playerCoins) {
			LevelHelper.setCoins(0);
			return 0;
		}
		return playerCoins;
	}
}