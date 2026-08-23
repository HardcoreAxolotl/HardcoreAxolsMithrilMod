package il.roboticaxolotl.mithril.datagen;

import il.roboticaxolotl.mithril.blocks.ModBlocks;
import il.roboticaxolotl.mithril.items.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
            super(packOutput, registries);
        }

        @Override
        protected @NonNull RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
            return new ModRecipeProvider(provider, recipeOutput);
        }

        @Override
        public String getName() {
            return "Mithril Recipes";
        }
    }

    @Override
    protected void buildRecipes() {
        // block up (9 ingots -> block)
        shaped(RecipeCategory.MISC, ModBlocks.MITHRIL_BLOCK.get())
                .unlockedBy(getHasName(ModItems.RAW_MITHRIL.get()), has(ModItems.RAW_MITHRIL))
                .unlockedBy(getHasName(ModItems.MITHRIL_INGOT.get()), has(ModItems.MITHRIL_INGOT))
                .unlockedBy(getHasName(ModItems.MITHRIL_NUGGET.get()), has(ModItems.MITHRIL_NUGGET))
                .unlockedBy(getHasName(ModBlocks.RAW_MITHRIL_BLOCK.get()), has(ModBlocks.RAW_MITHRIL_BLOCK))
                .unlockedBy(getHasName(ModBlocks.MITHRIL_BLOCK.get()), has(ModBlocks.MITHRIL_BLOCK))
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.MITHRIL_INGOT.get())
                .group("mithril_block")
                .save(output, "mithril:mithril_ingot_mithril_block");

        // ingot up (9 nuggets -> ingot)
        shaped(RecipeCategory.MISC, ModItems.MITHRIL_INGOT.get())
                .unlockedBy(getHasName(ModItems.RAW_MITHRIL.get()), has(ModItems.RAW_MITHRIL))
                .unlockedBy(getHasName(ModItems.MITHRIL_INGOT.get()), has(ModItems.MITHRIL_INGOT))
                .unlockedBy(getHasName(ModItems.MITHRIL_NUGGET.get()), has(ModItems.MITHRIL_NUGGET))
                .unlockedBy(getHasName(ModBlocks.RAW_MITHRIL_BLOCK.get()), has(ModBlocks.RAW_MITHRIL_BLOCK))
                .unlockedBy(getHasName(ModBlocks.MITHRIL_BLOCK.get()), has(ModBlocks.MITHRIL_BLOCK))
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.MITHRIL_NUGGET.get())
                .group("mithril_block")
                .save(output, "mithril:mithril_nugget_mithril_ingot");

        // raw block up
        shaped(RecipeCategory.MISC, ModBlocks.RAW_MITHRIL_BLOCK.get())
                .unlockedBy(getHasName(ModItems.RAW_MITHRIL.get()), has(ModItems.RAW_MITHRIL))
                .unlockedBy(getHasName(ModItems.MITHRIL_INGOT.get()), has(ModItems.MITHRIL_INGOT))
                .unlockedBy(getHasName(ModItems.MITHRIL_NUGGET.get()), has(ModItems.MITHRIL_NUGGET))
                .unlockedBy(getHasName(ModBlocks.RAW_MITHRIL_BLOCK.get()), has(ModBlocks.RAW_MITHRIL_BLOCK))
                .unlockedBy(getHasName(ModBlocks.MITHRIL_BLOCK.get()), has(ModBlocks.MITHRIL_BLOCK))
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.RAW_MITHRIL.get())
                .group("raw_mithril_block")
                .save(output, "mithril:raw_mithril_raw_mithril_block");

        // block down (block -> 9 ingots)
        shapeless(RecipeCategory.MISC, ModItems.MITHRIL_INGOT.get(), 9)
                .unlockedBy(getHasName(ModItems.RAW_MITHRIL.get()), has(ModItems.RAW_MITHRIL))
                .unlockedBy(getHasName(ModItems.MITHRIL_INGOT.get()), has(ModItems.MITHRIL_INGOT))
                .unlockedBy(getHasName(ModItems.MITHRIL_NUGGET.get()), has(ModItems.MITHRIL_NUGGET))
                .unlockedBy(getHasName(ModBlocks.RAW_MITHRIL_BLOCK.get()), has(ModBlocks.RAW_MITHRIL_BLOCK))
                .unlockedBy(getHasName(ModBlocks.MITHRIL_BLOCK.get()), has(ModBlocks.MITHRIL_BLOCK))
                .requires(ModBlocks.MITHRIL_BLOCK.get())
                .group("mithril")
                .save(output, "mithril:mithril_block_mithril_ingot");

        // ingot down (block -> 9 ingots)
        shapeless(RecipeCategory.MISC, ModItems.MITHRIL_NUGGET.get(), 9)
                .unlockedBy(getHasName(ModItems.RAW_MITHRIL.get()), has(ModItems.RAW_MITHRIL))
                .unlockedBy(getHasName(ModItems.MITHRIL_INGOT.get()), has(ModItems.MITHRIL_INGOT))
                .unlockedBy(getHasName(ModItems.MITHRIL_NUGGET.get()), has(ModItems.MITHRIL_NUGGET))
                .unlockedBy(getHasName(ModBlocks.RAW_MITHRIL_BLOCK.get()), has(ModBlocks.RAW_MITHRIL_BLOCK))
                .unlockedBy(getHasName(ModBlocks.MITHRIL_BLOCK.get()), has(ModBlocks.MITHRIL_BLOCK))
                .requires(ModItems.MITHRIL_INGOT.get())
                .group("mithril")
                .save(output, "mithril:mithril_ingot_mithril_nugget");

        // raw block down
        shapeless(RecipeCategory.MISC, ModItems.RAW_MITHRIL.get(), 9)
                .unlockedBy(getHasName(ModItems.RAW_MITHRIL.get()), has(ModItems.RAW_MITHRIL))
                .unlockedBy(getHasName(ModItems.MITHRIL_INGOT.get()), has(ModItems.MITHRIL_INGOT))
                .unlockedBy(getHasName(ModItems.MITHRIL_NUGGET.get()), has(ModItems.MITHRIL_NUGGET))
                .unlockedBy(getHasName(ModBlocks.RAW_MITHRIL_BLOCK.get()), has(ModBlocks.RAW_MITHRIL_BLOCK))
                .unlockedBy(getHasName(ModBlocks.MITHRIL_BLOCK.get()), has(ModBlocks.MITHRIL_BLOCK))
                .requires(ModBlocks.RAW_MITHRIL_BLOCK.get())
                .group("raw_mithril")
                .save(output, "mithril:raw_mithril_block_raw_mithril");
    }
}
