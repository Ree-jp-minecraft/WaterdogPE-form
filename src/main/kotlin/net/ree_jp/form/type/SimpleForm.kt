package net.ree_jp.form.type

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.nukkitx.protocol.bedrock.packet.ModalFormRequestPacket
import dev.waterdog.waterdogpe.player.ProxiedPlayer
import net.ree_jp.form.FormStore
import net.ree_jp.form.elements.SimpleFormElement

class SimpleForm(private val title: String, private val content: String) : Form() {

    private val elements = mutableListOf<SimpleFormElement>()

    override fun sendForm(p: ProxiedPlayer) {
        val pk = ModalFormRequestPacket()
        val buttonsData = mutableListOf<Map<String, String>>()

        elements.forEach { button ->
            buttonsData.add(button.toMap())
        }
        pk.formData = Gson().toJson(
            mapOf(
                "type" to "form",
                "title" to title,
                "content" to content,
                "buttons" to buttonsData
            )
        )
        pk.formId = FormStore.instance.storeForm(p.name, this, formID)
        p.sendPacket(pk)
    }

    override fun handle(response: String?) {
        if (response == null) {
            return
        }

        try {
            val buttonsID = Gson().fromJson(response, Int::class.java)
            if (buttonsID != null) {
                elements[buttonsID].call()
            } else {
                print("unknown simple form response$response")
            }
        } catch (e: JsonSyntaxException) {
            e.printStackTrace()
        }
    }

    fun addElement(vararg button: SimpleFormElement) {
        button.forEach { elements.add(it) }
    }
}
