package dev.ekuinox.IntegrativeYmzeServerPlugin.services.phantomcoping.listeners

import dev.ekuinox.IntegrativeYmzeServerPlugin.services.phantomcoping.{Configure, PhantomCopeService}
import dev.ekuinox.IntegrativeYmzeServerPlugin.utils.EventListener
import org.bukkit.GameMode
import org.bukkit.entity.Player
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

    /**
     * クリエイティブモードを除外する
     * スペクテイターモードではアイテムの消費自体がないので無視する
     * `cope`権限を持つユーザのみに作用する
     * itemsに記述されたMaterialのみ対象にする
     */
    for {
      player <- event.getPlayer.withoutCreativeMode
      player <- player.withPermission(Cope)
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
         * インベントリからアイテムを探して減算する
         */
        decreaseAmount(event.getItem, player.getInventory)
        /**
         * イベントをキャンセルすることで毒などの効果を無視する
         * 空腹度にも作用しない
         */
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
  implicit class PlayerWithoutCreativeMode(player: Player) {
    def withoutCreativeMode: Option[Player] = if (player.getGameMode == GameMode.CREATIVE) None else Some(player)
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
