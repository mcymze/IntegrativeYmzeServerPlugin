package dev.ekuinox.IntegrativeYmzeServerPlugin.services.dragonhead

object Configure {

  /**
   * 着弾時に破壊する範囲
   */
  def getExplosiveRadius()(implicit service: DragonHeadService): Float =
    service.getPlugin.getConfig.getDouble(service.makeConfigurePath("explosive-radius"), 0.0d).toFloat

  /**
   * 着弾時に着火するか
   */
  def isTriggerFire()(implicit service: DragonHeadService): Boolean = {
    service.getPlugin.getConfig.getBoolean(service.makeConfigurePath("is-trigger-fire"), false)
  }

}
