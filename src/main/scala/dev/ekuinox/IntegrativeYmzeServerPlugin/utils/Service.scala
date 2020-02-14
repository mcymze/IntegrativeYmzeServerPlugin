package dev.ekuinox.IntegrativeYmzeServerPlugin.utils

import dev.ekuinox.IntegrativeYmzeServerPlugin.Main
import org.bukkit.NamespacedKey

abstract class Service(implicit val main: Main) {
  def getPlugin: Main = main

  def makeNamespacedKey(key: String): NamespacedKey = new NamespacedKey(main, s"$name.key")

  val name: String

  val configurePath = s"services.$name"

}
