package dev.ekuinox.IntegrativeYmzeServerPlugin

import org.bukkit.command.CommandSender

package object utils {

  /**
   * 配列から.でチェーンしたパスを生成する
   */
  def makePath(arr: Array[String]): String = arr.mkString(".")

  def makePath(parent: String, child: String): String = makePath(Array(parent, child))

  implicit class CommandSenderExtendedWithService(sender: CommandSender)(implicit service: Service) {
    def sendServiceMessage(message: String): Unit = sender.sendMessage(service.makeMessage(message))
  }

}
