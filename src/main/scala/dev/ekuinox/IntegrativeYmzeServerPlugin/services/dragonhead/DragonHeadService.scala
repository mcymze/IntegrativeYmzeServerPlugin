package dev.ekuinox.IntegrativeYmzeServerPlugin.services.dragonhead

import dev.ekuinox.IntegrativeYmzeServerPlugin.Main
import dev.ekuinox.IntegrativeYmzeServerPlugin.services.dragonhead.listeners.{EntityDamageEventListener, PlayerInteractEventListener}
import dev.ekuinox.IntegrativeYmzeServerPlugin.utils.{EventListener, Service}

class DragonHeadService(implicit main: Main) extends Service {
  override val name: String = "dragon-head"
  /**
   * Listener 管理
   */
  override val eventListeners: Seq[EventListener] = Seq(
    new PlayerInteractEventListener()(this),
    new EntityDamageEventListener()(this)
  )
}
