package dev.ekuinox.IntegrativeYmzeServerPlugin

import dev.ekuinox.IntegrativeYmzeServerPlugin.services.central.Central
import dev.ekuinox.IntegrativeYmzeServerPlugin.services.phantomcoping.PhantomCopeService
import dev.ekuinox.IntegrativeYmzeServerPlugin.utils.Service
import org.bukkit.command.{Command, CommandSender}
import org.bukkit.plugin.java.JavaPlugin

class Main extends JavaPlugin {
  implicit val self: Main = this
  lazy val services: Map[String, Service] = Seq(
    new PhantomCopeService,
    new Central
  ).map(service => (service.name, service)).toMap

  override def onEnable(): Unit = {
    saveDefaultConfig()
    services.foreach(_._2.registerListeners())
  }

  override def onCommand(sender: CommandSender, command: Command, label: String, args: Array[String]): Boolean = {
    (for {
      subCommand <- args.headOption
      service <- services.get(subCommand)
    } yield (subCommand, service)) match {
      case Some((_, service)) => service.onCommand(sender, args.toList.tail)
      case None => services.get(Central.NAME).foreach(_.onCommand(sender, args.toList))
    }
    true
  }
}

object Main {
  val PERMISSIONS_ROOT = "ymze"
}
