package dev.ekuinox.IntegrativeYmzeServerPlugin.services.dragonhead

import org.bukkit.configuration.file.FileConfiguration

object Configure {

  /**
   * 相対パスを受け取る => FileConfiguration, 絶対パス => 任意の型
   */
  private def get[T](path: String)(callback: (FileConfiguration, String) => T)(implicit service: DragonHeadService): T =
    callback(service.getPlugin.getConfig, service.makeConfigurePath(path))

  /**
   * 着弾時に破壊する範囲
   */
  def getExplosiveRadius()(implicit service: DragonHeadService): Float =
    get("explosive-radius")((conf, path) => conf.getDouble(path, 0.0d)).toFloat

  /**
   * 着弾時に着火するか
   */
  def isTriggerFire()(implicit service: DragonHeadService): Boolean =
    get("is-trigger-fire")((conf, path) => conf.getBoolean(path, false))

  /**
   * 何ticks間隔をあけるか
   */
  def getCoolTimeTicks()(implicit service: DragonHeadService): Int =
    get("cool-time")((conf, path) => conf.getInt(path, 20))

  /**
   * Fireballの速度
   */
  def getFireballSpeed()(implicit service: DragonHeadService): Int =
    get("fireball-speed")((conf, path) => conf.getInt(path, 1))
}
