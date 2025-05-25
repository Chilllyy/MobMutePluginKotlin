package me.chillywilly.mobMutePluginKotlin.events

import me.chillywilly.mobMutePluginKotlin.MobMute
import net.kyori.adventure.text.minimessage.MiniMessage
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

        val MM = MiniMessage.miniMessage()

        val item_name = when {
            main -> MM.serialize(player.inventory.itemInMainHand.displayName()).lowercase()
            off -> MM.serialize(player.inventory.itemInOffHand.displayName()).lowercase()
            else -> "empty"
        }

        if ("[mute]" in item_name) {
            entity.isSilent = true
            player.sendMessage(MobMute.instance.getMMComp("mute"))
            event.isCancelled = true
        } else if ("[unmute]" in item_name) {
            entity.isSilent = false
            player.sendMessage(MobMute.instance.getMMComp("unmute"))
            event.isCancelled = true
        }
    }
}