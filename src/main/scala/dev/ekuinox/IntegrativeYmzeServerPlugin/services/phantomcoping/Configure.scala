package dev.ekuinox.IntegrativeYmzeServerPlugin.services.phantomcoping

import scala.language.implicitConversions

object Configure {

  implicit class ServiceWithConfigure(service: PhantomCopeService) {
    private val configure = service.getPlugin.getConfig

    /**
     * 回避を開始して効果が無効になるまでの時間(tick)
     * @return Long
     */
    def getActiveCopingTicks: Long = configure.getLong(service.makeConfigurePath("tick"), 1000)
  }
}
