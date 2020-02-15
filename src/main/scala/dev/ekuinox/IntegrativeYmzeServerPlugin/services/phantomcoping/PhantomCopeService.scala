package dev.ekuinox.IntegrativeYmzeServerPlugin.services.phantomcoping

import dev.ekuinox.IntegrativeYmzeServerPlugin.services.phantomcoping.listeners.{EntityTargetEventListener, PlayerItemConsumeEventListener}
import dev.ekuinox.IntegrativeYmzeServerPlugin.utils.{EventListener, Service}
import dev.ekuinox.IntegrativeYmzeServerPlugin.{Main => Plugin}

/**
 * ファントムの寄ってくるのが鬱陶しいので、寄ってこないようにする
 * @param plugin Plugin
 */
class PhantomCopeService(implicit plugin: Plugin) extends Service {
  implicit override val self: PhantomCopeService = this

  override val name = "phantomCopeService"

  // このクラスで利用するListener
  override val eventListeners = Seq(
    new PlayerItemConsumeEventListener,
    new EntityTargetEventListener
  )

}
