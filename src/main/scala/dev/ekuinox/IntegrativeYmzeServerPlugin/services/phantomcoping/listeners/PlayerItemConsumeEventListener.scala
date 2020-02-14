package dev.ekuinox.IntegrativeYmzeServerPlugin.services.phantomcoping.listeners

import dev.ekuinox.IntegrativeYmzeServerPlugin.services.phantomcoping.{Configure, PhantomCopeService}
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
      item <- event.getItem.toTargetItem
    } {
      if (player.isSneaking) {
        player.deactivateCoping()
      } else {
        player.activateCoping(item.ticks)
      }
    }
  }
}

object PlayerItemConsumeEventListener {
  import Configure._
  implicit class ItemStackExtends(itemStack: ItemStack)(implicit service: PhantomCopeService) {
    def toTargetItem: Option[TargetItem] = service.getTargetItems.find(_.material == itemStack.getType)
  }
}
