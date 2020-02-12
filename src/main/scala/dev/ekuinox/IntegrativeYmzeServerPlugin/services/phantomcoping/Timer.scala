package dev.ekuinox.IntegrativeYmzeServerPlugin.services.phantomcoping

import org.bukkit.persistence.PersistentDataType
import org.bukkit.scheduler.BukkitRunnable
import collection.mutable.{Map => MutableMap}

object Timer {
  private type BukkitPlayer = org.bukkit.entity.Player

  val NAMESPACED_KEY = "timer"
  val DATA_TYPE: PersistentDataType[String, String] = PersistentDataType.STRING

  val timers: collection.mutable.Map[BukkitPlayer, BukkitRunnable] = MutableMap[BukkitPlayer, BukkitRunnable]()

  class Player(player: BukkitPlayer)(implicit service: PhantomCopeService) {
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
    def activateCoping(): Unit = {
      container.set(namespacedKey, DATA_TYPE, "*")
      timers.get(player).foreach(_.cancel())
    }

    /**
     * 追跡の回避を無効にする
     */
    def deactivateCoping(): Unit = {
      container.remove(namespacedKey)
    }
  }

}
