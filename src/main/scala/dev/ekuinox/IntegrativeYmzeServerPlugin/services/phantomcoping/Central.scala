package dev.ekuinox.IntegrativeYmzeServerPlugin.services.phantomcoping

import dev.ekuinox.IntegrativeYmzeServerPlugin.Main
import dev.ekuinox.IntegrativeYmzeServerPlugin.utils.{EventListener, Service}

/**
 * プラグイン自体の管理だとかそういうのをやる
 */
class Central(implicit main: Main) extends Service {
  override val name: String = "central"
  override val eventListeners: Seq[EventListener] = Seq.empty

}
