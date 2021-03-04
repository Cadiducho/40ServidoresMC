package com.cadiducho.cservidoresmc;

import com.cadiducho.cservidoresmc.model.updater.UpdaterInfo;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class TestUpdater {

    @Test
    void parseUpdateRequest() {
        String file = "{\n" +
                "    \"pluginVersions\": {\n" +
                "        \"3.0\": \"Reescritura del sistema para hacerlo compatible con Spigot, Sponge y BungeeCord\"\n" +
                "    },\n" +
                "    \"minecraftVersions\": {\n" +
                "        \"1.8.8\": \"3.0\",\n" +
                "        \"1.12.2\": \"3.0\",\n" +
                "        \"1.13.2\": \"3.0\",\n" +
                "        \"1.14.4\": \"3.0\",\n" +
                "        \"1.15.2\": \"3.0\",\n" +
                "        \"1.16.2\": \"3.0\",\n" +
                "        \"1.16.4\": \"3.0\",\n" +
                "        \"1.16.5\": \"3.0\"\n" +
                "    }\n" +
                "}";
        Gson gson = new Gson();
        UpdaterInfo updaterInfo = gson.fromJson(file, UpdaterInfo.class);
        assertNotNull(updaterInfo);
        assertEquals("3.0", updaterInfo.getMinecraftVersions().get("1.16.5"));
        Optional<Map.Entry<String, String>> versionEntry = updaterInfo.getPluginForMinecraft("1.16.5");
        assertTrue(versionEntry.isPresent());

        String updaterVersion = versionEntry.get().getKey();
        String updateDescription = versionEntry.get().getValue();
        assertEquals("3.0", updaterVersion);
        assertEquals("Reescritura del sistema para hacerlo compatible con Spigot, Sponge y BungeeCord", updateDescription);
    }
}
