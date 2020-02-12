package dev.ekuinox.IntegrativeYmzeServerPlugin.services.phantomcoping

import java.lang

import org.bukkit.persistence.PersistentDataType

object Timer {
  private type BukkitPlayer = org.bukkit.entity.Player

  val NAMESPACED_KEY = "timer"
  val PERSISTENT_DATA_CONTAINER_TYPE: PersistentDataType[lang.Byte, lang.Byte] = PersistentDataType.BYTE

  implicit class Player(player: BukkitPlayer)(implicit service: PhantomCopeService) {
    private val container = player.getPersistentDataContainer
    private val namespacedKey = service.makeNamespacedKey(NAMESPACED_KEY)

    /**
     * Playerがファントムの追跡を解除できるか
     * @return Boolean
     */
    def canCope: Boolean = container.has(namespacedKey, PERSISTENT_DATA_CONTAINER_TYPE)


  }
}
