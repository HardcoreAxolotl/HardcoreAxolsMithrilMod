package il.roboticaxolotl.mithril.armor;

import il.roboticaxolotl.mithril.MithrilMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.minecraft.world.item.equipment.trim.MaterialAssetGroup;
import net.minecraft.world.item.equipment.trim.TrimMaterial;
import java.util.Map;

/**
 * CUSTOM TRIM MATERIAL REGISTRY
 *
 * This file defines a custom trim material ("mithril") that players can apply to any
 * armor piece using a smithing table + a smithing template. It creates the colored
 * overlay you see on armor when a trim is applied.
 *
 * ============================================================================
 * HOW VANILLA TRIM MATERIALS WORK:
 * ============================================================================
 *
 * A trim material is a registry entry (registered under Registries.TRIM_MATERIAL) that
 * defines:
 *   (a) A display name shown in the smithing table UI
 *   (b) An "asset group" that maps equipment asset keys to texture palette suffixes
 *
 * The asset suffix determines which color palette texture to use when rendering the trim.
 * For example, "iron" maps to "trims/color_palettes/iron.png" which defines the trim color.
 *
 * ============================================================================
 * WHAT THIS FILE DEFINES:
 * ============================================================================
 *
 * 1. MITHRIL (ResourceKey<TrimMaterial>) - The registry key for our trim material.
 *    The game uses this key as the identifier for our material. It's stored on armor
 *    items as part of the DataComponents.TRIM component.
 *
 * 2. MITHRIL_ASSETS (MaterialAssetGroup) - Maps equipment asset keys to palette suffixes.
 *    This tells the rendering system which color palette to use for each armor model.
 *
 *    - For MOST armor types (diamond, iron, gold, copper, chainmail, leather, turtle):
 *      suffix = "mithril" → looks for "trims/color_palettes/mithril.png"
 *
 *    - For NETHERITE armor specifically:
 *      suffix = "mithril_darker" → looks for "trims/color_palettes/mithril_darker.png"
 *
 *    This is the same pattern vanilla uses (e.g. netherite has "netherite_darker").
 *    Darker variants ensure the trim is visible against netherite's dark texture.
 *
 * 3. bootstrap() - Called by ModDatapackProvider to register the material with Minecraft's
 *    datapack system. This generates the JSON file at:
 *    `data/mithril/trim_material/mithril.json`
 *
 * ============================================================================
 * WHAT YOU NEED TO CREATE A CUSTOM TRIM MATERIAL:
 * ============================================================================
 *
 * A. Register a ResourceKey (this file, step 1)
 * B. Create a MaterialAssetGroup (this file, step 2)
 * C. Bootstrap the registry entry (this file, step 3)
 * D. Add the .trimMaterial() component to your ingot item (see ModItems.java)
 * E. Create palette textures in assets/<modid>/textures/trims/color_palettes/
 *    - <material>.png (used for most armor types)
 *    - <material>_darker.png (used for netherite, optional)
 * F. The textures MUST be discovered by an atlas (see notes in ModEquipmentAssetProvider)
 *
 * ============================================================================
 * PALETTE TEXTURE REQUIREMENTS:
 * ============================================================================
 *
 * The palette PNG files (trims/color_palettes/mithril.png) must be:
 *   - 256x1 pixel texture
 *   - Each pixel maps to a different part of the trim overlay
 *   - The pixel color IS the trim color (greyscale → tinted by the pixel value)
 *
 * NeoForge's `neoforge:directory_paletted_permutations` in the armor_trims atlas
 * automatically discovers palette files from `trims/color_palettes/` across ALL
 * mod namespaces, so you just need the files to exist — no atlas registration needed.
 */
public class ModTrimMaterials {

    // Step 1: Define the Registry Key
    // This creates a unique identifier for our trim material in the TrimMaterial registry.
    // The path "mithril" becomes the material ID used in:
    //   - DataComponents.TRIM (stored on trimmed armor items)
    //   - TrimMaterialProperty (used by vanilla's select model to pick trim textures)
    //   - MaterialAssetGroup suffixes (maps this material to its palette textures)
    public static final ResourceKey<TrimMaterial> MITHRIL = ResourceKey.create(
            Registries.TRIM_MATERIAL,
            Identifier.fromNamespaceAndPath(MithrilMod.MOD_ID, "mithril")
    );

    // Step 2: Define the MaterialAssetGroup
    // This maps equipment asset keys (armor types) to palette texture suffixes.
    //
    // `MaterialAssetGroup.create(baseSuffix, overrides)`:
    //   - baseSuffix: The default suffix used for all armor types NOT in the overrides map.
    //     For us: "mithril" → the renderer looks for "trims/color_palettes/mithril.png"
    //
    //   - overrides: A Map<ResourceKey<EquipmentAsset>, String> that maps specific armor
    //     types to different suffixes. We override NETHERITE to use "mithril_darker" so
    //     the trim is more visible on dark netherite armor.
    //
    // The EquipmentAsset key (e.g. EquipmentAssets.NETHERITE) identifies which 3D armor
    // model is being worn. This is set in ModEquipmentAssets and referenced in
    // ModArmorMaterials when creating the ArmorMaterial.
    //
    // At render time, TrimmedArmorModel.createTrimLayer() does:
    //   suffix = material.value().assets().assetId(equipmentAssetKey).suffix()
    //   texture = baseTrimTexture.withSuffix("_" + suffix)
    //   e.g. "minecraft:trims/items/helmet_trim_mithril" or "helmet_trim_mithril_darker"
    public static final MaterialAssetGroup MITHRIL_ASSETS = MaterialAssetGroup.create(
            "mithril",
            Map.of(EquipmentAssets.NETHERITE, "mithril_darker")
    );

    // Step 3: Bootstrap method
    // Called by ModDatapackProvider to register this material with Minecraft's datapack system.
    // This generates `data/mithril/trim_material/mithril.json` containing:
    //   - asset_name: "mithril" (the base palette suffix)
    //   - ingredient: "mithril:mithril_ingot" (the item that provides this material)
    //   - description: The translatable display name shown in the smithing table
    //
    // The generated JSON is used at runtime to look up which palette texture to use
    // when rendering trims with this material on any armor piece.
    public static void bootstrap(BootstrapContext<TrimMaterial> context) {
        context.register(MITHRIL, new TrimMaterial(
                MITHRIL_ASSETS,
                Component.translatable("trim_material.mithril.mithril")
        ));
    }
}
