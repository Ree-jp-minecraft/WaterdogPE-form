package net.ree_jp.form.elements

data class Button(val text: String, val func: Runnable?) : ModalFormButton, SimpleFormElement {
    override fun toMap() = mapOf("text" to text)

    override fun call() {
        func?.run()
    }
}
