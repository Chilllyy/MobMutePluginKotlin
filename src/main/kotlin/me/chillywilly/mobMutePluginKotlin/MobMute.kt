package me.chillywilly.mobMutePluginKotlin

import me.chillywilly.mobMutePluginKotlin.events.MobEvent
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class MobMute : JavaPlugin() {

    companion object {
        lateinit var instance: MobMute
        private set
    }


    override fun onEnable() {
        saveResource("messages.yml", false)
        server.pluginManager.registerEvents(MobEvent(), this)
        MobMute.instance = this
    }

    fun getMMComp(path: String) : Component {
        var file : File = File("$dataFolder/messages.yml")
        if (!file.exists()) {
            saveResource("messages.yml", false)
            file = File("$dataFolder/messages.yml")

        }

        val config = YamlConfiguration.loadConfiguration(file)
        val rawMsg = config.getString(path)
        val rawPrefix = config.getString("prefix")

        val parser = MiniMessage.miniMessage()

        return parser.deserialize("${convertLegtoMM(rawPrefix)} ${convertLegtoMM(rawMsg)}")
    }

    fun convertLegtoMM(msg: String?) : String {
        return msg?.replace("&0", "<black>0")
            ?.replace("&1", "<dark_blue>")
            ?.replace("&2", "<dark_green>")
            ?.replace("&3", "<dark_aqua>")
            ?.replace("&4", "<dark_red>")
            ?.replace("&5", "<dark_purple>")
            ?.replace("&6", "<gold>")
            ?.replace("&7", "<gray>")
            ?.replace("&8", "<dark_gray>")
            ?.replace("&9", "<blue>")
            ?.replace("&a", "<green>")
            ?.replace("&b", "<aqua>")
            ?.replace("&c", "<red>")
            ?.replace("&d", "<light_purple>")
            ?.replace("&e", "<yellow>")
            ?.replace("&f", "<white>")
            ?.replace("&l", "<bold>")
            ?.replace("&n", "<underline>")
            ?.replace("&o", "<italic>")
            ?.replace("&m", "<strikethrough>")
            ?.replace("&k", "<magic>")
            ?.replace("&r", "<reset>") ?: "null"
    }
}