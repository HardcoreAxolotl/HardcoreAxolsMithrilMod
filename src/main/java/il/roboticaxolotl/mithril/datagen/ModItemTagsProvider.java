package il.roboticaxolotl.mithril.datagen;

import il.roboticaxolotl.mithril.blocks.ModBlocks;
import il.roboticaxolotl.mithril.items.ModItems;
import il.roboticaxolotl.mithril.tags.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.ItemTags;
import net.neoforged.neoforge.common.data.ItemTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, String modId) {
        super(output, lookupProvider, modId);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        // Recipe Tags
        tag(ModTags.Items.MITHRIL_ITEMS)
                .add(ModItems.RAW_MITHRIL.get())
                .add(ModItems.MITHRIL_INGOT.get())
                .add(ModItems.MITHRIL_NUGGET.get())
                .add(ModBlocks.RAW_MITHRIL_BLOCK.get().asItem())
                .add(ModBlocks.MITHRIL_BLOCK.get().asItem());

        // Trim Material Tags
        tag(ItemTags.TRIM_MATERIALS)
                .add(ModItems.MITHRIL_INGOT.get());

        // Tool Tags
        tag(ModTags.Items.MITHRIL_TOOL_MATERIALS).add(ModItems.MITHRIL_INGOT.get());

        tag(ModTags.Items.MITHRIL_REPAIRABLE).add(ModItems.MITHRIL_INGOT.get());

        tag(ItemTags.SWORDS).add(ModItems.MITHRIL_SWORD.get());
        tag(ItemTags.PICKAXES).add(ModItems.MITHRIL_PICKAXE.get());
        tag(ItemTags.AXES).add(ModItems.MITHRIL_AXE.get());
        tag(ItemTags.SHOVELS).add(ModItems.MITHRIL_SHOVEL.get());
        tag(ItemTags.HOES).add(ModItems.MITHRIL_HOE.get());
        tag(ItemTags.SPEARS).add(ModItems.MITHRIL_SPEAR.get());

        // Armor Tags
        tag(ModTags.Items.REPAIRS_MITHRIL_ARMOR).add(ModItems.MITHRIL_INGOT.get());

        tag(ItemTags.HEAD_ARMOR).add(ModItems.MITHRIL_HELMET.get());
        tag(ItemTags.CHEST_ARMOR).add(ModItems.MITHRIL_CHESTPLATE.get());
        tag(ItemTags.LEG_ARMOR).add(ModItems.MITHRIL_LEGGINGS.get());
        tag(ItemTags.FOOT_ARMOR).add(ModItems.MITHRIL_BOOTS.get());
    }
}
