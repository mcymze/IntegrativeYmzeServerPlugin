package dev.ekuinox.IntegrativeYmzeServerPlugin.services.phantomcoping

import dev.ekuinox.IntegrativeYmzeServerPlugin.services.phantomcoping.listeners.{EntityTargetEventListener, PlayerItemConsumeEventListener}
import dev.ekuinox.IntegrativeYmzeServerPlugin.utils.{EventListener, ListenerContainer, Service}
import dev.ekuinox.IntegrativeYmzeServerPlugin.{Main => Plugin}

/**
 * ファントムの寄ってくるのが鬱陶しいので、寄ってこないようにする
 * @param plugin Plugin
 */
class PhantomCopeService(implicit plugin: Plugin) extends Service with ListenerContainer{
  implicit val self: PhantomCopeService = this

  // このクラスで利用するListener
  override val eventListeners = Seq(
    new PlayerItemConsumeEventListener,
    new EntityTargetEventListener
  )

}
