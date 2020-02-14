package dev.ekuinox.IntegrativeYmzeServerPlugin.services.phantomcoping

import org.bukkit.Material

import scala.language.implicitConversions
import scala.jdk.CollectionConverters._
import scala.util.Try

object Configure {

  implicit class ServiceWithConfigure(service: PhantomCopeService) {
    private val configure = service.getPlugin.getConfig

    /**
     * 回避を開始して効果が無効になるまでの時間(tick)
     * @return Long
     */
    def getActiveCopingTicks: Long = configure.getLong(service.makeConfigurePath("tick"), 1000)

    /**
     * 対象にするMaterialのリスト
     */
    def getTargetItems: List[Material] = configure.getStringList("items").asScala.flatMap(name => Try(Material.valueOf(name)).toOption).toList
  }
}
