package com.destroystokyo.paper.youcandfindpaper;

import java.util.List;

import java.util.stream.Collectors;

import org.bukkit.configuration.file.YamlConfiguration;
import org.spigotmc.SpigotWorldConfig;

import static com.destroystokyo.paper.youcandfindpaper.PaperConfig.log;
import static com.destroystokyo.paper.youcandfindpaper.PaperConfig.saveConfig;

public class PaperWorldConfig {

   private final String worldName;
   private final SpigotWorldConfig spigotConfig;
   private YamlConfiguration config;
   private boolean verbose;

   public PaperWorldConfig(String worldName, SpigotWorldConfig spigotConfig) {
      this.worldName = worldName;
      this.spigotConfig = spigotConfig;
      this.config = PaperConfig.config;
      init();
   }

   public void init() {
      this.config = PaperConfig.config; // grab updated reference
      log("-------- World Settings For [" + worldName + "] --------");
      PaperConfig.readConfig(PaperWorldConfig.class, this);
   }

   private void set(String path, Object val) {
      config.set("world-settings.default." + path, val);
      if (config.get("world-settings." + worldName + "." + path) != null) {
         config.set("world-settings." + worldName + "." + path, val);
      }
   }

   private void remove(String path) {
      config.addDefault("world-settings.default." + path, null);
      set(path, null);
   }

   public void removeOldValues() {
      boolean needsSave = false;

      if (needsSave) {
         saveConfig();
      }
   }

   private boolean getBoolean(String path, boolean def) {
      return this.getBoolean(path, def, true);
   }

   private boolean getBoolean(String path, boolean def, boolean setDefault) {
      if (setDefault) {
         config.addDefault("world-settings.default." + path, def);
      }
      return config.getBoolean("world-settings." + worldName + "." + path, config.getBoolean("world-settings.default." + path, def));
   }

   private double getDouble(String path, double def) {
      config.addDefault("world-settings.default." + path, def);
      return config.getDouble("world-settings." + worldName + "." + path, config.getDouble("world-settings.default." + path));
   }

   private int getInt(String path, int def) {
      return getInt(path, def, true);
   }

   private int getInt(String path, int def, boolean setDefault) {
      if (setDefault) {
         config.addDefault("world-settings.default." + path, def);
      }
      return config.getInt("world-settings." + worldName + "." + path, config.getInt("world-settings.default." + path, def));
   }

   private long getLong(String path, long def) {
      config.addDefault("world-settings.default." + path, def);
      return config.getLong("world-settings." + worldName + "." + path, config.getLong("world-settings.default." + path));
   }

   private float getFloat(String path, float def) {
      // TODO: Figure out why getFloat() always returns the default value.
      return (float) getDouble(path, (double) def);
   }

   private <T> List<T> getList(String path, List<T> def) {
      config.addDefault("world-settings.default." + path, def);
      return (List<T>) config.getList("world-settings." + worldName + "." + path, config.getList("world-settings.default." + path));
   }

   private String getString(String path, String def) {
      config.addDefault("world-settings.default." + path, def);
      return config.getString("world-settings." + worldName + "." + path, config.getString("world-settings.default." + path));
   }

   private <T extends Enum<T>> List<T> getEnumList(String path, List<T> def, Class<T> type) {
      config.addDefault("world-settings.default." + path, def.stream().map(Enum::name).collect(Collectors.toList()));
      return ((List<String>) (config.getList("world-settings." + worldName + "." + path, config.getList("world-settings.default." + path)))).stream().map(s -> Enum.valueOf(type, s)).collect(Collectors.toList());
   }
}