package dev.ekuinox.IntegrativeYmzeServerPlugin.services.phantomcoping

import dev.ekuinox.IntegrativeYmzeServerPlugin.Main
import dev.ekuinox.IntegrativeYmzeServerPlugin.utils.{EventListener, Service}

/**
 * プラグイン自体の管理だとかそういうのをやる
 */
class Central(implicit main: Main) extends Service {
  import Central._
  override val name: String = NAME
  override val eventListeners: Seq[EventListener] = Seq.empty
}

object Central {
  val NAME = "central"
}
