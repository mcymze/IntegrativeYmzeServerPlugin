package dev.ekuinox.IntegrativeYmzeServerPlugin.services.dragonhead

object Configure {

  /**
   * 着弾時に破壊する範囲
   */
  def getExplosiveRadius()(implicit service: DragonHeadService): Float =
    service.getConf("explosive-radius")((conf, path) => conf.getDouble(path, 0.0d)).toFloat

  /**
   * 着弾時に着火するか
   */
  def isTriggerFire()(implicit service: DragonHeadService): Boolean =
    service.getConf("is-trigger-fire")((conf, path) => conf.getBoolean(path, false))

  /**
   * 何ticks間隔をあけるか
   */
  def getCoolTimeTicks()(implicit service: DragonHeadService): Int =
    service.getConf("cool-time")((conf, path) => conf.getInt(path, 20))

  /**
   * Fireballの速度
   */
  def getFireballSpeed()(implicit service: DragonHeadService): Int =
    service.getConf("fireball-speed")((conf, path) => conf.getInt(path, 1))
}
