package il.roboticaxolotl.mithril;

import il.roboticaxolotl.mithril.datagen.*;
import il.roboticaxolotl.mithril.worldgen.ModDatapackProvider;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Collections;
import java.util.List;

@EventBusSubscriber(modid = MithrilMod.MOD_ID)
public class MithrilModDataGen {
    @SubscribeEvent
    public static void gatherClientData(GatherDataEvent.Client event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        var lookupProvider = event.getLookupProvider();

        generator.addProvider(true, new ModModelProvider(output));
        generator.addProvider(true, new ModBlockTagsProvider(output, lookupProvider));
        generator.addProvider(true, new ModItemTagsProvider(output, lookupProvider, MithrilMod.MOD_ID));
        generator.addProvider(true, new LootTableProvider(output, Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(ModBlockLootTableProvider::new, LootContextParamSets.BLOCK)), lookupProvider));

        generator.addProvider(true, new ModRecipeProvider.Runner(output, lookupProvider));
        generator.addProvider(true, new ModDatapackProvider(output, lookupProvider));
        generator.addProvider(true, new ModEquipmentAssetProvider(output));
        generator.addProvider(true, new ModAdvancementsProvider(output, lookupProvider));
        generator.addProvider(true, new ModGlobalLootModifierProvider(output, lookupProvider));
    }
}
