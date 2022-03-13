package net.ree_jp.form.elements

class StepSlider(private val text: String, private val steps: List<Int>, private val default: Int) :
    CustomFormElement,
    CustomFormResult {
    private var result: Int = 0

    override fun toMap() = mapOf("type" to "step_slider", "text" to text, "steps" to steps, "default" to default)

    override fun setResult(result: Any) {
        this.result = (result as Double).toInt()
    }

    override fun getResult(): Int {
        return result
    }
}
