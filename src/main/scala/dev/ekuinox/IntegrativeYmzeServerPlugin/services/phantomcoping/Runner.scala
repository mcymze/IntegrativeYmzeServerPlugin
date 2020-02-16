package dev.ekuinox.IntegrativeYmzeServerPlugin.services.phantomcoping

import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable

class Runner(player: Player, effectiveTicks: Long)(implicit service: PhantomCopeService) extends BukkitRunnable {
  private var spentTicks = 0L

  // 毎Tick呼び出す
  runTaskTimer(service.getPlugin, 0L, 1L)

  override def run(): Unit = {
    import Timer._
    spentTicks += 1

    // 有効時間を経過
    if (spentTicks > effectiveTicks) {
      player.deactivateCoping()
      Timer.timers.remove(player)
      cancel()
    }
  }

  def getSpentTicks: Long = spentTicks
}

object Runner {

  def apply(player: Player, effectiveTicks: Long)(implicit service: PhantomCopeService): Runner = new Runner(player, effectiveTicks)(service)

}