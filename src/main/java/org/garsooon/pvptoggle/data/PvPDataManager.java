package org.garsooon.pvptoggle.data;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PvPDataManager {
    private final Map<UUID, Boolean> toggleMap = new HashMap<>();
    private final PluginConfig config;

    public PvPDataManager(PluginConfig config) {
        this.config = config;
    }

    public boolean isPvPEnabled(UUID uuid) {
        return toggleMap.getOrDefault(uuid, config.getValues().defaultPVPEnabled);
    }

    public void setPvPEnabled(UUID uuid, boolean enabled) {
        toggleMap.put(uuid, enabled);
    }

    public void reset(UUID uuid) {
        toggleMap.remove(uuid);
    }
}
