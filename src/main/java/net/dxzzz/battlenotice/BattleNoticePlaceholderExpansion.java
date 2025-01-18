package net.dxzzz.battlenotice;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class BattleNoticePlaceholderExpansion extends PlaceholderExpansion {
    private static BattleNoticePlaceholderExpansion instance;
    private static String identifier;
    private static JavaPlugin plugin = JavaPlugin.getProvidingPlugin(BattleNoticePlaceholderExpansion.class);

    public static BattleNoticePlaceholderExpansion getInstance() {
        if (instance == null) {
            instance = new BattleNoticePlaceholderExpansion("battlenotice");
        }
        return instance;
    }

    public BattleNoticePlaceholderExpansion(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public String getAuthor() {
        return "wyuu";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        if (identifier.startsWith("message")) {
            String[] parts = identifier.split("_");
            if (parts.length < 2) {
                return null;
            }
            String tag = parts[1];
            PermissionManager permissionManager = new PermissionManager(player, plugin);
            if (tag.equals("combat")) {
                List<String> msgNoticePermission = permissionManager.getUsingPermissions("battlenotice.message.combat");
                if (msgNoticePermission.isEmpty()) {
                    return "§c关闭";
                } else {
                    return "§a开启";
                }
            } else if (tag.equals("bow")) {
                List<String> msgNoticePermission = permissionManager.getUsingPermissions("battlenotice.message.bow");
                if (msgNoticePermission.isEmpty()) {
                    return "§c关闭";
                } else {
                    return "§a开启";
                }
            } else {
                return null;
            }
        } else if (identifier.startsWith("title")) {
            String[] parts = identifier.split("_");
            if (parts.length < 2) {
                return null;
            }
            String tag = parts[1];
            PermissionManager permissionManager = new PermissionManager(player, plugin);
            if (tag.equals("combat")) {
                List<String> msgNoticePermission = permissionManager.getUsingPermissions("battlenotice.title.combat");
                if (msgNoticePermission.isEmpty()) {
                    return "§c关闭";
                } else {
                    return "§a开启";
                }
            } else if (tag.equals("bow")) {
                List<String> msgNoticePermission = permissionManager.getUsingPermissions("battlenotice.title.bow");
                if (msgNoticePermission.isEmpty()) {
                    return "§c关闭";
                } else {
                    return "§a开启";
                }
            } else {
                return null;
            }

        }
        return null;
    }
}

