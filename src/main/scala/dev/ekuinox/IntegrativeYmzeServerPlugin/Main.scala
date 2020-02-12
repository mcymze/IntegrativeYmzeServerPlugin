package dev.ekuinox.IntegrativeYmzeServerPlugin

import dev.ekuinox.IntegrativeYmzeServerPlugin.services.phantomcoping.PhantomCopeService
import org.bukkit.plugin.java.JavaPlugin

class Main extends JavaPlugin {
  val phantomCopeService = new PhantomCopeService(this)

  override def onEnable(): Unit = {
    phantomCopeService.registerListeners()
  }

}

