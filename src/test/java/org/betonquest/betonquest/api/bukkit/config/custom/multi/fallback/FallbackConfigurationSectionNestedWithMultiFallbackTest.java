package org.betonquest.betonquest.api.bukkit.config.custom.multi.fallback;

import org.betonquest.betonquest.api.bukkit.config.custom.fallback.FallbackConfiguration;
import org.betonquest.betonquest.api.bukkit.config.custom.fallback.FallbackConfigurationSection;
import org.betonquest.betonquest.api.bukkit.config.custom.fallback.FallbackConfigurationSectionNestedTest;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.MemoryConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the {@link FallbackConfigurationSection} class.
 */
@SuppressWarnings("PMD.JUnitAssertionsShouldIncludeMessage")
public class FallbackConfigurationSectionNestedWithMultiFallbackTest extends FallbackConfigurationSectionNestedTest {
    @Override
    public ConfigurationSection getConfig() {
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
