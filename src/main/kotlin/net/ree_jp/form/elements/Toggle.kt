package net.ree_jp.form.elements

class Toggle(private val text: String, private val default: Boolean = false) : CustomFormElement, CustomFormResult {
    private var result: Boolean = false

    override fun toMap() = mapOf("type" to "toggle", "text" to text, "default" to default)

    override fun setResult(result: String) {
        this.result = result.toBoolean()
    }

    override fun getResult(): Boolean {
        return result
    }
}
