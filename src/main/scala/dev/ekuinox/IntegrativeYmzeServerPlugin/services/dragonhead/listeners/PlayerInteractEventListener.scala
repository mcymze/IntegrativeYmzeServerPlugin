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
  implicit class MatchPlayerInteractEvent(event: PlayerInteractEvent) {
    def withRightClick: Option[PlayerInteractEvent] = event.getAction match {
      case Action.RIGHT_CLICK_AIR | Action.RIGHT_CLICK_AIR => Some(event)
      case _ => None
    }
    def withOffHand: Option[PlayerInteractEvent] = if (event.getHand == EquipmentSlot.OFF_HAND) Some(event) else None
    def withDragonHead: Option[PlayerInteractEvent] = if (event.getItem.getType == Material.DRAGON_HEAD) Some(event) else None
    def withMatch: Option[PlayerInteractEvent] = for {
      event <- withRightClick
      event <- withOffHand
      event <- withDragonHead
    } yield event
  }
}
