package me.chillywilly.mobMutePluginKotlin.events

import me.chillywilly.mobMutePluginKotlin.MobMute
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEntityEvent

class MobEvent : Listener {
    @EventHandler
    fun interact(event: PlayerInteractEntityEvent) {
        val player = event.player
        val entity = event.rightClicked

        val main = player.inventory.itemInMainHand.type == Material.NAME_TAG
        val off = player.inventory.itemInOffHand.type == Material.NAME_TAG

        val item_name = when {
            main -> player.inventory.itemInMainHand.displayName().toString()
            off -> player.inventory.itemInOffHand.displayName().toString()
            else -> ""
        }

        if (item_name == "[mute]") {
            entity.isSilent = true
            player.sendMessage(MobMute.getMMComp("mute"))
        } else if (item_name == "[unmute]") {
            entity.isSilent = false
            player.sendMessage(MobMute.getMMComp("unmute"))
        }
    }
}