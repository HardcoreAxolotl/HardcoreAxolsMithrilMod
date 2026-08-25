package il.roboticaxolotl.mithril.armor;

import il.roboticaxolotl.mithril.MithrilMod;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;

/**
 * EQUIPMENT ASSET KEYS
 *
 * Equipment assets are registry keys that identify 3D armor models.
 * They link an ArmorMaterial to its visual appearance when worn on a player.
 *
 * ============================================================================
 * HOW IT WORKS:
 * ============================================================================
 *
 * 1. An ArmorMaterial (ModArmorMaterials.MITHRIL) references an EquipmentAsset key
 *    as its last parameter. This tells the game "when rendering this armor, use the
 *    3D model identified by this key."
 *
 * 2. The EquipmentAsset provider (ModEquipmentAssetProvider) generates a JSON file
 *    at `assets/<modid>/equipment/mithril.json` that maps this key to the actual
 *    texture files used for the 3D armor model layers.
 *
 * 3. The same EquipmentAsset key is used by ModTrimMaterials.MITHRIL_ASSETS to
 *    determine which color palette suffix to use when rendering trims on this armor.
 *    If the equipment asset key matches one in the MaterialAssetGroup's overrides map,
 *    that override suffix is used. Otherwise, the default suffix is used.
 *
 * ============================================================================
 * VANILLA EQUIPMENT ASSETS:
 * ============================================================================
 *
 * Vanilla defines these in EquipmentAssets:
 *   - EquipmentAssets.IRON      → iron armor model
 *   - EquipmentAssets.DIAMOND   → diamond armor model
 *   - EquipmentAssets.GOLD      → gold armor model
 *   - EquipmentAssets.NETHERITE → netherite armor model
 *   - EquipmentAssets.CHAINMAIL → chainmail armor model
 *   - EquipmentAssets.LEATHER   → leather armor model (with color tinting)
 *   - EquipmentAssets.COPPER    → copper armor model
 *   - EquipmentAssets.TURTLE    → turtle helmet model
 *
 * Our custom key (MITHRIL) points to our own 3D armor model textures.
 *
 * ============================================================================
 * WHY NETHERITE HAS A DARKER TRIM PALETTE:
 * ============================================================================
 *
 * In ModTrimMaterials, we map EquipmentAssets.NETHERITE → "mithril_darker" suffix.
 * This is because netherite armor is very dark, and the normal mithril trim color
 * would blend in. The "_darker" palette variant provides better contrast.
 *
 * Since our mithril armor uses ModEquipmentAssets.MITHRIL (not NETHERITE),
 * it gets the default "mithril" palette — which is the bright version.
 */
public interface ModEquipmentAssets {
    // The registry key for the EquipmentAsset registry itself.
    // This is always "minecraft:equipment_asset" (vanilla defines this registry).
    ResourceKey<? extends Registry<EquipmentAsset>> ROOT_ID = ResourceKey.createRegistryKey(Identifier.withDefaultNamespace("equipment_asset"));

    // Our custom equipment asset key: "mithril:mithril"
    // Referenced by:
    //   - ModArmorMaterials.MITHRIL (links armor material to this 3D model)
    //   - ModEquipmentAssetProvider (generates the JSON mapping this key to textures)
    ResourceKey<EquipmentAsset> MITHRIL = ResourceKey.create(ROOT_ID, Identifier.fromNamespaceAndPath(MithrilMod.MOD_ID, "mithril"));
}
