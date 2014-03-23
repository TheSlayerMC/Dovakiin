package net.dovakiin.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.dovakiin.Dovakiin;
import net.minecraft.entity.player.EntityPlayer;

public class PacketRefreshStats extends AbstractPacket {
	public int level, coins, mobLevel, swordLevel;

	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		buffer.writeInt(level);
		buffer.writeInt(swordLevel);
		buffer.writeInt(coins);
		buffer.writeInt(mobLevel);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		level = buffer.readInt();
		swordLevel = buffer.readInt();
		coins = buffer.readInt();
		mobLevel = buffer.readInt();
	}

	@Override
	public void handleClientSide(EntityPlayer player) {
		Dovakiin.level = level;
		Dovakiin.swordLevel = swordLevel;
		Dovakiin.coins = coins;
		Dovakiin.mobLevel = mobLevel;
	}

	@Override
	public void handleServerSide(EntityPlayer player) { }
}