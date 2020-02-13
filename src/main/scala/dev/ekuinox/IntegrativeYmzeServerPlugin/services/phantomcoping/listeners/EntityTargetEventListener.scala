package dev.ekuinox.IntegrativeYmzeServerPlugin.services.phantomcoping.listeners

import dev.ekuinox.IntegrativeYmzeServerPlugin.services.phantomcoping.{ImplicitConversions, PhantomCopeService}
import dev.ekuinox.IntegrativeYmzeServerPlugin.utils.EventListener
import org.bukkit.entity.{Entity, Phantom, Player}
import org.bukkit.entity.EntityType._
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.EntityTargetEvent

class EntityTargetEventListener(implicit service: PhantomCopeService) extends EventListener {
  import EntityTargetEventListener._
  import ImplicitConversions._

  @EventHandler
  def onEntityTarget(event: EntityTargetEvent): Unit = {
    for {
      phantom <- event.getEntity.asPhantom
      player <- event.getTarget.asPlayer
    } {
      if (player.canCope) {
        event.setCancelled(false)
      }
    }
  }
}

object EntityTargetEventListener {
  implicit class EntityExtends(entity: Entity) {
    // for Phantom
    def isPhantom: Boolean = entity.getType == PHANTOM
    def asPhantom: Option[Phantom] = if (isPhantom) Some(entity.asInstanceOf[Phantom]) else None

    // for Player
    def isPlayer: Boolean = entity.getType == PLAYER
    def asPlayer: Option[Player] = if (isPlayer) Some(entity.asInstanceOf[Player]) else None
  }
}