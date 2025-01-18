package net.dxzzz.battlenotice;

import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class InformationUtils {
    public static void AddPvpExtraBuff(TextComponent rawMsg, Player p1, Player aim){
        int protectionLevel = getPlayerProtectionLevel(p1)  ;
        int regenerationLevel=getPlayerRegenerationLevel(p1);
        double absorbtionAmount = getPlayerAbsorbtionAmount(p1);

        String extraRawMsg="";
        String proctionInfo="";
        String regenerationInfo="";
        String absorbtionInfo="";
        if(regenerationLevel>0){
            extraRawMsg=extraRawMsg+ "§a§l✚M";
            regenerationInfo= "§a§l✚(生命恢复"+String.valueOf(regenerationLevel)+"§a§l)\n";
        }
        if(protectionLevel>0){
            extraRawMsg=extraRawMsg+ "§b§l⬛M";
            proctionInfo="§b§l⬛(所有防具总保护等级"+String.valueOf(protectionLevel)+"§b§l)\n";
        }
        TextComponent extraMsg= new TextComponent(extraRawMsg);
        rawMsg.addExtra(extraMsg);
        rawMsg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new ComponentBuilder("§e§l➤➤对方属性概览\n\n").append(regenerationInfo).append(proctionInfo).create()));
        aim.spigot().sendMessage(rawMsg);
    }

    public static int getPlayerProtectionLevel(Player player) {
        int totalProtectionLevel = 0;
        for (ItemStack item : player.getInventory().getArmorContents()) {
            if (item != null && item.getType() != Material.AIR) {
                if (item.hasItemMeta() && item.getItemMeta().hasEnchant(Enchantment.PROTECTION_ENVIRONMENTAL)) {
                    totalProtectionLevel += item.getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL);
                }
            }
        }
        return totalProtectionLevel;
    }

    public static int getPlayerRegenerationLevel(Player player) {
        int totalHealthAndRegenLevel = 0;
        for (PotionEffect effect : player.getActivePotionEffects()) {
            if (effect.getType().equals(PotionEffectType.REGENERATION)) {
                totalHealthAndRegenLevel += effect.getAmplifier() + 1; // 放大器从0开始，因此需要+1
            }
        }
        return totalHealthAndRegenLevel;
    }
    public static int getPlayerAbsorbtionAmount(Player player) {
        int totalAbsorbtionAmount = 0;
        for (PotionEffect effect : player.getActivePotionEffects()) {
            if (effect.getType().equals(PotionEffectType.ABSORPTION)) {
                totalAbsorbtionAmount += effect.getAmplifier() + 1; // 放大器从0开始，因此需要+1
            }
        }
        return totalAbsorbtionAmount;
    }
}
