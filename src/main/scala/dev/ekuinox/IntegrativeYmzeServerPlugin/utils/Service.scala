package dev.ekuinox.IntegrativeYmzeServerPlugin.utils

import dev.ekuinox.IntegrativeYmzeServerPlugin.Main
import org.bukkit.NamespacedKey
import org.bukkit.command.CommandSender

abstract class Service(implicit val main: Main) {
  import Service._

  implicit val self: Service = this
  
  val name: String

  def getPlugin: Main = main

  def makeNamespacedKey(key: String): NamespacedKey = new NamespacedKey(main, s"$name.key")

  /**
   * config 管理
   */
  lazy val configurePath: String = makePath(CONFIGURES_PARENT, name)

  def makeConfigurePath(key: String): String = makePath(configurePath, key)

  def reloadConfig(): Unit = main.reloadConfig()

  /**
   * permission 管理
   */
  lazy val permissionPath: String = makePath(PERMISSIONS_PARENT, name)

  def makePermissionPath(key: String): String = makePath(permissionPath, key)

  /**
   * Listener 管理
   */
  val eventListeners: Seq[EventListener]

  def registerListeners(): Unit = eventListeners.foreach(_.register())

  /**
   * command 管理
   */
  val subCommand: String = name

  def onCommand(sender: CommandSender, args: List[String]): Unit = {}

}

object Service {
  lazy val CONFIGURES_PARENT = "services"
  lazy val PERMISSIONS_PARENT: String =  makePath(Main.PERMISSIONS_ROOT, "services")
}
