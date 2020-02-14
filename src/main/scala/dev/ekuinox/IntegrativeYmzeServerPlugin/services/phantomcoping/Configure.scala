package dev.ekuinox.IntegrativeYmzeServerPlugin.services.phantomcoping

import scala.language.implicitConversions

object Configure {

  class ServiceWithConfigure(service: PhantomCopeService) {
    private val configure = service.getPlugin.getConfig

    /**
     * 回避を開始して効果が無効になるまでの時間(tick)
     * @return Long
     */
    def getActiveCopingTicks: Long = configure.getLong(service.makeConfigurePath("tick"), 1000)
  }
  implicit def convertToServiceWithConfigure(service: PhantomCopeService): ServiceWithConfigure = new ServiceWithConfigure(service)

}
