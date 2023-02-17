package net.ree_jp.form

import dev.waterdog.waterdogpe.player.ProxiedPlayer
import org.cloudburstmc.protocol.bedrock.packet.ModalFormResponsePacket

class FormReceiveService(private val store: FormStore, private val p: ProxiedPlayer) {

    fun receive(pk: ModalFormResponsePacket): Boolean {
        val form = store.takeForm(p.name, pk.formId)

        return if (form != null) {
            form.handle(pk.formData)
            true
        } else {
            false
        }
    }
}
