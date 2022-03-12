package net.ree_jp.form.elements

class Dropdown(private val text: String, private val options: List<String>) : CustomFormElement, CustomFormResult {
    private var result: Int = 0

    override fun toMap() = mapOf("type" to "dropdown", "text" to text, "options" to options)

    override fun setResult(result: String) {
        this.result = result.toInt()
    }

    override fun getResult(): Int {
        return result
    }

    fun getResultValue(): String? {
        return options[result]
    }
}
