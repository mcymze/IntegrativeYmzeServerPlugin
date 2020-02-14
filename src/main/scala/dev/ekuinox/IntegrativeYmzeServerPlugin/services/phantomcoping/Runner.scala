package dev.ekuinox.IntegrativeYmzeServerPlugin.services.phantomcoping

import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable

class Runner(player: Player)(implicit service: PhantomCopeService) extends BukkitRunnable {
  override def run(): Unit = {
    import Timer._
    player.deactivateCoping()
  }
}
