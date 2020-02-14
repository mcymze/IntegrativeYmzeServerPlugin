package dev.ekuinox.IntegrativeYmzeServerPlugin.utils

import dev.ekuinox.IntegrativeYmzeServerPlugin.Main
import org.bukkit.NamespacedKey

abstract class Service(implicit val main: Main) {
  val name: String

  def getPlugin: Main = main

  def makeNamespacedKey(key: String): NamespacedKey = new NamespacedKey(main, s"$name.key")

  /**
   * config 管理
   */
  val configurePath = s"services.$name"

  def makeConfigurePath(key: String): String = s"$configurePath.$key"

  def reloadConfig(): Unit = main.reloadConfig()

  /**
   * Listener 管理
   */
  val eventListeners: Seq[EventListener]

  def registerListeners(): Unit = eventListeners.foreach(_.register())

}
