package dev.ekuinox.IntegrativeYmzeServerPlugin

import dev.ekuinox.IntegrativeYmzeServerPlugin.services.phantomcoping.PhantomCopeService
import dev.ekuinox.IntegrativeYmzeServerPlugin.utils.Service
import org.bukkit.plugin.java.JavaPlugin

class Main extends JavaPlugin {
  implicit val self: Main = this
  lazy val services: Seq[Service] = Seq(
    new PhantomCopeService
  )

  override def onEnable(): Unit = {
    services.foreach(_.registerListeners())
  }

}

