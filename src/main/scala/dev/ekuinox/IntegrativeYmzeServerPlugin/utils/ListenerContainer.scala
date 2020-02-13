package dev.ekuinox.IntegrativeYmzeServerPlugin.utils

/**
 * ServiceでEventListenerを管理しやすくしたい気持ち
 */
trait ListenerContainer {
  val eventListeners: Seq[EventListener]
  def registerListeners(): Unit = eventListeners.foreach(_.register())
}
