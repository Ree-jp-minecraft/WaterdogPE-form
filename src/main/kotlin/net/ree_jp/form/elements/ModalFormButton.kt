package net.ree_jp.form.elements

interface ModalFormButton {
    fun toMap(): Map<String, String>

    fun call()
}
