package dev.ekuinox.IntegrativeYmzeServerPlugin.services.phantomcoping

import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable
import collection.mutable.{Map => MutableMap}
import CopingEffect._

class Runner(player: Player, effectiveTicks: Long)(implicit service: PhantomCopeService) extends BukkitRunnable {
  import Runner._

  private var spentTicks = 0L

  override def run(): Unit = {
    spentTicks += 1

    // 有効時間を経過
    if (spentTicks > effectiveTicks) stop(player)
  }

  def getRemainingTicks: Long = effectiveTicks - spentTicks

}

object Runner {
  private val runners: collection.mutable.Map[Player, Runner] = MutableMap[Player, Runner]()

  def apply(player: Player, effectiveTicks: Long)(implicit service: PhantomCopeService): Runner = {
    /**
     * 前に実行されていたものがあるかチェックする
     * あればstopして、経過時間を返す
     */
    val spentTicks = runners.get(player) match {
      case Some(runner) =>
        stop(player)
        runner.getRemainingTicks
      case None => 0L
    }
    new Runner(player, spentTicks + effectiveTicks)(service)
  }
  
  def start(player: Player, effectiveTicks: Long)(implicit service: PhantomCopeService): Unit = {
    val runner = Runner(player, effectiveTicks)
    runner.runTaskTimer(service.getPlugin, 0L, 1L)
    runners += (player -> runner)
  }

  // プレイヤからrunnerを探して停止させる
  def stop(player: Player)(implicit service: PhantomCopeService): Boolean = {
    val runner = runners.get(player)
    if (runner.isEmpty) false
    else {
      runner.foreach(_.cancel())
      runners.remove(player)
      player.deactivateCoping()
      true
    }
  }

}