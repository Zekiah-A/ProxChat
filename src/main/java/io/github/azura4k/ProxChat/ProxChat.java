package io.github.azura4k.ProxChat;
import cn.nukkit.plugin.PluginBase;

public class ProxChat extends PluginBase {
    @Override
    public void onEnable() {
        super.onEnable();
        getServer().getLogger().info("ProxChat Enabled");
        saveDefaultConfig();
        this.getServer().getPluginManager().registerEvents(new PlayerChatListner(this), this);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        getServer().getLogger().info("ProxChat Disabled");
    }
}
