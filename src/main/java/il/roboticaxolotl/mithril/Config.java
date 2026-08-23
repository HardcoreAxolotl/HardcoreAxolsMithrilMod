package il.roboticaxolotl.mithril;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.common.ModConfigSpec;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue SHOULD_ORE_SPAWN = BUILDER
            .comment("Should mithril spawn?")
            .define("spawn", true);

    public static final ModConfigSpec.IntValue MAX_ORE_SPAWN_HEIGHT = BUILDER
            .comment("The highest point mithril ore can spawn at")
            .defineInRange("max_ore_spawn_height", 50, 0, 100);

    public static final ModConfigSpec.IntValue MIN_ORE_SPAWN_HEIGHT = BUILDER
            .comment("The lowest point mithril ore can spawn at")
            .defineInRange("min_ore_spawn_height", 25, 0, 100);

    /*public static final ModConfigSpec.ConfigValue<String> SEARCH_FOR = BUILDER
            .comment("Don't ask me.")
            .define("search_for", "...");
    "mithril.configuration.search_for": "What are you searching for?",
            */

    static final ModConfigSpec SPEC = BUILDER.build();

    private static boolean validateItemName(final Object obj) {
        return obj instanceof String itemName && BuiltInRegistries.ITEM.containsKey(Identifier.parse(itemName));
    }
}
