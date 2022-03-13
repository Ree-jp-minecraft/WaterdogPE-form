package net.ree_jp.form.type

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.nukkitx.protocol.bedrock.packet.ModalFormRequestPacket
import dev.waterdog.waterdogpe.player.ProxiedPlayer
import net.ree_jp.form.FormStore
import net.ree_jp.form.elements.ModalFormButton

// On some platforms, it is possible to force the form to close, but this will result in the same behavior as pressing the false button.
class ModalForm(
    private val title: String,
    private val content: String,
    private val trueButton: ModalFormButton,
    private val falseButton: ModalFormButton
) : Form() {

    override fun sendForm(p: ProxiedPlayer) {
        val pk = ModalFormRequestPacket()

        pk.formData = Gson().toJson(
            mapOf(
                "type" to "modal",
                "title" to title,
                "content" to content,
                "button1" to trueButton.toButton(),
                "button2" to falseButton.toButton()
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
            val result = Gson().fromJson(response, Boolean::class.java)
            if (result) {
                trueButton.call()
            } else {
                falseButton.call()
            }
        } catch (e: JsonSyntaxException) {
            e.printStackTrace()
        }
    }
}
