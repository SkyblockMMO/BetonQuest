package org.betonquest.betonquest.api.bukkit.config.custom.multi.fallback;

import org.betonquest.betonquest.api.bukkit.config.custom.fallback.FallbackConfiguration;
import org.betonquest.betonquest.api.bukkit.config.custom.fallback.FallbackConfigurationNestedTest;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.MemoryConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.junit.jupiter.api.Tag;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This is a test for the {@link MultiFallbackConfiguration}.
 */
@Tag("ConfigurationSection")
@SuppressWarnings("PMD.JUnitAssertionsShouldIncludeMessage")
class FallbackConfigurationNestedWithMultiFallbackTest extends FallbackConfigurationNestedTest {
    @Override
    public Configuration getConfig() {
        final Configuration original = YamlConfiguration.loadConfiguration(new File("src/test/resources/api/bukkit/fallback/original.yml"));
        fallback = YamlConfiguration.loadConfiguration(new File("src/test/resources/api/bukkit/fallback/fallback.yml"));

        final Configuration defaults = super.getDefaultConfig().getDefaults();
        assertNotNull(defaults);
        original.setDefaults(defaults);

        final Configuration originalRoot = new MemoryConfiguration();
        final Configuration fallbackRoot = new MemoryConfiguration();
        originalRoot.set("original.nested.section", original);
        fallbackRoot.set("fallback.nested.section", fallback);
        final ConfigurationSection originalSection = originalRoot.getConfigurationSection("original.nested.section");
        final ConfigurationSection fallbackSection = fallbackRoot.getConfigurationSection("fallback.nested.section");
        assertNotNull(originalSection);
        assertNotNull(fallbackSection);

        return new FallbackConfiguration(originalSection, fallbackSection);
    }
}
