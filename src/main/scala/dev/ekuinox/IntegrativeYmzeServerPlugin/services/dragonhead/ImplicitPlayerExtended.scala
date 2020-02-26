package dev.ekuinox.IntegrativeYmzeServerPlugin.services.dragonhead

import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.persistence.{PersistentDataContainer, PersistentDataType}

object ImplicitPlayerExtended {

  implicit class PlayerDragonHeadExtended(player: Player)(implicit service: DragonHeadService) {
    lazy val container: PersistentDataContainer = player.getPersistentDataContainer
    object DragonFlight {
      lazy val key: NamespacedKey = service.makeNamespacedKey("dragon-flight")
      lazy val dataType: PersistentDataType[String, String] = PersistentDataType.STRING
    }

    // フラグを有効にする
    def activateDragonFlight(): Unit = container.set(DragonFlight.key, DragonFlight.dataType, "*")

    // フラグを無効にする
    def deactivateDragonFlight(): Unit = container.remove(DragonFlight.key)

    // フラグが有効か
    def isActiveDragonFlight: Boolean = container.has(DragonFlight.key, DragonFlight.dataType)

  }

}
