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
      case "reload" => {
        Try(sender.asInstanceOf[Player]).toOption match {
          case Some(player) if player.hasPermission("ymze.central.reload") => reloadConfig()
          case None => reloadConfig()
        }
      }
    }
  }
}

object Central {
  val NAME = "central"
}
