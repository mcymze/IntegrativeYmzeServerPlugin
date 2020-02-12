package dev.ekuinox.IntegrativeYmzeServerPlugin.services.phantomcoping.listeners

import dev.ekuinox.IntegrativeYmzeServerPlugin.utils.EventListener
import dev.ekuinox.IntegrativeYmzeServerPlugin.{Main => Plugin}
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerItemConsumeEvent

class PlayerItemConsumeEventListener(plugin: Plugin) extends EventListener(plugin) {

  @EventHandler
  def onPlayerItemConsumeEvent(event: PlayerItemConsumeEvent): Unit = {
    val player = event.getPlayer
    val item = event.getItem
    player.sendMessage(s"${player.getName}が${item.getType.toString}を消費しとる")


  }
}
