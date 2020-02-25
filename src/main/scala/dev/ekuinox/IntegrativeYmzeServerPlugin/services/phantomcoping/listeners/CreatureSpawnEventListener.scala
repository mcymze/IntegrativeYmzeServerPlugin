package dev.ekuinox.IntegrativeYmzeServerPlugin.services.phantomcoping.listeners

import dev.ekuinox.IntegrativeYmzeServerPlugin.services.phantomcoping.PhantomCopeService
import dev.ekuinox.IntegrativeYmzeServerPlugin.utils.EventListener
import org.bukkit.block.Block
import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.CreatureSpawnEvent

class CreatureSpawnEventListener(implicit service: PhantomCopeService) extends EventListener {
  import CreatureSpawnEventListener._
  import dev.ekuinox.IntegrativeYmzeServerPlugin.services.phantomcoping.Configure._

  @EventHandler
  def onCreatureSpawn(event: CreatureSpawnEvent): Unit = {
    // 機能が有効でない場合蹴る
    if (!isCancelingSpawningPhantomIndoor) return

    // ファントムじゃない場合蹴る
    if (event.getEntity.getType != EntityType.PHANTOM) return

    // 屋内の場合キャンセルする
    if (event.getLocation.getBlock.isIndoor(getMaxAltitude)) {
      event.setCancelled(true)
    }

  }

}

object CreatureSpawnEventListener {

  implicit class BlockExtended(block: Block) {
    // 屋内のブロックか
    def isIndoor(maxY: Int = 256): Boolean = {
      for (i <- 0 until maxY - block.getY) {
        val targetBlock = block.getRelative(0, i, 0)
        if (!targetBlock.getType.isAir) return true
      }
      false
    }
  }

}