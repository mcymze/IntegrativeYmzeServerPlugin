package dev.ekuinox.IntegrativeYmzeServerPlugin.services.dragonhead

import dev.ekuinox.IntegrativeYmzeServerPlugin.services.dragonhead.Configure.{getExplosiveRadius, getFireballSpeed, isTriggerFire}
import dev.ekuinox.IntegrativeYmzeServerPlugin.services.dragonhead.permissions.Fire
import org.bukkit.entity.{Fireball, Player}

object FireballShooter {

  implicit class FireballShooterExtended(player: Player) {
    import dev.ekuinox.IntegrativeYmzeServerPlugin.services.dragonhead.InteractTimer._

    /**
     * プレイヤからFireballを発射させる
     * 条件にあった場合、タイマを開始させて生成した射出したFireballを返す
     * それ以外の場合はNoneを返す
     */
    def shootFireball[A <: Fireball] (clazz: Class[A])(implicit service: DragonHeadService): Option[A] = {
      // 権限を所持しているか
      if (!player.hasPermission(Fire)) return None

      // タイマが進んでいる
      if (!player.isInteractTimerStop) return None

      // ファイアボールをスポーンさせる
      val fireball = player.getWorld.spawn(player.getEyeLocation, clazz)
      fireball.setVelocity(player.getEyeLocation.getDirection.multiply(getFireballSpeed()))
      fireball.setShooter(player)
      fireball.setIsIncendiary(isTriggerFire()) // 着火するか
      fireball.setYield(getExplosiveRadius()) // 破壊範囲

      // タイマを開始させる
      player.startInteractTimer()

      Some(fireball)
    }
  }

}
