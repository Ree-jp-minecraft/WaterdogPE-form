package net.ree_jp.form.type

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import com.nukkitx.protocol.bedrock.packet.ModalFormRequestPacket
import dev.waterdog.waterdogpe.player.ProxiedPlayer
import net.ree_jp.form.FormStore
import net.ree_jp.form.elements.CustomFormElement
import net.ree_jp.form.elements.CustomFormResult

class CustomForm(private val title: String, private val func: Runnable, private val closeFunc: Runnable? = null) :
    Form() {

    private val elements = mutableListOf<CustomFormElement>()

    override fun sendForm(p: ProxiedPlayer) {
        val pk = ModalFormRequestPacket()
        val elementsData = mutableListOf<Map<String, Any>>()

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
        pk.formId = FormStore.instance.storeForm(p.name, this, formID)
        p.sendPacket(pk)
    }

    override fun handle(response: String?) {
        if (response == null) {
            return
        }

        try {
            val results: List<Any>? = Gson().fromJson(response, object : TypeToken<List<Any?>>() {}.type)
            if (results != null) {
                elements.forEachIndexed { index, element ->
                    if (element is CustomFormResult) {
                        results[index].let { element.setResult(it) }
                    }
                }
                func.run()
            } else closeFunc?.run()
        } catch (e: JsonSyntaxException) {
            e.printStackTrace()
        }
    }

    fun addElement(vararg button: CustomFormElement) {
        button.forEach { elements.add(it) }
    }
}
