package il.roboticaxolotl.mithril.datagen;

import il.roboticaxolotl.mithril.MithrilMod;
import il.roboticaxolotl.mithril.blocks.ModBlocks;
import il.roboticaxolotl.mithril.items.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;

public class ModModelProvider extends ModelProvider {
    public ModModelProvider(PackOutput output) {
        super(output, MithrilMod.MOD_ID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        registerItemModels(blockModels, itemModels);
        registerBlockModels(blockModels, itemModels);
    }

    private void registerItemModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        itemModels.generateFlatItem(ModItems.ENDER_TEMPLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.MITHRIL_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.MITHRIL_NUGGET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.RAW_MITHRIL.get(), ModelTemplates.FLAT_ITEM);
    }

    private void registerBlockModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        blockModels.createTrivialCube(ModBlocks.MITHRIL_ORE.get());
        blockModels.createTrivialCube(ModBlocks.RAW_MITHRIL_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.MITHRIL_BLOCK.get());
    }
}
