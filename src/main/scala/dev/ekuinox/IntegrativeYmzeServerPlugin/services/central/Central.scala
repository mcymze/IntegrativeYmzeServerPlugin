package dev.ekuinox.IntegrativeYmzeServerPlugin.services.central

import dev.ekuinox.IntegrativeYmzeServerPlugin.Main
import dev.ekuinox.IntegrativeYmzeServerPlugin.utils.{EventListener, Service}
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

import scala.util.Try

/**
 * プラグイン自体の管理だとかそういうのをやる
 */
class Central(implicit main: Main) extends Service {
  import Central._

  override val name: String = NAME
  override val eventListeners: Seq[EventListener] = Seq.empty

  override def onCommand(sender: CommandSender, args: List[String]): Unit = {
    args.headOption.foreach {
      case "reload" => onReloadCommand(sender, args.tail)
    }
  }

  private def onReloadCommand(sender: CommandSender, args: List[String]): Unit = {
    import dev.ekuinox.IntegrativeYmzeServerPlugin.utils.Permissions._
    import Permissions._
    import dev.ekuinox.IntegrativeYmzeServerPlugin.utils._

    (Try(sender.asInstanceOf[Player]).toOption match {
      case Some(player) => player.withPermission(Reload).map(_.asInstanceOf[CommandSender])
      case None => Some(sender)
    }).foreach(sender => {
      reloadConfig()
      sender.sendServiceMessage("Reloaded configurations")
    })
  }
}

object Central {
  val NAME = "central"
}
