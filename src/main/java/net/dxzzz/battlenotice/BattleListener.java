package net.dxzzz.battlenotice;

import com.connorlinfoot.titleapi.TitleAPI;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class BattleListener implements Listener {
    private final JavaPlugin plugin;
    public static BattleListener instance;//
    public BattleListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }
    public static BattleListener getInstance(JavaPlugin plugin) {
        if (instance == null) {
            instance = new BattleListener(plugin);
        }
        return instance;
    }
    @EventHandler(priority = EventPriority.HIGH)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        //().info("监听到玩家受到伤害");
        if(event.isCancelled()){
            //().info("事件被取消");
            return;
        }
        DecimalFormat df = new DecimalFormat("0.0");
        String formatToDeath = "§a对§e%s§a造成§c❤§4§l[%s§4§l]§a伤害，他还剩§c❤§4§l[0.0]§a他没了";
        String formatToHurt = "§a对§e%s§a造成§c❤§4§l[%s§4§l]§a伤害，他还剩§c❤§4§l[%s§4§l]";
        if (event.getDamager() instanceof Player) {
            Player damager = (Player) event.getDamager();
            if (event.getEntity() instanceof Player) {
                Player victim = (Player) event.getEntity();
                double damageValue = event.getFinalDamage();
                PermissionManager permissionManager = new PermissionManager(damager,plugin);
                List<String> noticeMsgPermission = permissionManager.getUsingPermissions("battlenotice.message");
                List<String> noticeTitlePermission = permissionManager.getUsingPermissions("battlenotice.title");
                //().info(String.valueOf( noticeMsgPermission.size()));
                //().info(String.valueOf( noticeTitlePermission.size()));
                //().info("监听到玩家受到伤害");
                if(!noticeMsgPermission.isEmpty()) {
                    //().info("有权限使用battlenotice.message");
                    if (victim.getHealth() - damageValue < 0.01) {
                        TextComponent msgToDeath = new TextComponent(String.format(formatToDeath, victim.getName(), df.format(damageValue)));
                        InformationUtils.AddPvpExtraBuff(msgToDeath, victim, damager);
                    } else {
                        TextComponent msgToHurt = new TextComponent(String.format(formatToHurt, victim.getName(), df.format(damageValue),df.format(victim.getHealth() - damageValue)));
                        InformationUtils.AddPvpExtraBuff(msgToHurt, victim, damager);
                    }
                }
                if(!noticeTitlePermission.isEmpty()){
                    //().info("有权限使用battlenotice.title");
                    if (victim.getHealth() - damageValue < 0.01) {
                        String leftBlood = "&c0.0";
                        TitleAPI.sendTitle(damager, 0, 15, 0, leftBlood, null);
//                        damager.sendTitle(leftBlood, null, 0, 15, 0);
                    } else {
                        Double leftBlood = victim.getHealth() - damageValue;
                        String colorCode = "§a";

                        if(leftBlood<= 14&& leftBlood>8){
                            colorCode = "§e";
                        }
                        else if(leftBlood<= 8){
                            colorCode = "§c";
                        }
                        String leftBloodStr = colorCode + df.format(leftBlood);
                        TitleAPI.sendTitle(damager, 0, 15, 0, leftBloodStr, null);
//                        damager.sendTitle(null, leftBloodStr, 0, 15, 0);
                    }
                }
            }
        }
        else if (event.getDamager() instanceof Arrow) {
            Arrow arrow = (Arrow) event.getDamager();
            if (event.getEntity() instanceof Player) {
                if (arrow.getShooter() instanceof Player) {
                    Player damager = (Player) arrow.getShooter();
                    Player victim = (Player) event.getEntity();
                    double damageValue = event.getFinalDamage();
                    PermissionManager permissionManager = new PermissionManager(damager, plugin);
                    List<String> noticeMsgPermission = permissionManager.getUsingPermissions("battlenotice.message");
                    List<String> noticeTitlePermission = permissionManager.getUsingPermissions("battlenotice.title");
                    if (!noticeMsgPermission.isEmpty()) {
                        if (victim.getHealth() - damageValue < 0.01) {
                            TextComponent msgToDeath = new TextComponent(String.format(formatToDeath, victim.getName(), df.format(damageValue)));
                            InformationUtils.AddPvpExtraBuff(msgToDeath, victim, damager);
                        } else {
                            TextComponent msgToHurt = new TextComponent(String.format(formatToHurt, victim.getName(), df.format(damageValue),df.format(victim.getHealth() - damageValue)));
                            InformationUtils.AddPvpExtraBuff(msgToHurt, victim, damager);
                        }
                    }
                    if (!noticeTitlePermission.isEmpty()) {

                        if (victim.getHealth() - damageValue < 0.01) {
                            String leftBlood = "§a0.0";
                            TitleAPI.sendTitle(damager, 0, 15, 0, leftBlood, null);
//                            damager.sendTitle(leftBlood, null, 0, 15, 0);
                        } else {
                            Double leftBlood = victim.getHealth() - damageValue;
                            String colorCode = "§7➚§a";

                            if (leftBlood <= 14 && leftBlood > 8) {
                                colorCode = "§7➚§e";
                            } else if (leftBlood <= 8) {
                                colorCode = "§7➚§c";
                            }
                            String leftBloodStr = colorCode + df.format(leftBlood);
                            TitleAPI.sendTitle(damager, 0, 15, 0, leftBloodStr, null);
//                            damager.sendTitle(null, leftBloodStr, 0, 15, 0);
                        }
                    }
                }
            }
        }
    }
}
