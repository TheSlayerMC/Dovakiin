package net.dovakiin.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.dovakiin.DataHelper;
import net.dovakiin.Dovakiin;
import net.dovakiin.api.DovakiinAPI;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.GameData;

public class PacketRequestBuy extends AbstractPacket {
	
	public boolean item;
	public String name;
	public int amount, cost;

	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		buffer.writeBoolean(item);
		ByteBufUtils.writeUTF8String(buffer, name);
		buffer.writeInt(amount);
		buffer.writeInt(cost);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		item = buffer.readBoolean();
		name = ByteBufUtils.readUTF8String(buffer);
		amount = buffer.readInt();
		cost = buffer.readInt();
	}

	@Override
	public void handleClientSide(EntityPlayer player) { }

	@Override
	public void handleServerSide(EntityPlayer player) {
		ItemStack is = null;
		if(item) {
			is = new ItemStack(GameData.itemRegistry.get(name));
		} else {
			is = new ItemStack(GameData.blockRegistry.get(name));
		}
		if(player.capabilities.isCreativeMode) {
			player.inventory.addItemStackToInventory(is);
		} else if(DataHelper.getCoins(player) >= cost) {
			player.inventory.addItemStackToInventory(is);
			useCoins(player, cost);
		} else { 
			int more = cost - DataHelper.getCoins(player);
			player.addChatMessage(DovakiinAPI.addChatMessage(EnumChatFormatting.RED + "You need " + EnumChatFormatting.GOLD + more + EnumChatFormatting.RED + " more coins!"));
		}
	}

	public static int useCoins(EntityPlayer player, int coins) {
		int playerCoins = DataHelper.getCoins(player);
		if(playerCoins > 0) {
			DataHelper.setCoins(player, playerCoins - coins);
			return DataHelper.getCoins(player);
		} else if(-1 > playerCoins) {
			DataHelper.setCoins(player, 0);
			return 0;
		}
		return playerCoins;
	}
}