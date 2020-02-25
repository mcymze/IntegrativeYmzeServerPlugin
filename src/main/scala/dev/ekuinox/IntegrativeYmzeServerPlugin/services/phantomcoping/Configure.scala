package dev.ekuinox.IntegrativeYmzeServerPlugin.services.phantomcoping

import org.bukkit.Material

import scala.collection.mutable
import scala.language.implicitConversions
import scala.jdk.CollectionConverters._
import scala.util.Try

object Configure {

  case class TargetItem(material: Material, ticks: Long)
  object TargetItem {
    def fromMap(map: mutable.Map[String, Any]): Option[TargetItem] = for {
      name <- map.get("name")
      name <- Try(name.asInstanceOf[String]).toOption
      material <- Try(Material.valueOf(name)).toOption
      ticks <- map.get("ticks")
      ticks <- Try(ticks.asInstanceOf[Int]).toOption
    } yield TargetItem(material, ticks.toLong)
  }

  /**
   * 対象のTargetItemのリスト
   */
  def getTargetItems(implicit service: PhantomCopeService): List[TargetItem] =
    service.getConf("items")((conf, path) => conf.getMapList(path).asScala.map(_.asScala).flatMap(map => for {
      map <- Try(map.asInstanceOf[mutable.Map[String, Any]]).toOption
      item <- TargetItem.fromMap(map)
    } yield item).toList)

  /**
   * 機能有効時に表示されるメッセージ
   */
  def getActivationMessage(implicit service: PhantomCopeService): Option[String] =
    service.getConf("messages.activation")((conf, path) => Option(conf.getString(path)))

  /**
   * 機能無効時に表示されるメッセージ
   */
  def getDeactivationMessage(implicit service: PhantomCopeService): Option[String] =
    service.getConf("messages.deactivation")((conf, path) => Option(conf.getString(path)))

  /**
   * itemの効果を無視するか
   */
  def isIgnoredItemEffect(implicit service: PhantomCopeService): Boolean =
    service.getConf("ignore-item-effect")((conf, path) => conf.getBoolean(path, true))

  /**
   * ファントムを屋内で沸かせない（ガラスの下だと湧いてしまうので）
   */
  def isCancelingSpawningPhantomIndoor(implicit service: PhantomCopeService): Boolean =
    service.getConf("cancel-indoor")((conf, path) => conf.getBoolean(path, true))

  /**
   * 屋内かの検索をかける最大高度
   */
  def getMaxAltitude(implicit service: PhantomCopeService): Int =
    service.getConf("max-altitude")((conf, path) => conf.getInt(path, 256))

}
