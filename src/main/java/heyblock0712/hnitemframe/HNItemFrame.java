package heyblock0712.hnitemframe;

import heyblock0712.hnitemframe.listeners.HideFrame;
import heyblock0712.hnitemframe.listeners.ShowFrame;
import org.bukkit.plugin.java.JavaPlugin;

public final class HNItemFrame extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new HideFrame(), this);
        getServer().getPluginManager().registerEvents(new ShowFrame(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
