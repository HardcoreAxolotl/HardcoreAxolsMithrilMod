package il.roboticaxolotl.mithril.datagen;

import il.roboticaxolotl.mithril.MithrilMod;
import il.roboticaxolotl.mithril.items.ModItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementsProvider extends AdvancementProvider {
    public ModAdvancementsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, List.of(new MithrilModAdvancements()));
    }
    // TODO: add MAH8 easter egg category and achievement named: "Sacrifice for MAH8" obtained by dropping 64 mithril blocks to the void

    public static class MithrilModAdvancements implements AdvancementSubProvider {
        @Override
        public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> output) {
            var items = registries.lookupOrThrow(Registries.ITEM);

            AdvancementHolder mithril_hoe = Advancement.Builder.advancement()
                    .parent(AdvancementSubProvider.createPlaceholder("minecraft:husbandry/obtain_netherite_hoe"))
                    .display(
                        ModItems.MITHRIL_HOE,
                        Component.translatable("advancements.mithrilmod.mithril_hoe.title"),
                        Component.translatable("advancements.mithrilmod.mithril_hoe.description"),
                        Identifier.withDefaultNamespace("gui/advancementsbackgrounds/husbandry"),
                        AdvancementType.CHALLENGE,
                        false,
                        true,
                        true
                    )
                    .addCriterion("obtain_mithril_hoe", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.MITHRIL_HOE).build()))
                    .save(output, Identifier.fromNamespaceAndPath(MithrilMod.MOD_ID, "husbandry/obtain_mithril_hoe"));

            AdvancementHolder mithril_powder = Advancement.Builder.advancement()
                    .parent(AdvancementSubProvider.createPlaceholder("minecraft:end/root"))
                    .display(
                        ModItems.MITHRIL_POWDER,
                        Component.translatable("advancements.mithrilmod.mithril_powder.title"),
                        Component.translatable("advancements.mithrilmod.mithril_powder.description"),
                        Identifier.withDefaultNamespace("gui/advancementsbackgrounds/end"),
                        AdvancementType.GOAL,
                        false,
                        true,
                        true
                    )
                    .addCriterion("obtained_mithril_powder", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.MITHRIL_POWDER).build()))
                    .save(output, Identifier.fromNamespaceAndPath(MithrilMod.MOD_ID, "end/mithril_powder"));

            AdvancementHolder mithril_ingot = Advancement.Builder.advancement()
                    .parent(mithril_powder)
                    .display(
                            ModItems.MITHRIL_INGOT,
                            Component.translatable("advancements.mithrilmod.mithril_ingot.title"),
                            Component.translatable("advancements.mithrilmod.mithril_ingot.description"),
                            Identifier.withDefaultNamespace("gui/advancementsbackgrounds/end"),
                            AdvancementType.GOAL,
                            false,
                            true,
                            false
                    )
                    .addCriterion("obtained_mithril_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.MITHRIL_INGOT).build()))
                    .save(output, Identifier.fromNamespaceAndPath(MithrilMod.MOD_ID, "end/mithril_ingot"));

            AdvancementHolder cover_me_with_mithril = Advancement.Builder.advancement()
                    .parent(mithril_ingot)
                    .display(
                            ModItems.MITHRIL_CHESTPLATE,
                            Component.translatable("advancements.mithrilmod.cover_me_with_mithril.title"),
                            Component.translatable("advancements.mithrilmod.cover_me_with_mithril.description"),
                            Identifier.withDefaultNamespace("gui/advancementsbackgrounds/end"),
                            AdvancementType.CHALLENGE,
                            false,
                            true,
                            false
                    )
                    .addCriterion("mithril_armor", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.MITHRIL_BOOTS, ModItems.MITHRIL_LEGGINGS, ModItems.MITHRIL_CHESTPLATE, ModItems.MITHRIL_HELMET).build()))
                    .save(output, Identifier.fromNamespaceAndPath(MithrilMod.MOD_ID, "end/mithril_armor"));
            
        }
    }
}
