package net.ree_jp.form

import net.ree_jp.form.type.Form

class FormStore {
    companion object {
        val instance: FormStore = FormStore()
    }

    private val allForms = mutableMapOf<String, MutableMap<Int, Form>>()
    private val formIDCounter = mutableMapOf<String, Int>()

    fun storeForm(user: String, form: Form, id: Int?): Int {
        var finalID = id
        if (finalID == null) {
            finalID = createFormID(user)
        }

        val forms = allForms[user]
        if (forms == null) {
            allForms[user] = mutableMapOf(finalID to form)
        } else {
            forms[finalID] = form
            allForms[user] = forms
        }
        return finalID
    }

    private fun createFormID(user: String): Int {
        var id = formIDCounter[user]
        if (id == null) {
            id = 3000
        } else {
            id += 1
        }
        formIDCounter[user] = id
        return id
    }

    fun takeForm(user: String, id: Int): Form? {
        allForms[user]?.let {
            val result = it[id]
            it.remove(id)
            return result
        }

        return null
    }
}
