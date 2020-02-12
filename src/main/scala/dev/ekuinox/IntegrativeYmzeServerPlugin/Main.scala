package dev.ekuinox.IntegrativeYmzeServerPlugin

import dev.ekuinox.IntegrativeYmzeServerPlugin.services.phantomcoping.PhantomCopeService
import org.bukkit.plugin.java.JavaPlugin

class Main extends JavaPlugin {
  implicit val self: Main = this
  lazy val phantomCopeService = new PhantomCopeService

  override def onEnable(): Unit = {
    phantomCopeService.registerListeners()
  }

}

