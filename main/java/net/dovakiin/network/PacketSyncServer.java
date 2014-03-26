package net.dovakiin.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.dovakiin.DataHelper;
import net.minecraft.entity.player.EntityPlayer;

public class PacketSyncServer extends AbstractPacket{

	public static int coins, sword, level, mob;
	
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
		
	}

	@Override
	public void handleServerSide(EntityPlayer player) {
		DataHelper.setCoins(player, DataHelper.getCoins(player));
		DataHelper.setSwordLevel(player, DataHelper.getSwordLevel(player));
	}

}
