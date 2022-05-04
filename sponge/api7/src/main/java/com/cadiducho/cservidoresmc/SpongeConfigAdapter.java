package com.cadiducho.cservidoresmc;

import com.cadiducho.cservidoresmc.api.CSPlugin;
import com.cadiducho.cservidoresmc.config.CSConfiguration;
import com.cadiducho.cservidoresmc.config.ConfigurateConfigAdapter;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;

import java.nio.file.Path;

public class SpongeConfigAdapter extends ConfigurateConfigAdapter implements CSConfiguration {

    public SpongeConfigAdapter(CSPlugin plugin, Path path) {
        super(plugin, path);
    }

    @Override
    protected ConfigurationLoader<? extends ConfigurationNode> createLoader(Path path) {
        return HoconConfigurationLoader.builder().setPath(path).build();
    }
}
