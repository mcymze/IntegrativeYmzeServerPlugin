package dev.ekuinox.IntegrativeYmzeServerPlugin.services.dragonhead.listeners

import dev.ekuinox.IntegrativeYmzeServerPlugin.services.dragonhead.DragonHeadService
import dev.ekuinox.IntegrativeYmzeServerPlugin.utils.EventListener
import org.bukkit.event.EventHandler
import org.bukkit.event.player.{PlayerAnimationEvent, PlayerAnimationType}

class PlayerAnimationEventListener(implicit service: DragonHeadService) extends EventListener {

  @EventHandler
  def onPlayerAnimation(event: PlayerAnimationEvent): Unit = {
    // 左クリック
    if (event.getAnimationType != PlayerAnimationType.ARM_SWING) return



  }

}
