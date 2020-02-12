package dev.ekuinox.IntegrativeYmzeServerPlugin.services.phantomcoping

import org.bukkit.persistence.PersistentDataType
import collection.mutable.{Map => MutableMap}

object Timer {
  private type BukkitPlayer = org.bukkit.entity.Player

  val NAMESPACED_KEY = "timer"
  val DATA_TYPE: PersistentDataType[String, String] = PersistentDataType.STRING

  // 機能の有効Tick数
  val ACTIVE_TICKS = 1000

  val timers: collection.mutable.Map[BukkitPlayer, Runner] = MutableMap[BukkitPlayer, Runner]()

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
      val runner = new Runner(player)
      timers += (player -> runner)
      runner.runTaskLaterAsynchronously(service.getPlugin, ACTIVE_TICKS)
    }

    /**
     * 追跡の回避を無効にする
     */
    def deactivateCoping(): Unit = {
      container.remove(namespacedKey)
      timers.get(player).foreach(_.cancel())
    }
  }

}
