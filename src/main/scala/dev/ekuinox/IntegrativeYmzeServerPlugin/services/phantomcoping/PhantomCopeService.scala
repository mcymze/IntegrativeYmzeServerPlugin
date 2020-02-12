package dev.ekuinox.IntegrativeYmzeServerPlugin.services.phantomcoping

import dev.ekuinox.IntegrativeYmzeServerPlugin.services.phantomcoping.listeners.PlayerItemConsumeEventListener
import dev.ekuinox.IntegrativeYmzeServerPlugin.{Main => Plugin}

/**
 * ファントムの寄ってくるのが鬱陶しいので、寄ってこないようにする
 * @param plugin Plugin
 */
class PhantomCopeService(plugin: Plugin) {
  lazy val playerItemConsumeEventListener = new PlayerItemConsumeEventListener(plugin)

  /**
   * このクラスで扱うListenerをまとめて登録する
   */
  def registerListeners(): Unit = {
    playerItemConsumeEventListener.register()
  }

}
