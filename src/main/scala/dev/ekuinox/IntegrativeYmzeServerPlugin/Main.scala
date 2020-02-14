package dev.ekuinox.IntegrativeYmzeServerPlugin

import dev.ekuinox.IntegrativeYmzeServerPlugin.services.phantomcoping.PhantomCopeService
import dev.ekuinox.IntegrativeYmzeServerPlugin.utils.Service
import org.bukkit.plugin.java.JavaPlugin

class Main extends JavaPlugin {
  implicit val self: Main = this
  lazy val services: Map[String, Service] = Seq(
    new PhantomCopeService,
    new Central
  ).map(service => (service.name, service)).toMap

  override def onEnable(): Unit = {
    services.foreach(_._2.registerListeners())
  }

}

