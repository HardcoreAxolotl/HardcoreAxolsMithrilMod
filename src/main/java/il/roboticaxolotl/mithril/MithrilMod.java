package il.roboticaxolotl.mithril;

import il.roboticaxolotl.mithril.blocks.ModBlocks;
import il.roboticaxolotl.mithril.creativetabs.ModCreativeTabs;
import il.roboticaxolotl.mithril.items.ModItems;

import il.roboticaxolotl.mithril.loot.ModLootTableModifier;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;

@Mod(MithrilMod.MOD_ID)
public class MithrilMod {
    public static final String MOD_ID = "mithril";

    public MithrilMod(IEventBus modEventBus, ModContainer modContainer) {
        ModItems.register(modEventBus);
        ModCreativeTabs.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModLootTableModifier.register(modEventBus);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
}
