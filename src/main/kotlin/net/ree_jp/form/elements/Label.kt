package net.ree_jp.form.elements

data class Label(val text: String) : CustomFormElement {
    override fun toMap() = mapOf("type" to "label", "text" to text)
}
