package net.dovakiin.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.dovakiin.Dovakiin;
import net.minecraft.entity.player.EntityPlayer;

public class PacketOpenGui extends AbstractPacket {
	
	private int id;

	public PacketOpenGui setID(int id) {
		this.id = id;
		return this;
	}

	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		buffer.writeInt(id);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		id = buffer.readInt();
	}

	@Override
	public void handleClientSide(EntityPlayer player) { }

	@Override
	public void handleServerSide(EntityPlayer player) {
		player.openGui(Dovakiin.instance, id, player.worldObj, (int) player.posX, (int) player.posY, (int) player.posZ);
	}
}
