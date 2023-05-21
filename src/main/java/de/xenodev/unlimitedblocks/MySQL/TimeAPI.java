package de.xenodev.unlimitedblocks.MySQL;

import de.xenodev.unlimitedblocks.UnlimitedBlocksAPI;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class TimeAPI {

    private File file = new File("plugins/" + UnlimitedBlocksAPI.getInstance().getName(), "Playertime.yml");
    private YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    public Integer getSecond(OfflinePlayer offlinePlayer){
        return cfg.getInt(offlinePlayer.getUniqueId() + "Time.Second");
    }

    public void setSecond(OfflinePlayer offlinePlayer, Integer second){
        cfg.set(offlinePlayer.getUniqueId() + "Time.Second", second);
        try { cfg.save(file); } catch (IOException e) { throw new RuntimeException(e); }
    }

    public void addSecond(OfflinePlayer offlinePlayer, Integer second){
        setSecond(offlinePlayer, getSecond(offlinePlayer) + second);
    }

    public Integer getMinute(OfflinePlayer offlinePlayer){
        return cfg.getInt(offlinePlayer.getUniqueId() + "Time.Minute");
    }

    public void setMinute(OfflinePlayer offlinePlayer, Integer minute){
        cfg.set(offlinePlayer.getUniqueId() + "Time.Minute", minute);
        try { cfg.save(file); } catch (IOException e) { throw new RuntimeException(e); }
    }

    public void addMinute(OfflinePlayer offlinePlayer, Integer minute){
        setMinute(offlinePlayer, getMinute(offlinePlayer) + minute);
    }

    public Integer getHour(OfflinePlayer offlinePlayer){
        return cfg.getInt(offlinePlayer.getUniqueId() + "Time.Hour");
    }

    public void setHour(OfflinePlayer offlinePlayer, Integer Hour){
        cfg.set(offlinePlayer.getUniqueId() + "Time.Hour", Hour);
        try { cfg.save(file); } catch (IOException e) { throw new RuntimeException(e); }
    }

    public void addHour(OfflinePlayer offlinePlayer, Integer Hour){
        setHour(offlinePlayer, getHour(offlinePlayer) + Hour);
    }

    public String getTime(OfflinePlayer offlinePlayer){
        if(getHour(offlinePlayer) != 0){
            return getHour(offlinePlayer) + "h";
        }else if(getMinute(offlinePlayer) >= 0 && getHour(offlinePlayer) == 0){
            return getMinute(offlinePlayer) + " min";
        }else if(getSecond(offlinePlayer) >= 0 && getMinute(offlinePlayer) == 0 && getHour(offlinePlayer) == 0){
            return getSecond(offlinePlayer) + " sec";
        }
        return "Kein Eintrag!";
    }
}
