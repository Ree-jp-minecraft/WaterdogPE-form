package net.ree_jp.form

import dev.waterdog.waterdogpe.event.defaults.PlayerLoginEvent
import dev.waterdog.waterdogpe.plugin.Plugin
import net.ree_jp.form.handler.PacketHandler

class FormPlugin : Plugin() {

    override fun onEnable() {
        proxy.eventManager.subscribe(PlayerLoginEvent::class.java) { ev ->
            ev.player.pluginUpstreamHandler =
                PacketHandler(ev.player.upstream, FormReceiveService(FormStore.instance, ev.player))
        }
    }
}
