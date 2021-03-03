package com.cadiducho.cservidoresmc.model.updater;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Getter
public class UpdaterInfo {

    private HashMap<String, String> minecraftVersions;
    private HashMap<String, String> pluginVersions;

    public Optional<Map.Entry<String, String>> getPluginForMinecraft(String mcVersion) {
        String recommendedVersion = minecraftVersions.get(mcVersion);
        return pluginVersions.entrySet().stream().filter((entry) -> entry.getKey().equals(recommendedVersion)).findAny();
    }
}

