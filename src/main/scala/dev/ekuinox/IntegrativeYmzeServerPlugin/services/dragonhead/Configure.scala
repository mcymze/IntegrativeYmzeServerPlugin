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

  /**
   * JetPackを有効にするか
   */
  def isEnabledJetPack(implicit service: DragonHeadService): Boolean =
    service.getConf("enable-jetpack")((conf, path) => conf.getBoolean(path, true))

  /**
   * getEyeLocation.getDirection.multiplyに与える引数
   */
  def getJetPackMultiplyVelocity(implicit service: DragonHeadService): Double =
    service.getConf("multiply-jetpack")((conf, path) => conf.getDouble(path, 1.0d))

}
