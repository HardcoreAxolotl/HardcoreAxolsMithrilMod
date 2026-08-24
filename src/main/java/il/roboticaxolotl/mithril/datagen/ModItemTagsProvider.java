package il.roboticaxolotl.mithril.datagen;

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
        tag(ModTags.Items.MITHRIL_REPAIRABLE)
                .add(ModItems.MITHRIL_INGOT.get());

        tag(ModTags.Items.MITHRIL_TOOL_MATERIALS)
                .add(ModItems.MITHRIL_INGOT.get());

        tag(ItemTags.SWORDS)
                .add(ModItems.MITHRIL_SWORD.get());

        tag(ItemTags.PICKAXES)
                .add(ModItems.MITHRIL_PICKAXE.get());

        tag(ItemTags.AXES)
                .add(ModItems.MITHRIL_AXE.get());

        tag(ItemTags.SHOVELS)
                .add(ModItems.MITHRIL_SHOVEL.get());

        tag(ItemTags.HOES)
                .add(ModItems.MITHRIL_HOE.get());

        tag(ItemTags.SPEARS)
                .add(ModItems.MITHRIL_SPEAR.get());
    }
}
