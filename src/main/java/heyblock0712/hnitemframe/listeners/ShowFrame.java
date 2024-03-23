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
import org.bukkit.potion.PotionType;

import java.util.Random;

public class ShowFrame implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEntityEvent event) {
        Entity entity = event.getRightClicked();
        Player player = event.getPlayer();

        // 檢查 蹲下 + 實體:物品展示框
        if ((entity.getType() != EntityType.ITEM_FRAME) || !(player.isSneaking())) return;

        // 檢查玩家手中 物品:水桶
        ItemStack item = player.getInventory().getItemInMainHand();
        if (item.getType() != Material.WATER_BUCKET) return;

        // 設置 顯示展示框
        ItemFrame itemFrame = (ItemFrame) entity;
        itemFrame.setVisible(true);

        // 設置手上 物品:空桶
        player.getInventory().setItemInMainHand(new ItemStack(Material.BUCKET));

        // 回饋音效
        double distance = player.getLocation().distance(itemFrame.getLocation());
        float volume = Math.max(1.0f - (float) distance / 10.0f, 0.0f);
        float pitch = new Random().nextFloat() * 2.0f;
        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_FRAME_REMOVE_ITEM, volume, pitch);

        // 取消事件
        event.setCancelled(true);
    }
}
