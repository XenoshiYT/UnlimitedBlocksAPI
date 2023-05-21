package de.xenodev.unlimitedblocks;

import de.xenodev.unlimitedblocks.MySQL.TimeAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class UnlimitedBlocksAPI extends JavaPlugin implements Listener {

    private static UnlimitedBlocksAPI instance;
    final TimeAPI timeAPI = new TimeAPI();

    @Override
    public void onEnable() {
        instance = this;
        saveOnlineTime();
    }

    @Override
    public void onDisable() {
    }

    public static UnlimitedBlocksAPI getInstance() {
        return instance;
    }

    public TimeAPI getTimeAPI() {
        return timeAPI;
    }

    private void saveOnlineTime(){
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, new Runnable() {
            @Override
            public void run() {
                for(Player all : Bukkit.getOnlinePlayers()){
                    timeAPI.addSecond(all, 1);
                    if(timeAPI.getSecond(all) == 60){
                        timeAPI.setSecond(all, 0);
                        timeAPI.addMinute(all, 1);
                    }
                    if(timeAPI.getMinute(all) == 60){
                        timeAPI.setMinute(all, 0);
                        timeAPI.addHour(all, 1);
                    }
                }
            }
        }, 0, 20L);
    }
}