package dev.ekuinox.IntegrativeYmzeServerPlugin.services.phantomcoping.listeners

import dev.ekuinox.IntegrativeYmzeServerPlugin.services.phantomcoping.{ImplicitConversions, PhantomCopeService}
import dev.ekuinox.IntegrativeYmzeServerPlugin.utils.EventListener
import org.bukkit.entity.{Entity, Phantom, Player}
import org.bukkit.entity.EntityType._
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.EntityTargetEvent

import scala.util.Try

class EntityTargetEventListener(implicit service: PhantomCopeService) extends EventListener {
  import EntityTargetEventListener._
  import ImplicitConversions._

  @EventHandler
  def onEntityTarget(event: EntityTargetEvent): Unit = {
    for {
      phantom <- event.getEntity.asPhantom
      target <- Option(event.getTarget)
      player <- target.asPlayer
    } {
      event.setCancelled(player.canCope)
    }
  }
}

object EntityTargetEventListener {
  implicit class EntityExtends(entity: Entity) {
    // for Phantom
    def isPhantom: Boolean = entity.getType == PHANTOM
    def asPhantom: Option[Phantom] = if (isPhantom) Try(entity.asInstanceOf[Phantom]).toOption else None

    // for Player
    def isPlayer: Boolean = entity.getType == PLAYER
    def asPlayer: Option[Player] = if (isPlayer) Try(entity.asInstanceOf[Player]).toOption else None
  }
}