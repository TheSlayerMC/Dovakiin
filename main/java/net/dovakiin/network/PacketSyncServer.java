package net.dovakiin.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.dovakiin.DataHelper;
import net.dovakiin.Dovakiin;
import net.minecraft.entity.player.EntityPlayer;

public class PacketSyncServer extends AbstractPacket{

	private int coins, mob;
	private int sword, level;
	
	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		buffer.writeInt(coins);
		buffer.writeInt(sword);
		buffer.writeInt(level);
		buffer.writeInt(mob);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		coins = buffer.readInt();
		sword = buffer.readInt();
		level = buffer.readInt();
		mob = buffer.readInt();
	}

	@Override
	public void handleClientSide(EntityPlayer player) {
		Dovakiin.coins = coins;
		Dovakiin.swordLevel = sword;
		Dovakiin.level = level;
		Dovakiin.mobLevel = mob;
	}

	@Override
	public void handleServerSide(EntityPlayer player) { }
	
	public PacketSyncServer applyStats(EntityPlayer player) {
		coins = DataHelper.getCoins(player);
		sword = DataHelper.getSwordLevel(player);
		level = DataHelper.getLevel(player);
		mob = DataHelper.getMobLevel(player);
		return this;
	}
}