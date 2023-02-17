package net.ree_jp.form.handler

import dev.waterdog.waterdogpe.network.PacketDirection
import dev.waterdog.waterdogpe.network.protocol.handler.PluginPacketHandler
import net.ree_jp.form.FormReceiveService
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket
import org.cloudburstmc.protocol.bedrock.packet.ModalFormResponsePacket
import org.cloudburstmc.protocol.common.PacketSignal

class PacketHandler(private val service: FormReceiveService) : PluginPacketHandler {

    override fun handlePacket(pk: BedrockPacket, direction: PacketDirection): PacketSignal {
        if (pk is ModalFormResponsePacket) {
            if (service.receive(pk)) {
                return PacketSignal.HANDLED
            }
        }
        return PacketSignal.UNHANDLED
    }
}
