package dev.ekuinox.IntegrativeYmzeServerPlugin.services.phantomcoping.listeners

import dev.ekuinox.IntegrativeYmzeServerPlugin.services.phantomcoping.{Configure, PhantomCopeService}
import dev.ekuinox.IntegrativeYmzeServerPlugin.utils.EventListener
import org.bukkit.GameMode
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerItemConsumeEvent
import org.bukkit.inventory.{ItemStack, PlayerInventory}

class PlayerItemConsumeEventListener(implicit service: PhantomCopeService) extends EventListener {
  import PlayerItemConsumeEventListener._

  @EventHandler
  def onPlayerItemConsume(event: PlayerItemConsumeEvent): Unit = {
    import dev.ekuinox.IntegrativeYmzeServerPlugin.services.phantomcoping.Permissions._
    import dev.ekuinox.IntegrativeYmzeServerPlugin.utils.Permissions._
    import dev.ekuinox.IntegrativeYmzeServerPlugin.services.phantomcoping.Timer._

    for {
      player <- event.getPlayer.withPermission(Cope)
      item <- event.getItem.toTargetItem
    } {
      if (player.isSneaking) {
        player.deactivateCoping()
      } else {
        player.activateCoping(item.ticks)
      }

      import Configure.ServiceWithConfigure
      if (service.isIgnoreItemEffect) {
        /**
         * クリエイティブモードでなければ減算させる
         * スペクテイターモードの場合でも通ってしまうが、まずアイテムの消費がないので無視する
         */
        if (player.getGameMode != GameMode.CREATIVE) decreaseAmount(event.getItem, player.getInventory)

        event.setCancelled(true)
      }
    }
  }
}

object PlayerItemConsumeEventListener {
  import Configure._
  implicit class ItemStackExtends(itemStack: ItemStack)(implicit service: PhantomCopeService) {
    def toTargetItem: Option[TargetItem] = service.getTargetItems.find(_.material == itemStack.getType)
  }

  /**
   * プレイヤのインベントリに更新をかける
   * 右手(MainHand)にあるアイテムが優先して消費されるはず
   * Materialを比較して先に一致した方で更新する
   */
  def decreaseAmount(itemStack: ItemStack, inventory: PlayerInventory, count: Int = 1): Unit = {
    itemStack.setAmount(itemStack.getAmount - count)
    if (itemStack.getType == inventory.getItemInMainHand.getType) inventory.setItemInMainHand(itemStack)
    else if (itemStack.getType == inventory.getItemInOffHand.getType) inventory.setItemInOffHand(itemStack)
  }

}
