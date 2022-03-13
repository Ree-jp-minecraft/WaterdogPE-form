package net.ree_jp.form.elements

data class Input(val text: String, val holderText: String, val default: String) : CustomFormElement, CustomFormResult {
    private var result: String = ""

    override fun toMap() = mapOf("type" to "input", "text" to text, "placeholder" to holderText, "default" to default)

    override fun setResult(result: Any) {
        this.result = result.toString()
    }

    override fun getResult(): String {
        return result
    }
}
