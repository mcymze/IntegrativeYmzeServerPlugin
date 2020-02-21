package dev.ekuinox.IntegrativeYmzeServerPlugin.services.dragonhead.listeners

import dev.ekuinox.IntegrativeYmzeServerPlugin.services.dragonhead.DragonHeadService
import dev.ekuinox.IntegrativeYmzeServerPlugin.utils.EventListener
import org.bukkit.Material
import org.bukkit.entity.{Fireball, Player, Projectile}
import org.bukkit.event.EventHandler
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.EquipmentSlot

class PlayerInteractEventListener(implicit service: DragonHeadService) extends EventListener {
  import PlayerInteractEventListener._
  import dev.ekuinox.IntegrativeYmzeServerPlugin.utils.Permissions._
  import dev.ekuinox.IntegrativeYmzeServerPlugin.services.dragonhead.permissions._

  @EventHandler
  def onPlayerInteract(event: PlayerInteractEvent): Unit = {
    import dev.ekuinox.IntegrativeYmzeServerPlugin.services.dragonhead.InteractTimer._
    for {
      event <- event.withMatch
      player <- event.getPlayer.withPermission(Fire)
      player <- player.withInteractTimerStop
    } {
      import dev.ekuinox.IntegrativeYmzeServerPlugin.services.dragonhead.Configure._

      val speed = 1
      val world = player.getWorld
      val fireball = world.spawn(player.getEyeLocation, classOf[Fireball])
      fireball.setVelocity(player.getEyeLocation.getDirection.multiply(speed))
      fireball.setShooter(player)
      fireball.setIsIncendiary(isTriggerFire())
      fireball.setYield(getExplosiveRadius())

      event.setCancelled(true)
    }
  }

}

object PlayerInteractEventListener {
  implicit class MatchPlayerInteractEvent(event: PlayerInteractEvent) {
    def withRightClick: Option[PlayerInteractEvent] = event.getAction match {
      case Action.RIGHT_CLICK_AIR | Action.RIGHT_CLICK_BLOCK => Some(event)
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
