package dev.ekuinox.IntegrativeYmzeServerPlugin.services.central

import dev.ekuinox.IntegrativeYmzeServerPlugin.Main
import dev.ekuinox.IntegrativeYmzeServerPlugin.utils.{EventListener, Service}
import org.bukkit.Material
import org.bukkit.command.CommandSender
import org.bukkit.inventory.meta.Damageable
import org.bukkit.entity.Player
import org.bukkit.inventory.{ItemFlag, ItemStack}
import org.bukkit.inventory.meta.ItemMeta

import scala.util.Try

/**
 * プラグイン自体の管理だとかそういうのをやる
 */
class Central(implicit main: Main) extends Service {
  import Central._

  override val name: String = NAME
  override val eventListeners: Seq[EventListener] = Seq.empty

  override def onCommand(sender: CommandSender, args: List[String]): Unit = {
    args.headOption.foreach {
      case "reload" => onReloadCommand(sender, args.tail)
      case _ => onDefaultCommand(sender, args)
    }
  }

  private def onReloadCommand(sender: CommandSender, args: List[String]): Unit = {
    import dev.ekuinox.IntegrativeYmzeServerPlugin.utils.Permissions._
    import Permissions._
    import dev.ekuinox.IntegrativeYmzeServerPlugin.utils._

    (Try(sender.asInstanceOf[Player]).toOption match {
      case Some(player) => player.withPermission(Reload).map(_.asInstanceOf[CommandSender])
      case None => Some(sender)
    }).foreach(sender => {
      reloadConfig()
      sender.sendServiceMessage("Reloaded configurations")
    })
  }

  private def onDefaultCommand(sender: CommandSender, args: List[String]): Unit = {

    val mat = Material.valueOf(args.headOption.getOrElse("WOODEN_HOE"))
    val damage = args.lastOption.getOrElse("1").toIntOption.getOrElse(1)

    Try(sender.asInstanceOf[Player]).toOption.foreach(player => {

      val item = new ItemStack(Material.WOODEN_HOE, 1)
      val currentDamage = Try(item.getItemMeta.asInstanceOf[Damageable]).toOption.map(damageable => {
        damageable.setDamage(damage)
        item.setItemMeta(damageable.asInstanceOf[ItemMeta])
        damageable.getDamage
      })

      item.setItemMeta({
        val meta = item.getItemMeta
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE)
        meta.setUnbreakable(true)
        meta.setDisplayName("Central Powder")
        meta
      })

      player.getInventory.setItemInMainHand(item)
      player.sendMessage(s"アイテムをあげるわ...あなたに $currentDamage, ${item.getDurability}")

    })

  }
}

object Central {
  val NAME = "central"
}
