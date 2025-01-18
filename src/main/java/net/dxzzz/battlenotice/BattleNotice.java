package net.dxzzz.battlenotice;

import me.clip.placeholderapi.PlaceholderAPIPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Event;
import org.bukkit.event.EventException;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.EventExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class BattleNotice extends JavaPlugin {
    public static PlaceholderAPIPlugin placeholderAPI;
    public  final Logger logger= getLogger();
    private static BattleListener battleListener;
    @Override
    public void onEnable() {
        logger.info("===========[BattleNotice正在加载中]===========");
        logger.info("Author: X_32mx");
        logger.info("QQ: 2644489337");
        logger.info("This plugin is only for Dxzzz.net");





        if(!this.setupPlaceholderAPI()){
            logger.severe(ChatColor.RED+"未找到PlaceholderAPI前置插件,部分功能将失效。");
        }
        else {
            logger.info("已查找到PlaceholderAPI");
        }
        battleListener = new BattleListener(this);
        Bukkit.getPluginManager().registerEvents(battleListener, this);
//        Bukkit.getPluginManager().registerEvent(AsyncPlayerChatEvent.class, battleListener, EventPriority.HIGH,  new EventExecutor() {
//            @Override
//            public void execute(Listener listener, Event event) throws EventException {
//                if (event instanceof EntityDamageByEntityEvent) {
//                    battleListener.onEntityDamageByEntity((EntityDamageByEntityEvent) event);
//                }
//            }
//        },this);
        BattleNoticePlaceholderExpansion battleNoticePlaceholderExpansion = BattleNoticePlaceholderExpansion.getInstance();
        battleNoticePlaceholderExpansion.register();
//        Bukkit.getPluginCommand("chatfont").setExecutor(new CommandExc(this));


        logger.info("==========[BattleNotice已加载完毕]=========");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    private boolean setupPlaceholderAPI() {
        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") == null) {
            return false;
        }
        placeholderAPI = (PlaceholderAPIPlugin) getServer().getPluginManager().getPlugin("PlaceholderAPI");
        return placeholderAPI != null;
    }
}
