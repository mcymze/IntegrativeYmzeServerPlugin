package dev.ekuinox.IntegrativeYmzeServerPlugin.services.dragonhead

import org.bukkit.entity.Player
import org.bukkit.persistence.PersistentDataType
import org.bukkit.scheduler.BukkitRunnable

object InteractTimer {
  private val KEY = "interact-timer"
  private val TYPE = PersistentDataType.STRING
  private val DELAY = 1
  implicit class PlayerWithInteractTimer(player: Player)(implicit service: DragonHeadService) {
    private val namespacedKey = service.makeNamespacedKey(KEY)
    private val container = player.getPersistentDataContainer
    // タイマが停止しているか
    def isInteractTimerStop: Boolean = !container.has(namespacedKey, TYPE)
    // タイマを開始させる
    def startInteractTimer(): Unit = {
      new BukkitRunnable {
        override def run(): Unit = container.remove(namespacedKey)
      }.runTaskLaterAsynchronously(service.getPlugin, DELAY)
    }
    def withInteractTimerStop: Option[Player] = if (isInteractTimerStop) Some(player) else None
  }
}
