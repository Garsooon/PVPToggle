package org.garsooon.pvptoggle.data;

import org.bukkit.plugin.java.JavaPlugin;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

import java.io.*;
import java.util.*;

public class PluginConfig {

    //Seperating the config instead of putting into main
    private final File configFile;
    private ConfigValues values;
    private final JavaPlugin plugin;

    public PluginConfig(JavaPlugin plugin) {
        this.plugin = plugin;
        this.configFile = new File(plugin.getDataFolder(), "config.yml");
    }

    @SuppressWarnings({"IOStreamConstructor", "ResultOfMethodCallIgnored"})
    public void load() {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdirs();
        }

        if (!configFile.exists()) {
            values = new ConfigValues();
            save();
        } else {
            try (InputStream input = new FileInputStream(configFile)) {
                Yaml yaml = new Yaml();
                Object loaded = yaml.load(input);
                values = new ConfigValues();

                if (loaded instanceof Map) {
                    Map<?, ?> map = (Map<?, ?>) loaded;

                    Object defaultPvp = map.get("defaultPVPEnabled");
                    if (defaultPvp instanceof Boolean) {
                        values.defaultPVPEnabled = (Boolean) defaultPvp;
                    }

                    Object resetRelog = map.get("resetOnRelog");
                    if (resetRelog instanceof Boolean) {
                        values.resetOnRelog = (Boolean) resetRelog;
                    }

                    Object worlds = map.get("alwaysPVPWorlds");
                    if (worlds instanceof List) {
                       //its checked
                        @SuppressWarnings("unchecked")
                        List<String> worldList = (List<String>) worlds;
                        values.alwaysPVPWorlds = worldList;
                    }
                }
            } catch (IOException e) {
                plugin.getServer().getLogger().severe("Failed to load config.yml: " + e.getMessage());
                values = new ConfigValues();
            }
        }
    }

    public void save() {
        try (Writer writer = new FileWriter(configFile)) {
            DumperOptions options = new DumperOptions();
            options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
            options.setPrettyFlow(true);

            Representer representer = new Representer();
            representer.addClassTag(ConfigValues.class, Tag.MAP);

            Yaml yaml = new Yaml(representer, options);

            writer.write("# PVP Toggle Configuration\n");
            writer.write("# Whether PVP is enabled by default on join\n");
            writer.write("# This defines PVP status to default when player relogs\n");
            writer.write("# List of worlds where PVP is always enabled regardless of toggle. Copy the folder name of the world.\n\n");

            yaml.dump(values, writer);

        } catch (IOException e) {
            plugin.getServer().getLogger().severe("Failed to save config.yml: " + e.getMessage());
        }
    }

    public ConfigValues getValues() {
        return values;
    }

    public static class ConfigValues {
        public boolean defaultPVPEnabled = false;
        public boolean resetOnRelog = true;
        public List<String> alwaysPVPWorlds = Arrays.asList("world_nether", "pvp_arena");

        public ConfigValues() {}
    }
}
