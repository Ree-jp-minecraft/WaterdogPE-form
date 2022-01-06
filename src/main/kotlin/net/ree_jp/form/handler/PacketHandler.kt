package net.ree_jp.form.handler

import com.nukkitx.protocol.bedrock.BedrockPacket
import com.nukkitx.protocol.bedrock.BedrockSession
import com.nukkitx.protocol.bedrock.packet.ModalFormResponsePacket
import dev.waterdog.waterdogpe.utils.types.PacketHandler
import net.ree_jp.form.FormReceiveService

class PacketHandler(session: BedrockSession, private val service: FormReceiveService) : PacketHandler(session) {

    override fun handlePacket(pk: BedrockPacket): Boolean {
        if (pk is ModalFormResponsePacket) {
            service.receive(pk)
        }
        return super.handlePacket(pk)
    }
}
