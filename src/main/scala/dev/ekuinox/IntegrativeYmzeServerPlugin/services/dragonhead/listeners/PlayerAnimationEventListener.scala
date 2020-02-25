package dev.ekuinox.IntegrativeYmzeServerPlugin.services.dragonhead.listeners

import dev.ekuinox.IntegrativeYmzeServerPlugin.services.dragonhead.DragonHeadService
import dev.ekuinox.IntegrativeYmzeServerPlugin.services.dragonhead.Configure._
import dev.ekuinox.IntegrativeYmzeServerPlugin.services.dragonhead.permissions.JetPack
import dev.ekuinox.IntegrativeYmzeServerPlugin.utils.EventListener
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.player.{PlayerAnimationEvent, PlayerAnimationType}

class PlayerAnimationEventListener(implicit service: DragonHeadService) extends EventListener {

  @EventHandler
  def onPlayerAnimation(event: PlayerAnimationEvent): Unit = {
    // 左クリック
    if (event.getAnimationType != PlayerAnimationType.ARM_SWING) return

    if (!isEnabledJetPack) return

    val player = event.getPlayer

    if (!player.hasPermission(JetPack)) return

    // 左手にドラゴン頭を持っていないと発動しない
    if (player.getInventory.getItemInOffHand.getType != Material.DRAGON_HEAD) return

    player.setVelocity(player.getEyeLocation.getDirection.multiply(getJetPackMultiplyVelocity))

  }

}
