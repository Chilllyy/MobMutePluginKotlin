package me.chillywilly.mobMutePluginKotlin

import me.chillywilly.mobMutePluginKotlin.events.MobEvent
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

object MobMute : JavaPlugin() {

    val plugin = this

    override fun onEnable() {
        saveResource("/messages.yml", false)
        server.pluginManager.registerEvents(MobEvent(), this)
    }

    fun getMMComp(path: String) : Component {
        var file : File = File("$dataFolder/messages.yml")
        if (!file.exists()) {
            saveResource("/messages.yml", false)
            file = File("$dataFolder/messages.yml")

        }

        val config = YamlConfiguration.loadConfiguration(file)
        val rawMsg = config.getString(path)
        val rawPrefix = config.getString("prefix")

        val parser = MiniMessage.miniMessage()

        return parser.deserialize("$rawPrefix $rawMsg")
    }
}