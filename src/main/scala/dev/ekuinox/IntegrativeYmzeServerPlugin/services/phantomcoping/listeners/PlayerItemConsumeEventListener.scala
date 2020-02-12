package dev.ekuinox.IntegrativeYmzeServerPlugin.services.phantomcoping.listeners

import dev.ekuinox.IntegrativeYmzeServerPlugin.services.phantomcoping.{PhantomCopeService, ImplicitConversions}
import dev.ekuinox.IntegrativeYmzeServerPlugin.utils.EventListener
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerItemConsumeEvent
import org.bukkit.inventory.ItemStack

class PlayerItemConsumeEventListener(implicit service: PhantomCopeService) extends EventListener {
  import PlayerItemConsumeEventListener._

  @EventHandler
  def onPlayerItemConsumeEvent(event: PlayerItemConsumeEvent): Unit = {
    import ImplicitConversions._
    for {
      player <- event.getPlayer.withCoping
      item <- event.getItem.withTarget
    } {

    }
  }
}

object PlayerItemConsumeEventListener {
  val TARGET_ITEM: Material = Material.ROTTEN_FLESH
  class Item(item: ItemStack) {
    def withTarget: Option[ItemStack] = if (item.getType == TARGET_ITEM) Some(item) else None
  }
  implicit def toItem(item: ItemStack): Item = new Item(item)
}
