package dev.ekuinox.IntegrativeYmzeServerPlugin.services.dragonhead.listeners

import dev.ekuinox.IntegrativeYmzeServerPlugin.services.dragonhead.DragonHeadService
import dev.ekuinox.IntegrativeYmzeServerPlugin.utils.EventListener
import org.bukkit.Material
import org.bukkit.entity.Fireball
import org.bukkit.event.EventHandler
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.{EquipmentSlot, ItemStack}

class PlayerInteractEventListener(implicit service: DragonHeadService) extends EventListener {
  import PlayerInteractEventListener._
  import dev.ekuinox.IntegrativeYmzeServerPlugin.utils.Permissions._
  import dev.ekuinox.IntegrativeYmzeServerPlugin.services.dragonhead.permissions._

  @EventHandler
  def onPlayerInteract(event: PlayerInteractEvent): Unit = {
    import dev.ekuinox.IntegrativeYmzeServerPlugin.services.dragonhead.InteractTimer._
    import dev.ekuinox.IntegrativeYmzeServerPlugin.services.dragonhead.Configure._

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

      val player = event.getPlayer

      // タイマが進んでいる
      if (!player.isInteractTimerStop) return

      val world = player.getWorld

      val fireball = world.spawn(player.getEyeLocation, classOf[Fireball])
      fireball.setVelocity(player.getEyeLocation.getDirection.multiply(getFireballSpeed()))
      fireball.setShooter(player)
      fireball.setIsIncendiary(isTriggerFire())
      fireball.setYield(getExplosiveRadius())

      player.startInteractTimer()
      event.setCancelled(true)
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
