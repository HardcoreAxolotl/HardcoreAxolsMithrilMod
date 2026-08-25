package il.roboticaxolotl.mithril.worldgen;

import il.roboticaxolotl.mithril.MithrilMod;
import il.roboticaxolotl.mithril.armor.ModTrimMaterials;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 * DATAPACK REGISTRY BOOTSTRAPPER
 *
 * This file registers custom datapack entries with Minecraft's built-in registries.
 * Datapack entries are loaded at runtime from JSON files in `data/<modid>/`.
 *
 * ============================================================================
 * WHY TRIM MATERIALS USE DATAPACKS:
 * ============================================================================
 *
 * TrimMaterial is a datapack registry, meaning it's defined via JSON files rather than
 * code registration. This allows datapacks to add/modify trim materials without code changes.
 *
 * The RegistrySetBuilder + bootstrap pattern is how NeoForge handles datapack registries:
 *
 * 1. BUILDER.add(Registries.TRIM_MATERIAL, ModTrimMaterials::bootstrap)
 *    → Registers our bootstrap method that creates the TrimMaterial entry
 *
 * 2. At datagen time, this generates the JSON:
 *    `data/mithril/trim_material/mithril.json`
 *
 * 3. At runtime, Minecraft loads this JSON and registers the TrimMaterial in its registry
 *
 * ============================================================================
 * GENERATED JSON STRUCTURE:
 * ============================================================================
 *
 * The generated `data/mithril/trim_material/mithril.json` contains:
 * ```json
 * {
 *   "assets": {
 *     "asset_name": "mithril"
 *   },
 *   "ingredient": "mithril:mithril_ingot",
 *   "description": {
 *     "translate": "trim_material.mithril.mithril"
 *   }
 * }
 * ```
 *
 * This tells the game:
 *   - asset_name: "mithril" → use palette suffix "mithril" (and "mithril_darker" for netherite)
 *   - ingredient: The item that represents this material (shown in smithing recipe)
 *   - description: The translatable name shown in the smithing table UI
 *
 * ============================================================================
 * COMPLETE TRIM MATERIAL REGISTRATION CHECKLIST:
 * ============================================================================
 *
 * ✅ Step 1: Define ResourceKey → ModTrimMaterials.MITHRIL
 * ✅ Step 2: Create MaterialAssetGroup → ModTrimMaterials.MITHRIL_ASSETS
 * ✅ Step 3: Bootstrap the registry entry → ModTrimMaterials.bootstrap()
 * ✅ Step 4: Register bootstrap with RegistrySetBuilder → HERE (this file)
 * ✅ Step 5: Add .trimMaterial() to the ingot item → ModItems.MITHRIL_INGOT
 * ✅ Step 6: Create palette textures → assets/mithril/textures/trims/color_palettes/
 * ✅ Step 7: Atlas discovers palettes → NeoForge's directory_paletted_permutations
 * ✅ Step 8: Override armor models → ModModelProvider.generateDynamicTrimmableItem()
 */
public class ModDatapackProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            // Terrain Generation
            .add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifiers::bootstrap)
            // Armor Trim Material Registration
            // This adds our custom trim material to the datapack registry.
            // At datagen time, this generates `data/mithril/trim_material/mithril.json`
            // At runtime, Minecraft loads this JSON and registers the material.
            .add(Registries.TRIM_MATERIAL, ModTrimMaterials::bootstrap)
            ;

    public ModDatapackProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(MithrilMod.MOD_ID));
    }
}
