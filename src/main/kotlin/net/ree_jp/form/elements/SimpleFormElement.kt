package net.ree_jp.form.elements

interface SimpleFormElement {
    fun toMap(): Map<String, String>

    fun call()
}
