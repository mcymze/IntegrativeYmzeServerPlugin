package dev.ekuinox.IntegrativeYmzeServerPlugin.services.phantomcoping

import org.bukkit.Material

import scala.collection.mutable
import scala.language.implicitConversions
import scala.jdk.CollectionConverters._
import scala.util.Try

object Configure {

  case class TargetItem(material: Material, ticks: Long)

  implicit class ServiceWithConfigure(service: PhantomCopeService) {
    private val configure = service.getPlugin.getConfig

    private def makeKey(key: String): String = service.makeConfigurePath(key)

    /**
     * 対象のTargetItemのリスト
     */
    def getTargetItems: List[TargetItem] = {
      configure.getMapList(makeKey("items")).asScala.map(_.asScala).flatMap(map => {
        for {
          map <- Try(map.asInstanceOf[mutable.Map[String, Any]]).toOption
          name <- map.get("name")
          name <- Try(name.asInstanceOf[String]).toOption
          material <- Try(Material.valueOf(name)).toOption
          ticks <- map.get("ticks")
          ticks <- Try(ticks.asInstanceOf[Long]).toOption
        } yield TargetItem(material, ticks)
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
