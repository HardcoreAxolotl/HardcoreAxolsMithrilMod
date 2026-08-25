package il.roboticaxolotl.mithril.armor;

import com.google.common.collect.Maps;
import il.roboticaxolotl.mithril.tags.ModTags;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.Map;

/**
 * CUSTOM ARMOR MATERIAL DEFINITION
 *
 * This file defines the Mithril armor material, which determines:
 *   - Defense points per armor slot (boots, legs, chest, helmet, body)
 *   - Enchantment durability bonus
 *   - Durability multiplier
 *   - Equip/un-equip sound
 *   - Knockback resistance
 *   - Repair ingredient tag
 *   - Which equipment asset (3D armor model) to use
 *
 * ============================================================================
 * RELATIONSHIP TO ARMOR TRIMS:
 * ============================================================================
 *
 * The ArmorMaterial links to an EquipmentAsset key (ModEquipmentAssets.MITHRIL)
 * which tells the game which 3D model to use when the armor is worn.
 *
 * The EquipmentAsset key is ALSO used by the MaterialAssetGroup in ModTrimMaterials
 * to determine which palette suffix to use for the trim overlay on THIS specific
 * armor model. For example, if this armor uses EquipmentAssets.NETHERITE's model,
 * the trim system looks up the "mithril_darker" palette.
 *
 * Since we use ModEquipmentAssets.MITHRIL (our own key), the trim system will use
 * the default "mithril" palette for our armor (not the "mithril_darker" override
 * which is only for vanilla netherite).
 *
 * ============================================================================
 * ARMOR TRIM RENDERING PIPELINE (end-to-end):
 * ============================================================================
 *
 * 1. Player applies trim at smithing table
 *    → DataComponents.TRIM is set on the armor item with the material ResourceKey
 *
 * 2. Game renders the 3D armor on the player model
 *    → Armor renderer uses the equipment asset key to find the 3D model
 *    → TrimMaterialData is looked up from the TrimMaterial registry
 *    → The material's asset group resolves the palette suffix for this equipment asset
 *    → The trim overlay texture is loaded from the armor_trims atlas
 *    → The palette texture recolors the overlay to the material's color
 *
 * 3. Game renders the 2D item in inventory
 *    → Item definition determines the model type:
 *       - "minecraft:select" (vanilla) - only works for 11 hardcoded materials
 *       - "neoforge:trimmed_armor" (NeoForge) - works for ANY material dynamically
 *    → NeoForge's TrimmedArmorModel loads the trim overlay from the items atlas
 *    → The palette texture recolors the overlay to the material's color
 */
public interface ModArmorMaterials {
    // Create the Mithril armor material with stats comparable to Netherite:
    //   - Enchantment value: 40 (high, like netherite)
    //   - Defense: 5 boots, 8 legs, 10 chest, 5 helmet, 21 body
    //   - Durability multiplier: 25 (very durable)
    //   - Sound: netherite equip sound
    //   - Knockback resistance: 5.0F (50% like netherite)
    //   - Toughness: 0.3F
    //   - Repair tag: ModTags.Items.REPAIRS_MITHRIL_ARMOR (mithril ingots)
    //   - Equipment asset: ModEquipmentAssets.MITHRIL (links to our 3D model)
    ArmorMaterial MITHRIL = new ArmorMaterial(
            40, makeDefense(5, 8, 10, 5, 21), 25, SoundEvents.ARMOR_EQUIP_NETHERITE, 5.0F, 0.3F, ModTags.Items.REPAIRS_MITHRIL_ARMOR, ModEquipmentAssets.MITHRIL
    );

    private static Map<ArmorType, Integer> makeDefense(int boots, int legs, int chest, int helm, int body) {
        return Maps.newEnumMap(
                Map.of(ArmorType.BOOTS, boots, ArmorType.LEGGINGS, legs, ArmorType.CHESTPLATE, chest, ArmorType.HELMET, helm, ArmorType.BODY, body)
        );
    }
}
