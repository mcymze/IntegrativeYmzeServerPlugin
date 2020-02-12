package dev.ekuinox.IntegrativeYmzeServerPlugin.utils

import dev.ekuinox.IntegrativeYmzeServerPlugin.Main

class Service(implicit val main: Main) {
  def getPlugin: Main = main
}
