package dev.ekuinox.IntegrativeYmzeServerPlugin.services.phantomcoping.listeners

import dev.ekuinox.IntegrativeYmzeServerPlugin.services.phantomcoping.PhantomCopeService
import dev.ekuinox.IntegrativeYmzeServerPlugin.utils.{EventListener, Service}
import dev.ekuinox.IntegrativeYmzeServerPlugin.{Main => Plugin}
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerItemConsumeEvent
import org.bukkit.inventory.ItemStack

class PlayerItemConsumeEventListener(implicit service: PhantomCopeService) extends EventListener {
  import PlayerItemConsumeEventListener._
  import dev.ekuinox.IntegrativeYmzeServerPlugin.services.phantomcoping.Permissions._

  @EventHandler
  def onPlayerItemConsumeEvent(event: PlayerItemConsumeEvent): Unit = {
    for {
      player <- event.getPlayer.withCoping
      item <- event.getItem.withTarget
    } {

    }
  }
}

object PlayerItemConsumeEventListener {
  val TARGET_ITEM: Material = Material.ROTTEN_FLESH
  implicit class Item(item: ItemStack) {
    def withTarget: Option[ItemStack] = if (item.getType == TARGET_ITEM) Some(item) else None
  }
}
