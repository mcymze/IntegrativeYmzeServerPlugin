package dev.ekuinox.IntegrativeYmzeServerPlugin.services.phantomcoping

import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable
import collection.mutable.{Map => MutableMap}

class Runner(player: Player, effectiveTicks: Long)(implicit service: PhantomCopeService) extends BukkitRunnable {
  import Runner._

  private var spentTicks = 0L

  override def run(): Unit = {
    import Timer._
    spentTicks += 1

    // 有効時間を経過
    if (spentTicks > effectiveTicks) {
      player.deactivateCoping()
      runners.remove(player)
      cancel()
    }
  }

  def getSpentTicks: Long = spentTicks

  def start(): Unit = {
    // 毎Tick呼び出す
    runTaskTimer(service.getPlugin, 0L, 1L)
    runners += (player -> this)
  }

}

object Runner {
  val runners: collection.mutable.Map[Player, Runner] = MutableMap[Player, Runner]()

  def apply(player: Player, effectiveTicks: Long)(implicit service: PhantomCopeService): Runner = new Runner(player, effectiveTicks)(service)

}