package dev.ekuinox.IntegrativeYmzeServerPlugin.utils

import org.bukkit.event.{Listener => BukkitListener}

class EventListener(implicit service: Service) extends BukkitListener {
  def register(): Unit = {
    service.getPlugin.getServer.getPluginManager.registerEvents(this, service.getPlugin)
  }
}
