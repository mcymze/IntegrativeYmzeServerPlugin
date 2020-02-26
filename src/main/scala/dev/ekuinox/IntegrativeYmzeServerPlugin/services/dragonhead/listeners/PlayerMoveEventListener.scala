package dev.ekuinox.IntegrativeYmzeServerPlugin.services.dragonhead.listeners

import dev.ekuinox.IntegrativeYmzeServerPlugin.services.dragonhead.DragonHeadService
import dev.ekuinox.IntegrativeYmzeServerPlugin.utils.EventListener
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerMoveEvent

class PlayerMoveEventListener(implicit service: DragonHeadService) extends EventListener {

  @EventHandler
  def onPlayerMove(event: PlayerMoveEvent): Unit = {
    import dev.ekuinox.IntegrativeYmzeServerPlugin.services.dragonhead.ImplicitPlayerExtended._

    val player = event.getPlayer
    if (!player.isActiveDragonFlight && player.isOnGround) {
    // 接地していない場合は無視
    if (!player.isOnGround) return
      player.deactivateDragonFlight()
    }

  }

}
