package dev.ekuinox.IntegrativeYmzeServerPlugin.services.phantomcoping

import org.bukkit.Material

import scala.language.implicitConversions
import scala.jdk.CollectionConverters._
import scala.util.Try

object Configure {

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
    def getTargetItems: List[Material] = configure.getStringList(makeKey("items")).asScala.flatMap(name => Try(Material.valueOf(name)).toOption).toList

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
