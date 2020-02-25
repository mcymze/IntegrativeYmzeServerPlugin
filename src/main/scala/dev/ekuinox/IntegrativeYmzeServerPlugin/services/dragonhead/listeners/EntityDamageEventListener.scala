package dev.ekuinox.IntegrativeYmzeServerPlugin.services.dragonhead.listeners

import dev.ekuinox.IntegrativeYmzeServerPlugin.services.dragonhead.{Configure, DragonHeadService}
import dev.ekuinox.IntegrativeYmzeServerPlugin.utils.EventListener
import org.bukkit.entity.{EntityType, Player}
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.EntityDamageEvent

import scala.util.Try

class EntityDamageEventListener(implicit service: DragonHeadService) extends EventListener {

  @EventHandler
  def onEntityDamage(event: EntityDamageEvent): Unit = {
    import dev.ekuinox.IntegrativeYmzeServerPlugin.services.dragonhead.ImplicitPlayerExtended._

    // 落下ダメージ以外は無視
    if (event.getCause != EntityDamageEvent.DamageCause.FALL) return

    // プレイヤ以外は無視
    if (event.getEntityType != EntityType.PLAYER) return

    Try(event.getEntity.asInstanceOf[Player]).toOption.foreach { player =>
      // ジェッパによる飛行中ではなかった
      if (!player.isActiveDragonFlight) return

      // 無効に
      player.deactivateDragonFlight()

      event.setCancelled(Configure.isIgnoredJetPackFallingDamage)
    }

  }

}
