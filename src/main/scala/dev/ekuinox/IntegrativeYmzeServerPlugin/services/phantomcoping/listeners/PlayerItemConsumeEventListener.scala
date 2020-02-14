package dev.ekuinox.IntegrativeYmzeServerPlugin.services.phantomcoping.listeners

import dev.ekuinox.IntegrativeYmzeServerPlugin.services.phantomcoping.PhantomCopeService
import dev.ekuinox.IntegrativeYmzeServerPlugin.utils.EventListener
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerItemConsumeEvent
import org.bukkit.inventory.ItemStack

class PlayerItemConsumeEventListener(implicit service: PhantomCopeService) extends EventListener {
  import PlayerItemConsumeEventListener._

  @EventHandler
  def onPlayerItemConsume(event: PlayerItemConsumeEvent): Unit = {
    import dev.ekuinox.IntegrativeYmzeServerPlugin.services.phantomcoping.Permissions._
    import dev.ekuinox.IntegrativeYmzeServerPlugin.services.phantomcoping.Timer._

    for {
      player <- event.getPlayer.withCoping
      item <- event.getItem.withTarget
    } {
      if (player.isSneaking) {
        player.deactivateCoping()
      } else {
        player.activateCoping()
      }
    }
  }
}

object PlayerItemConsumeEventListener {
  val TARGET_ITEM: Material = Material.ROTTEN_FLESH
  implicit class Item(item: ItemStack) {
    def withTarget: Option[ItemStack] = if (item.getType == TARGET_ITEM) Some(item) else None
  }
  def toItem(item: ItemStack): Item = new Item(item)
}
