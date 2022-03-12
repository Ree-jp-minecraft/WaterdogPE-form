package net.ree_jp.form.elements

class Slider(private val text: String, private val min: Int, private val max: Int, private val default: Int) :
    CustomFormElement, CustomFormResult {
    private var result: Int = 0

    override fun toMap() = mapOf("type" to "slider", "text" to text, "min" to min, "max" to max, "default" to default)

    override fun setResult(result: String) {
        this.result = result.toInt()
    }

    override fun getResult(): Int {
        return result
    }
}
