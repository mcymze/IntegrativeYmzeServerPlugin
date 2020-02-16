package dev.ekuinox.IntegrativeYmzeServerPlugin.services.phantomcoping

import org.bukkit.persistence.PersistentDataType

import Configure._
import dev.ekuinox.IntegrativeYmzeServerPlugin.utils._
import org.bukkit.entity.Player

object Timer {
  val NAMESPACED_KEY = "timer"
  val DATA_TYPE: PersistentDataType[String, String] = PersistentDataType.STRING

  implicit class PlayerWithTimer(player: Player)(implicit service: PhantomCopeService) {
    private val container = player.getPersistentDataContainer
    private val namespacedKey = service.makeNamespacedKey(NAMESPACED_KEY)

    /**
     * Playerがファントムの追跡を解除できるか
     * @return Boolean
     */
    def canCope: Boolean = container.has(namespacedKey, DATA_TYPE)

    /**
     * 追跡の回避を有効にする
     */
    def activateCoping(ticks: Long): Unit = {
      container.set(namespacedKey, DATA_TYPE, "*")
      Runner.start(player, ticks)
      service.getActivationMessage.foreach(player.sendServiceMessage)
    }

    /**
     * 追跡の回避を無効にする
     */
    def deactivateCoping(): Unit = {
      container.remove(namespacedKey)
      Runner.stop(player)
      service.getDeactivationMessage.foreach(player.sendServiceMessage)
    }
  }

}
