package dev.ekuinox.IntegrativeYmzeServerPlugin.services.phantomcoping

import org.bukkit.persistence.PersistentDataType
import collection.mutable.{Map => MutableMap}
import Configure._
import org.bukkit.entity.Player

object Timer {
  val NAMESPACED_KEY = "timer"
  val DATA_TYPE: PersistentDataType[String, String] = PersistentDataType.STRING
  val timers: collection.mutable.Map[Player, Runner] = MutableMap[Player, Runner]()

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
    def activateCoping(tick: Long): Unit = {
      container.set(namespacedKey, DATA_TYPE, "*")
      timers.get(player).foreach(_.cancel())
      val runner = new Runner(player)
      timers += (player -> runner)
      runner.runTaskLaterAsynchronously(service.getPlugin, tick)
      service.getActivationMessage.foreach(player.sendMessage)
    }

    /**
     * 追跡の回避を無効にする
     */
    def deactivateCoping(): Unit = {
      container.remove(namespacedKey)
      timers.get(player).foreach(_.cancel())
      service.getDeactivationMessage.foreach(player.sendMessage)
    }
  }

}
