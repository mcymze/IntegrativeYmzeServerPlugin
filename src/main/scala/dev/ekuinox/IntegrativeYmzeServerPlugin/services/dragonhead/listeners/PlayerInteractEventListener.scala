package dev.ekuinox.IntegrativeYmzeServerPlugin.services.dragonhead.listeners

import dev.ekuinox.IntegrativeYmzeServerPlugin.services.dragonhead.DragonHeadService
import dev.ekuinox.IntegrativeYmzeServerPlugin.utils.EventListener
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.{EquipmentSlot, ItemStack}

class PlayerInteractEventListener(implicit service: DragonHeadService) extends EventListener {
  import PlayerInteractEventListener._

  @EventHandler
  def onPlayerInteract(event: PlayerInteractEvent): Unit = {
    import dev.ekuinox.IntegrativeYmzeServerPlugin.services.dragonhead.Fireball._

    for {
      // itemはnullableなので
      item <- event.getItemOption
    } {
      // 右クリじゃないと発火しない
      if (!event.isRightClick) return

      // 左手によるイベントじゃないと発火しない
      if (!event.isOffHand) return

      // ドラゴン頭じゃないと発火しない
      if (!item.isDragonHead) return

      // どうあれドラゴン頭の右手使用はキャンセルする
      event.setCancelled(true)

      // ファイアボールを発射する => 発射された場合にFireballが返るが特に利用することがない
      event.getPlayer.shootFireball()
    }
  }

}

object PlayerInteractEventListener {
  implicit class MatchPlayerInteractEvent(event: PlayerInteractEvent) {
    def getItemOption: Option[ItemStack] = Option(event.getItem)
    def isRightClick: Boolean = event.getAction == Action.RIGHT_CLICK_AIR || event.getAction == Action.RIGHT_CLICK_BLOCK
    def isOffHand: Boolean = event.getHand == EquipmentSlot.OFF_HAND
  }

  implicit class ItemStackExtended(itemStack: ItemStack) {
    def isDragonHead: Boolean = itemStack.getType == Material.DRAGON_HEAD
  }
}
