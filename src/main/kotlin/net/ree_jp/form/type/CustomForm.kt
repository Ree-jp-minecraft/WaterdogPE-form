package net.ree_jp.form.type

import com.google.gson.Gson
import com.nukkitx.protocol.bedrock.packet.ModalFormRequestPacket
import dev.waterdog.waterdogpe.player.ProxiedPlayer
import net.ree_jp.form.FormStore
import net.ree_jp.form.elements.CustomFormElement

class CustomForm(private val title: String) : Form() {

    private val elements = mutableListOf<CustomFormElement>()

    override fun sendForm(p: ProxiedPlayer) {
        val pk = ModalFormRequestPacket()
        val elementsData = mutableListOf<Map<String, String>>()

        elements.forEach { button ->
            elementsData.add(button.toMap())
        }

        pk.formData = Gson().toJson(
            mapOf(
                "type" to "custom_form",
                "title" to title,
                "content" to elementsData
            )
        )
        FormStore.instance.storeForm(p.name, this, formID)
        p.sendPacket(pk)
    }

    override fun handle(response: String?) {
        TODO("Not yet implemented")
    }

    fun addElement(vararg button: CustomFormElement) {
        button.forEach { elements.add(it) }
    }
}
