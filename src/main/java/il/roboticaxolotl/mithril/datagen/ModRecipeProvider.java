package il.roboticaxolotl.mithril.datagen;

import il.roboticaxolotl.mithril.MithrilMod;
import il.roboticaxolotl.mithril.blocks.ModBlocks;
import il.roboticaxolotl.mithril.items.ModItems;
import il.roboticaxolotl.mithril.tags.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import org.jspecify.annotations.NonNull;

import java.util.List;
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

        mithrilSmithing(Items.NETHERITE_SWORD, RecipeCategory.COMBAT, ModItems.MITHRIL_SWORD.get());
        mithrilSmithing(Items.NETHERITE_PICKAXE, RecipeCategory.COMBAT, ModItems.MITHRIL_PICKAXE.get());
        mithrilSmithing(Items.NETHERITE_AXE, RecipeCategory.COMBAT, ModItems.MITHRIL_AXE.get());
        mithrilSmithing(Items.NETHERITE_SHOVEL, RecipeCategory.COMBAT, ModItems.MITHRIL_SHOVEL.get());
        mithrilSmithing(Items.NETHERITE_HOE, RecipeCategory.COMBAT, ModItems.MITHRIL_HOE.get());
        mithrilSmithing(Items.NETHERITE_SPEAR, RecipeCategory.COMBAT, ModItems.MITHRIL_SPEAR.get());

        List<ItemLike> MITHRIL_SMELTABLES = List.of(ModItems.RAW_MITHRIL, ModBlocks.MITHRIL_ORE);
        oreSmelting(MITHRIL_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, ModItems.MITHRIL_INGOT.get(), 0.25f, 300, "mithril");
        oreBlasting(MITHRIL_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, ModItems.MITHRIL_INGOT.get(), 0.25f, 150, "mithril");
        oreSmelting(List.of(ModItems.MITHRIL_INGOT), RecipeCategory.MISC, CookingBookCategory.MISC, ModItems.MITHRIL_NUGGET.get(), 0.25f, 300, "mithril_nugget");
        oreBlasting(List.of(ModItems.MITHRIL_INGOT), RecipeCategory.MISC, CookingBookCategory.MISC, ModItems.MITHRIL_NUGGET.get(), 0.25f, 150, "mithril_nugget");
    }

    protected void mithrilSmithing(Item base, RecipeCategory category, Item result) {
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.ENDER_TEMPLATE), Ingredient.of(base), this.tag(ModTags.Items.MITHRIL_TOOL_MATERIALS), category, result).unlocks("has_mithril_ingot", this.has(ModTags.Items.MITHRIL_TOOL_MATERIALS)).save(this.output, MithrilMod.MOD_ID + ":" + getItemName(result) + "_smithing");
    }

    @Override
    protected <T extends AbstractCookingRecipe> void oreCooking(AbstractCookingRecipe.Factory<T> factory, List<ItemLike> smeltables,
                                                                RecipeCategory craftingCategory, CookingBookCategory cookingCategory, ItemLike result,
                                                                float experience, int cookingTime, String group, String fromDesc) {
        for(ItemLike itemlike : smeltables) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), craftingCategory, cookingCategory, result, experience, cookingTime, factory).group(group).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(output, MithrilMod.MOD_ID + ":" + getItemName(result) + fromDesc + "_" + getItemName(itemlike));
        }
    }
}
