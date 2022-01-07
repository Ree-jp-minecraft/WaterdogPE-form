package net.ree_jp.form.elements

data class Button(val text: String, val func: (() -> Unit)?) : ModalFormButton, SimpleFormElement {
    override fun toMap() = mapOf("text" to text)

    override fun call() {
        func?.let { it() }
    }
}
