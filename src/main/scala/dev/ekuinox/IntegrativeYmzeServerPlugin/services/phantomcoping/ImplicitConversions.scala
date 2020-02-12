package dev.ekuinox.IntegrativeYmzeServerPlugin.services.phantomcoping

object ImplicitConversions {
  implicit def toPermissionPlayer(player: org.bukkit.entity.Player): Permissions.Player = new Permissions.Player(player)
  implicit def toTimerPlayer(player: org.bukkit.entity.Player)(implicit service: PhantomCopeService): Timer.Player = new Timer.Player(player)

}
