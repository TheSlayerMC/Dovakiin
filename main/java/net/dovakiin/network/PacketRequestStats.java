package net.dovakiin.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.dovakiin.Dovakiin;
import net.minecraft.entity.player.EntityPlayer;

public class PacketRequestStats extends AbstractPacket {
	
	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
	}

	@Override
	public void handleClientSide(EntityPlayer player) {
	}

	@Override
	public void handleServerSide(EntityPlayer player) {
		Dovakiin.sendStats(player);
	}
}