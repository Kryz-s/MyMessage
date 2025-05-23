package me.kryz.mymessage.nms.v1_20_R3.listeners;

import me.kryz.mymessage.common.Prefix;
import me.kryz.mymessage.common.packet.PacketEvent;
import me.kryz.mymessage.common.packet.PacketListener;
import me.kryz.mymessage.nms.v1_20_R3.ComponentSerializer;
import net.kyori.adventure.text.Component;
import net.minecraft.network.protocol.game.ClientboundSystemChatPacket;
import org.bukkit.entity.Player;

public final class SystemChatPacketListener implements PacketListener<ClientboundSystemChatPacket> {

    @Override
    public void read(Player var1, PacketEvent<ClientboundSystemChatPacket> var2) {

    }

    @Override
    public void write(Player player, PacketEvent<ClientboundSystemChatPacket> packetEvent) {
        final var packet = packetEvent.getPacket();
        final var component = packet.a();
        if(!Prefix.startsWith(component.getString()))
            return;
        final String parsed = ComponentSerializer.asJson(component);
        final Component processed = ComponentSerializer.process(parsed, player);
        final var toLegacy = ComponentSerializer.asLegacy(processed);
        final var newPacket = new ClientboundSystemChatPacket(toLegacy, packet.d());
        packetEvent.setPacket(newPacket);
    }

    @Override
    public Class<?> getPacketClass() {
        return ClientboundSystemChatPacket.class;
    }
}
