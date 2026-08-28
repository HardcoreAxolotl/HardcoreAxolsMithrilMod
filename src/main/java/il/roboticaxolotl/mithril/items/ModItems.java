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

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MithrilMod.MOD_ID);

    // =====================================================================
    // BASIC ITEMS
    // =====================================================================

    public static final DeferredItem<Item> MITHRIL_INGOT = ITEMS.registerSimpleItem("mithril_ingot",
            props -> props.fireResistant().trimMaterial(ModTrimMaterials.MITHRIL));
    public static final DeferredItem<Item> MITHRIL_NUGGET = ITEMS.registerSimpleItem("mithril_nugget", Item.Properties::fireResistant);
    public static final DeferredItem<Item> RAW_MITHRIL_ALLOY = ITEMS.registerSimpleItem("raw_mithril_alloy", Item.Properties::fireResistant);
    public static final DeferredItem<Item> MITHRIL_POWDER = ITEMS.registerSimpleItem("mithril_powder", Item.Properties::fireResistant);

    // =====================================================================
    // SMITHING TEMPLATE
    // =====================================================================

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

    public static final DeferredItem<Item> MITHRIL_HORSE_ARMOR = ITEMS.registerItem("mithril_horse_armor",
            properties -> new Item(properties.horseArmor(ModArmorMaterials.MITHRIL).fireResistant()));

    public static final DeferredItem<Item> MITHRIL_NAUTILUS_ARMOR = ITEMS.registerItem("mithril_nautilus_armor",
            properties -> new Item(properties.nautilusArmor(ModArmorMaterials.MITHRIL).fireResistant()));

    public static void register(IEventBus event_bus) {
        ITEMS.register(event_bus);
    }
}
