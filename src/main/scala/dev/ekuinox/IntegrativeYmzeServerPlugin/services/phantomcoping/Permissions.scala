package dev.ekuinox.IntegrativeYmzeServerPlugin.services.phantomcoping

import org.bukkit.entity.Player

object Permissions {

  private val COPING = "ymze.phantomcoping"

  implicit class PlayerWithPermission(player: Player) {

    def hasCopingPermission: Boolean = player.hasPermission(COPING)
    def withCoping: Option[Player] = if (hasCopingPermission) Some(player) else None

  }
}
