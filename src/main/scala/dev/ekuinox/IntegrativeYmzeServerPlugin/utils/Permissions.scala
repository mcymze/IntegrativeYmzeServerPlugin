package dev.ekuinox.IntegrativeYmzeServerPlugin.utils

import org.bukkit.entity.Player

object Permissions {

  case class Permission(key: String)

  /**
   * String変換
   */
  private implicit def permissionAsString(permission: Permission)(implicit service: Service): String = service.makePermissionPath(permission.key)

  /**
   * Player拡張
   */
  implicit class PlayerWithPermission(player: Player)(implicit service: Service) {
    def hasPermission(permission: Permission): Boolean = player.hasPermission(permission)
    def withPermission(permission: Permission): Option[Player] = if (hasPermission(permission)) Some(player) else None
  }

}