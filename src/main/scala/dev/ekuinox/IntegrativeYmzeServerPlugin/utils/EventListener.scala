package dev.ekuinox.IntegrativeYmzeServerPlugin.utils

import dev.ekuinox.IntegrativeYmzeServerPlugin.{Main => Plugin}
import org.bukkit.event.{Listener => BukkitListener}

class EventListener(plugin: Plugin) extends BukkitListener {
  def register(): Unit = {
    plugin.getServer.getPluginManager.registerEvents(this, plugin)
  }
}
