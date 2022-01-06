package net.ree_jp.form

import com.nukkitx.protocol.bedrock.packet.ModalFormResponsePacket
import dev.waterdog.waterdogpe.player.ProxiedPlayer

class FormReceiveService(private val store: FormStore, private val p: ProxiedPlayer) {

    fun receive(pk: ModalFormResponsePacket) {
        store.takeForm(p.name, pk.formId)?.handle(pk.formData)
    }
}
