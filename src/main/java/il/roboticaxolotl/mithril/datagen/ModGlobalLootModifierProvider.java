package il.roboticaxolotl.mithril.datagen;

import il.roboticaxolotl.mithril.MithrilMod;
import il.roboticaxolotl.mithril.items.ModItems;
import il.roboticaxolotl.mithril.loot.AddItemModifier;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.storage.loot.predicates.AnyOfCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;

import java.util.concurrent.CompletableFuture;

public class ModGlobalLootModifierProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, MithrilMod.MOD_ID);
    }

    @Override
    protected void start() {
        this.add("ender_template_in_end_chests",
                new AddItemModifier(
                        new LootItemCondition[] {
                                AnyOfCondition.anyOf(
                                        LootTableIdCondition.builder(Identifier.withDefaultNamespace("chests/end_city")),
                                        LootTableIdCondition.builder(Identifier.withDefaultNamespace("chests/end_city_treasure"))
                                ).build(),
                                LootItemRandomChanceCondition.randomChance(0.25f).build()
                        },
                        0,
                        ModItems.ENDER_TEMPLATE.get()
                )
        );
    }
}
