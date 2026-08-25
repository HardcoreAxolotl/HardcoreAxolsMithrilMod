package il.roboticaxolotl.mithril.datagen;

import il.roboticaxolotl.mithril.MithrilMod;
import il.roboticaxolotl.mithril.blocks.ModBlocks;
import il.roboticaxolotl.mithril.items.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;

/**
 * DATA GENERATOR: Item and Block Model Definitions
 *
 * This file defines the visual models (how items/blocks look) for our mod's content.
 * It also overrides ALL vanilla armor item models so that custom trim materials
 * (like our mithril) render correctly in the inventory.
 *
 * ============================================================================
 * HOW ARMOR TRIM ITEM MODELS WORK (Two Systems):
 * ============================================================================
 *
 * 1. VANILLA SYSTEM - "minecraft:select" model type:
 *    - Vanilla generates item definitions using `generateTrimmableItem()`.
 *    - This creates a "select" model with a `TrimMaterialProperty` that switches
 *      between pre-baked trim overlay textures based on the applied material.
 *    - PROBLEM: Only the 11 vanilla materials (quartz, iron, netherite, redstone,
 *      copper, gold, emerald, diamond, lapis, amethyst, resin) are hardcoded.
 *    - Custom materials like mithril fall through to the untrimmed fallback model,
 *      so the trim overlay is INVISIBLE in the inventory.
 *
 * 2. NEOFORGE SYSTEM - "neoforge:trimmed_armor" model type:
 *    - NeoForge added `generateDynamicTrimmableItem()` which creates a
 *      `TrimmedArmorModel` that dynamically resolves ANY trim material at runtime.
 *    - It reads the trim material's asset suffix (e.g. "mithril" or "mithril_darker"),
 *      constructs the overlay texture path (e.g. "trims/items/helmet_trim_mithril"),
 *      and loads it from the atlas at runtime.
 *    - This means it works for ALL trim materials, vanilla AND custom.
 *
 * WHY WE OVERRIDE VANILLA ARMORS:
 *    - We call `generateDynamicTrimmableItem()` for every vanilla armor piece so
 *      they use NeoForge's dynamic system instead of vanilla's hardcoded select.
 *    - This file generates JSON under `assets/minecraft/items/` for vanilla items,
 *      overriding their default definitions with the dynamic trim version.
 *
 * ============================================================================
 * FILE STRUCTURE GENERATED:
 * ============================================================================
 *   assets/mithril/items/             - Our mod's item models (mithril_helmet.json, etc.)
 *   assets/minecraft/items/           - Overrides for vanilla armor (iron_helmet.json, etc.)
 *   assets/mithril/models/item/       - Our model JSONs (layer0 textures)
 *   assets/mithril/models/block/      - Our block model JSONs
 */
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
        // =====================================================================
        // STEP 1: Register simple flat item models (non-trimmable items)
        // =====================================================================
        // `generateFlatItem()` creates a basic "item/generated" model with a single texture layer.
        // These items don't support trims, so we just need a texture.

        itemModels.generateFlatItem(ModItems.ENDER_TEMPLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.MITHRIL_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.MITHRIL_NUGGET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.RAW_MITHRIL_ALLOY.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.MITHRIL_POWDER.get(), ModelTemplates.FLAT_ITEM);

        itemModels.generateFlatItem(ModItems.MITHRIL_HORSE_ARMOR.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.MITHRIL_NAUTILUS_ARMOR.get(), ModelTemplates.FLAT_ITEM);

        // `generateFlatItem()` with FLAT_HANDHELD gives a slightly different transform (held in hand like a tool).
        itemModels.generateFlatItem(ModItems.MITHRIL_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.MITHRIL_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.MITHRIL_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.MITHRIL_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.MITHRIL_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateSpear(ModItems.MITHRIL_SPEAR.get());

        itemModels.createFlatItemModel(ModItems.MITHRIL_HELMET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.createFlatItemModel(ModItems.MITHRIL_CHESTPLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.createFlatItemModel(ModItems.MITHRIL_LEGGINGS.get(), ModelTemplates.FLAT_ITEM);
        itemModels.createFlatItemModel(ModItems.MITHRIL_BOOTS.get(), ModelTemplates.FLAT_ITEM);

        itemModels.generateDynamicTrimmableItem(ModItems.MITHRIL_HELMET.get(), ItemModelGenerators.TRIM_PREFIX_HELMET);
        itemModels.generateDynamicTrimmableItem(ModItems.MITHRIL_CHESTPLATE.get(), ItemModelGenerators.TRIM_PREFIX_CHESTPLATE);
        itemModels.generateDynamicTrimmableItem(ModItems.MITHRIL_LEGGINGS.get(), ItemModelGenerators.TRIM_PREFIX_LEGGINGS);
        itemModels.generateDynamicTrimmableItem(ModItems.MITHRIL_BOOTS.get(), ItemModelGenerators.TRIM_PREFIX_BOOTS);

        itemModels.generateDynamicTrimmableItem(Items.LEATHER_HELMET, ItemModelGenerators.TRIM_PREFIX_HELMET, -6265536);
        itemModels.generateDynamicTrimmableItem(Items.LEATHER_CHESTPLATE, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, -6265536);
        itemModels.generateDynamicTrimmableItem(Items.LEATHER_LEGGINGS, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, -6265536);
        itemModels.generateDynamicTrimmableItem(Items.LEATHER_BOOTS, ItemModelGenerators.TRIM_PREFIX_BOOTS, -6265536);

        // --- Iron Armor ---
        itemModels.generateDynamicTrimmableItem(Items.IRON_HELMET, ItemModelGenerators.TRIM_PREFIX_HELMET);
        itemModels.generateDynamicTrimmableItem(Items.IRON_CHESTPLATE, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE);
        itemModels.generateDynamicTrimmableItem(Items.IRON_LEGGINGS, ItemModelGenerators.TRIM_PREFIX_LEGGINGS);
        itemModels.generateDynamicTrimmableItem(Items.IRON_BOOTS, ItemModelGenerators.TRIM_PREFIX_BOOTS);

        // --- Diamond Armor ---
        itemModels.generateDynamicTrimmableItem(Items.DIAMOND_HELMET, ItemModelGenerators.TRIM_PREFIX_HELMET);
        itemModels.generateDynamicTrimmableItem(Items.DIAMOND_CHESTPLATE, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE);
        itemModels.generateDynamicTrimmableItem(Items.DIAMOND_LEGGINGS, ItemModelGenerators.TRIM_PREFIX_LEGGINGS);
        itemModels.generateDynamicTrimmableItem(Items.DIAMOND_BOOTS, ItemModelGenerators.TRIM_PREFIX_BOOTS);

        // --- Gold Armor ---
        itemModels.generateDynamicTrimmableItem(Items.GOLDEN_HELMET, ItemModelGenerators.TRIM_PREFIX_HELMET);
        itemModels.generateDynamicTrimmableItem(Items.GOLDEN_CHESTPLATE, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE);
        itemModels.generateDynamicTrimmableItem(Items.GOLDEN_LEGGINGS, ItemModelGenerators.TRIM_PREFIX_LEGGINGS);
        itemModels.generateDynamicTrimmableItem(Items.GOLDEN_BOOTS, ItemModelGenerators.TRIM_PREFIX_BOOTS);

        // --- Netherite Armor ---
        itemModels.generateDynamicTrimmableItem(Items.NETHERITE_HELMET, ItemModelGenerators.TRIM_PREFIX_HELMET);
        itemModels.generateDynamicTrimmableItem(Items.NETHERITE_CHESTPLATE, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE);
        itemModels.generateDynamicTrimmableItem(Items.NETHERITE_LEGGINGS, ItemModelGenerators.TRIM_PREFIX_LEGGINGS);
        itemModels.generateDynamicTrimmableItem(Items.NETHERITE_BOOTS, ItemModelGenerators.TRIM_PREFIX_BOOTS);

        // --- Chainmail Armor ---
        itemModels.generateDynamicTrimmableItem(Items.CHAINMAIL_HELMET, ItemModelGenerators.TRIM_PREFIX_HELMET);
        itemModels.generateDynamicTrimmableItem(Items.CHAINMAIL_CHESTPLATE, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE);
        itemModels.generateDynamicTrimmableItem(Items.CHAINMAIL_LEGGINGS, ItemModelGenerators.TRIM_PREFIX_LEGGINGS);
        itemModels.generateDynamicTrimmableItem(Items.CHAINMAIL_BOOTS, ItemModelGenerators.TRIM_PREFIX_BOOTS);

        // --- Copper Armor ---
        itemModels.generateDynamicTrimmableItem(Items.COPPER_HELMET, ItemModelGenerators.TRIM_PREFIX_HELMET);
        itemModels.generateDynamicTrimmableItem(Items.COPPER_CHESTPLATE, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE);
        itemModels.generateDynamicTrimmableItem(Items.COPPER_LEGGINGS, ItemModelGenerators.TRIM_PREFIX_LEGGINGS);
        itemModels.generateDynamicTrimmableItem(Items.COPPER_BOOTS, ItemModelGenerators.TRIM_PREFIX_BOOTS);

        // --- Turtle Helmet (only helmet exists for turtle) ---
        itemModels.generateDynamicTrimmableItem(Items.TURTLE_HELMET, ItemModelGenerators.TRIM_PREFIX_HELMET);
    }

    private void registerBlockModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        blockModels.createTrivialCube(ModBlocks.MITHRIL_ORE.get());
        blockModels.createTrivialCube(ModBlocks.RAW_MITHRIL_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.MITHRIL_BLOCK.get());
    }
}
