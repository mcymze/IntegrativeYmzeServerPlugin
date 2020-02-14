package dev.ekuinox.IntegrativeYmzeServerPlugin.services.phantomcoping

import org.bukkit.Material

import scala.collection.mutable
import scala.language.implicitConversions
import scala.jdk.CollectionConverters._
import scala.util.Try

object Configure {

  case class TargetItem(material: Material, tick: Long)

  implicit class ServiceWithConfigure(service: PhantomCopeService) {
    private val configure = service.getPlugin.getConfig

    private def makeKey(key: String): String = service.makeConfigurePath(key)

    /**
     * 回避を開始して効果が無効になるまでの時間(tick)
     * @return Long
     */
    def getActiveCopingTicks: Long = configure.getLong(makeKey("tick"), 1000)

    /**
     * 対象にするMaterialのリスト
     */
    def getTargetItems: List[TargetItem] = {
      configure.getMapList(makeKey("items")).asScala.map(_.asScala).flatMap(map => {
        for {
          map <- Try(map.asInstanceOf[mutable.Map[String, Any]]).toOption
          name <- map.get("name")
          name <- Try(name.asInstanceOf[String]).toOption
          material <- Try(Material.valueOf(name)).toOption
          tick <- map.get("tick")
          tick <- Try(tick.asInstanceOf[Long]).toOption
        } yield TargetItem(material, tick)
      }).toList
    }

    /**
     * 機能有効時に表示されるメッセージ
     */
    def getActivationMessage: Option[String] = Option(configure.getString(makeKey("messages.activation")))

    /**
     * 機能無効時に表示されるメッセージ
     */
    def getDeactivationMessage: Option[String] = Option(configure.getString(makeKey("messages.deactivation")))
  }
}
