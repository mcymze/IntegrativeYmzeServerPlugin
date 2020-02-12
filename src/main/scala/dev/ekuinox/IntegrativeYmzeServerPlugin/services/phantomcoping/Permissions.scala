package dev.ekuinox.IntegrativeYmzeServerPlugin.services.phantomcoping

object Permissions {

  private val COPING = "ymze.phantomcoping"

  implicit class Player(player: org.bukkit.entity.Player) {

    def hasCopingPermission: Boolean = player.hasPermission(COPING)
    def withCoping: Option[org.bukkit.entity.Player] = if (hasCopingPermission) Some(player) else None

  }

}
