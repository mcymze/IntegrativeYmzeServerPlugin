package dev.ekuinox.IntegrativeYmzeServerPlugin.services.phantomcoping

import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable
import collection.mutable.{Map => MutableMap}

class Runner(player: Player, effectiveTicks: Long)(implicit service: PhantomCopeService) extends BukkitRunnable {
  import Runner._

  private var spentTicks = 0L

  override def run(): Unit = {
    spentTicks += 1

    // 有効時間を経過
    if (spentTicks > effectiveTicks) stop()
  }

  def getRemainingTicks: Long = effectiveTicks - spentTicks

  def start(): Unit = {
    // 毎Tick呼び出す
    runTaskTimer(service.getPlugin, 0L, 1L)
    runners += (player -> this)
  }

  def stop(): Unit = {
    import Timer._
    player.deactivateCoping()
    runners.remove(player)
    cancel()
  }

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
        runner.stop()
        runner.getRemainingTicks
      case None => 0L
    }
    new Runner(player, spentTicks + effectiveTicks)(service)
  }

  // プレイヤからrunnerを探して停止させる
  def stop(player: Player): Unit = runners.find(_._1 == player).foreach(_._2.stop())

}