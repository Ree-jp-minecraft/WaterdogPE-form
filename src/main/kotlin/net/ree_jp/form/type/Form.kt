package net.ree_jp.form.type

import dev.waterdog.waterdogpe.player.ProxiedPlayer

abstract class Form {

    var formID: Int? = null

    abstract fun sendForm(p: ProxiedPlayer)

    abstract fun handle(response: String?)
}
