package heyblock0712.hnitemframe.listeners;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import java.util.Random;

public class HideFrame implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEntityEvent event) {
        Entity entity = event.getRightClicked();
        Player player = event.getPlayer();

        // 檢查 蹲下 + 實體:物品展示框
        if((entity.getType() != EntityType.ITEM_FRAME) || !(player.isSneaking())) return;
        ItemFrame itemFrame = (ItemFrame) entity;

        // 檢查 展示框:隱形
        if (!(itemFrame.isVisible())) return;

        // 檢查玩家手中 物品:藥水
        ItemStack item = player.getInventory().getItemInMainHand();
        if (item.getType() != Material.POTION) return;

        // 檢查 藥水:隱形
        PotionMeta potionMeta = (PotionMeta) item.getItemMeta();
        if (potionMeta == null || potionMeta.getBasePotionType() != PotionType.LONG_INVISIBILITY) return;


        // 設置 隱形展示框
        itemFrame.setVisible(false);

        // 移除 手上物品
        player.getInventory().setItemInMainHand(null);

        // 回饋音效
        double distance = player.getLocation().distance(itemFrame.getLocation());
        float volume = Math.max(1.0f - (float)distance / 10.0f, 0.0f);
        float pitch = new Random().nextFloat() * 2.0f;
        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_ADD_ITEM, volume, pitch);

        // 取消事件
        event.setCancelled(true);
    }
}
