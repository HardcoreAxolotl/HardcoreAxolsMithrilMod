package il.roboticaxolotl.mithril.datagen;

import il.roboticaxolotl.mithril.MithrilMod;
import il.roboticaxolotl.mithril.blocks.ModBlocks;
import il.roboticaxolotl.mithril.tags.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, MithrilMod.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.MITHRIL_BLOCK.get())
                .add(ModBlocks.RAW_MITHRIL_BLOCK.get())
                .add(ModBlocks.MITHRIL_ORE.get());

        tag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
                .add(ModBlocks.MITHRIL_BLOCK.get())
                .add(ModBlocks.RAW_MITHRIL_BLOCK.get())
                .add(ModBlocks.MITHRIL_ORE.get());

        tag(ModTags.Blocks.NEEDS_MITHRIL_TOOL)
                .add(Blocks.REINFORCED_DEEPSLATE);
    }
}
