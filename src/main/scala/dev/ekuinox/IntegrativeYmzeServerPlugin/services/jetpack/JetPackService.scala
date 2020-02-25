package dev.ekuinox.IntegrativeYmzeServerPlugin.services.jetpack

import dev.ekuinox.IntegrativeYmzeServerPlugin.Main
import dev.ekuinox.IntegrativeYmzeServerPlugin.utils.{EventListener, Service}

class JetPackService(implicit main: Main) extends Service {
  override val name: String = "jetpack"

  override val eventListeners: Seq[EventListener] = Seq()
  
}
