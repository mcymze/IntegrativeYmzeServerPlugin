package dev.ekuinox.IntegrativeYmzeServerPlugin

import dev.ekuinox.IntegrativeYmzeServerPlugin.services.central.Central
import dev.ekuinox.IntegrativeYmzeServerPlugin.services.phantomcoping.PhantomCopeService
import org.bukkit.command.{Command, CommandSender}
import org.bukkit.plugin.java.JavaPlugin

class Main extends JavaPlugin {
  implicit val self: Main = this
  lazy val central = new Central
  lazy val services = Seq(
    new PhantomCopeService,
    central
  )

  override def onEnable(): Unit = {
    saveDefaultConfig()
    services.foreach(_.registerListeners())
  }

  override def onCommand(sender: CommandSender, command: Command, label: String, args: Array[String]): Boolean = {
    (for {
      subCommand <- args.headOption
      service <- services.find(_.subCommand == subCommand)
    } yield (subCommand, service)) match {
      case Some((_, service)) => service.onCommand(sender, args.toList.tail)
      case None => central.onCommand(sender, args.toList)
    }
    true
  }
}

object Main {
  val PERMISSIONS_ROOT = "ymze"
}
