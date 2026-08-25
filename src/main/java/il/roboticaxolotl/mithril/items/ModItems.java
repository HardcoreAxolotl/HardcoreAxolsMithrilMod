package il.roboticaxolotl.mithril.items;

import il.roboticaxolotl.mithril.MithrilMod;
import il.roboticaxolotl.mithril.armor.ModArmorMaterials;
import il.roboticaxolotl.mithril.armor.ModTrimMaterials;
import il.roboticaxolotl.mithril.items.custom.MithrilPickaxe;
import il.roboticaxolotl.mithril.items.custom.MithrilUpgradeSmithingTemplate;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.*;
import net.minecraft.world.item.equipment.ArmorType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

/**
 * ITEM REGISTRATION
 *
 * Registers all mod items with NeoForge's deferred register system.
 *
 * ============================================================================
 * THE MOST CRITICAL LINE FOR TRIM MATERIALS:
 * ============================================================================
 *
 * The mithril_ingot registration includes `.trimMaterial(ModTrimMaterials.MITHRIL)`.
 * This adds the DataComponents.TRIM_MATERIAL component to the item, which tells
 * the smithing table "this item can be used as a trim material."
 *
 * WITHOUT this component:
 *   - The smithing table recipe silently fails (no output)
 *   - Even though the item is in the #minecraft:trim_materials tag
 *   - The tag alone is NOT sufficient — vanilla checks the data component directly
 *
 * WITH this component:
 *   - The smithing table recognizes the item as a valid trim ingredient
 *   - The TRIM_MATERIAL component stores the ResourceKey pointing to our TrimMaterial
 *   - The recipe resolves correctly and applies the trim to the armor
 *
 * ============================================================================
 * WHY .trimMaterial() WORKS:
 * ============================================================================
 *
 * The `Item.Properties.trimMaterial(ResourceKey<TrimMaterial>)` method sets:
 *   DataComponents.TRIM_MATERIAL → the ResourceKey for our trim material
 *
 * When a player places a smithing template + armor + mithril ingot in the smithing table:
 *   1. The recipe checks if the addition ingredient has TRIM_MATERIAL component
 *   2. It reads the ResourceKey from the component
 *   3. It looks up the TrimMaterial in the registry
 *   4. It applies the trim to the armor with the resolved material
 *
 * ============================================================================
 * ARMOR ITEM REGISTRATION:
 * ============================================================================
 *
 * Mithril armor uses `properties.humanoidArmor(material, armorType)` which:
 *   - Sets the Equippable component (determines slot, sound, etc.)
 *   - Links to our ArmorMaterial (ModArmorMaterials.MITHRIL)
 *   - The ArmorMaterial links to our EquipmentAsset (ModEquipmentAssets.MITHRIL)
 *   - The EquipmentAsset links to our 3D armor model textures
 *
 * ============================================================================
 * COMPLETE TRIM MATERIAL FILE DEPENDENCY CHAIN:
 * ============================================================================
 *
 * ModTrimMaterials.MITHRIL (ResourceKey)
 *     ↓ referenced by
 * ModItems.MITHRIL_INGOT (.trimMaterial())
 *     ↓ generates
 * data/mithril/trim_material/mithril.json (via ModDatapackProvider)
 *     ↓ loaded at runtime
 * TrimMaterial registry entry (available for smithing table recipes)
 *
 * ModTrimMaterials.MITHRIL_ASSETS (MaterialAssetGroup)
 *     ↓ resolves palette suffixes for each EquipmentAsset
 * Palette textures: assets/mithril/textures/trims/color_palettes/mithril.png
 *     ↓ discovered by
 * NeoForge's neoforge:directory_paletted_permutations in armor_trims atlas
 *     ↓ used by
 * TrimmedArmorModel (3D worn) / ItemModelGenerators (2D inventory)
 */
public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MithrilMod.MOD_ID);

    // =====================================================================
    // BASIC ITEMS
    // =====================================================================

    /**
     * MITHRIL INGOT - The trim material item.
     *
     * .trimMaterial(ModTrimMaterials.MITHRIL) adds the TRIM_MATERIAL data component.
     * This is THE critical registration that makes the smithing table recognize this
     * item as a valid trim ingredient.
     *
     * Without .trimMaterial(), the smithing table recipe returns empty even if the
     * item is tagged as #minecraft:trim_materials. Vanilla checks the data component,
     * not the tag, for recipe validation.
     *
     * .fireResistant() is separate and just means the item won't burn in lava.
     */
    public static final DeferredItem<Item> MITHRIL_INGOT = ITEMS.registerSimpleItem("mithril_ingot",
            props -> props.fireResistant().trimMaterial(ModTrimMaterials.MITHRIL));
    public static final DeferredItem<Item> MITHRIL_NUGGET = ITEMS.registerSimpleItem("mithril_nugget", Item.Properties::fireResistant);
    public static final DeferredItem<Item> RAW_MITHRIL = ITEMS.registerSimpleItem("raw_mithril", Item.Properties::fireResistant);

    // =====================================================================
    // SMITHING TEMPLATE
    // =====================================================================

    /**
     * MITHRIL UPGRADE SMITHING TEMPLATE
     *
     * Used in the smithing table to upgrade netherite tools/armor to mithril.
     * This is separate from the trim template — you need a trim template to apply trims.
     */
    public static final DeferredItem<Item> ENDER_TEMPLATE = ITEMS.registerItem("mithril_upgrade_smithing_template",
            properties -> new MithrilUpgradeSmithingTemplate(
                    Component.translatable("item.mithril.smithing_template.refining.applies_to"),
                    Component.translatable("item.mithril.smithing_template.refining.ingredients"),
                    Component.translatable("item.mithril.smithing_template.refining.base_slot_description"),
                    Component.translatable("item.mithril.smithing_template.refining.additions_slot_description"),
                    List.of(
                            Identifier.withDefaultNamespace("container/slot/helmet"),
                            Identifier.withDefaultNamespace("container/slot/chestplate"),
                            Identifier.withDefaultNamespace("container/slot/leggings"),
                            Identifier.withDefaultNamespace("container/slot/boots"),
                            Identifier.withDefaultNamespace("container/slot/sword"),
                            Identifier.withDefaultNamespace("container/slot/pickaxe"),
                            Identifier.withDefaultNamespace("container/slot/axe"),
                            Identifier.withDefaultNamespace("container/slot/shovel"),
                            Identifier.withDefaultNamespace("container/slot/hoe")
                    ),
                    List.of(Identifier.withDefaultNamespace("container/slot/ingot")),
                    properties.rarity(Rarity.RARE)
            )
    );

    // =====================================================================
    // TOOLS
    // =====================================================================

    public static final DeferredItem<Item> MITHRIL_SWORD = ITEMS.registerItem("mithril_sword",
            properties -> new Item(properties.sword(ModToolTiers.MITHRIL, 3.0F, -2.4F).fireResistant()));

    public static final DeferredItem<Item> MITHRIL_PICKAXE = ITEMS.registerItem("mithril_pickaxe",
            properties -> new MithrilPickaxe(properties.pickaxe(ModToolTiers.MITHRIL, 1.0F, -2.8F).fireResistant()));

    public static final DeferredItem<Item> MITHRIL_AXE = ITEMS.registerItem("mithril_axe",
            properties -> new AxeItem(ModToolTiers.MITHRIL, 6.0F, -3.0F, properties.fireResistant()));

    public static final DeferredItem<Item> MITHRIL_SHOVEL = ITEMS.registerItem("mithril_shovel",
            properties -> new ShovelItem(ModToolTiers.MITHRIL, 1.5F, -3.0F, properties.fireResistant()));

    public static final DeferredItem<Item>  MITHRIL_HOE = ITEMS.registerItem("mithril_hoe",
            properties -> new HoeItem(ModToolTiers.MITHRIL, -4.0F, 0.0F, properties.fireResistant()));

    public static final DeferredItem<Item> MITHRIL_SPEAR = ITEMS.registerItem("mithril_spear",
            properties -> new Item(properties.spear(ModToolTiers.MITHRIL, 1.15F, 1.2F, 0.4F, 2.5F, 9.0F, 5.5F, 5.1F, 8.75F, 4.6F).fireResistant()));

    // =====================================================================
    // ARMOR
    // =====================================================================

    /**
     * MITHRIL ARMOR ITEMS
     *
     * Uses `properties.humanoidArmor(material, armorType)` which:
     *   - Sets the Equippable component (slot, equip sound, etc.)
     *   - Links to ModArmorMaterials.MITHRIL (our ArmorMaterial)
     *   - The ArmorMaterial references ModEquipmentAssets.MITHRIL (our 3D model key)
     *
     * Note: The TRIM_MATERIAL component is NOT set on armor items.
     * Trim materials are only set on ingot items (see MITHRIL_INGOT above).
     * Armor items receive trims via the smithing table recipe, not via their properties.
     */
    public static final DeferredItem<Item> MITHRIL_HELMET = ITEMS.registerItem("mithril_helmet",
            properties -> new Item(properties.humanoidArmor(ModArmorMaterials.MITHRIL, ArmorType.HELMET).fireResistant()));
    public static final DeferredItem<Item> MITHRIL_CHESTPLATE = ITEMS.registerItem("mithril_chestplate",
            properties -> new Item(properties.humanoidArmor(ModArmorMaterials.MITHRIL, ArmorType.CHESTPLATE).fireResistant()));
    public static final DeferredItem<Item> MITHRIL_LEGGINGS = ITEMS.registerItem("mithril_leggings",
            properties -> new Item(properties.humanoidArmor(ModArmorMaterials.MITHRIL, ArmorType.LEGGINGS).fireResistant()));
    public static final DeferredItem<Item> MITHRIL_BOOTS = ITEMS.registerItem("mithril_boots",
            properties -> new Item(properties.humanoidArmor(ModArmorMaterials.MITHRIL, ArmorType.BOOTS).fireResistant()));

    public static void register(IEventBus event_bus) {
        ITEMS.register(event_bus);
    }
}
