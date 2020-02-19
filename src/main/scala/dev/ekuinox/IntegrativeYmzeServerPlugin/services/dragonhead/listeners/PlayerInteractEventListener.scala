package dev.ekuinox.IntegrativeYmzeServerPlugin.services.dragonhead.listeners

import dev.ekuinox.IntegrativeYmzeServerPlugin.services.dragonhead.DragonHeadService
import dev.ekuinox.IntegrativeYmzeServerPlugin.utils.EventListener
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerInteractEvent

class PlayerInteractEventListener(implicit service: DragonHeadService) extends EventListener {
  import PlayerInteractEventListener._

  @EventHandler
  def onPlayerInteract(event: PlayerInteractEvent): Unit = {

  }

}

object PlayerInteractEventListener {

}
