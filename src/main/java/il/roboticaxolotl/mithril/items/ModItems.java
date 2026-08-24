package il.roboticaxolotl.mithril.items;

import il.roboticaxolotl.mithril.MithrilMod;
import il.roboticaxolotl.mithril.tags.ModTags;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MithrilMod.MOD_ID);

    // Basic Items
    public static final DeferredItem<Item> MITHRIL_INGOT = ITEMS.registerSimpleItem("mithril_ingot");
    public static final DeferredItem<Item> MITHRIL_NUGGET = ITEMS.registerSimpleItem("mithril_nugget");
    public static final DeferredItem<Item> RAW_MITHRIL = ITEMS.registerSimpleItem("raw_mithril");

    // Ender Template
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

    // Tools
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


    public static void register(IEventBus event_bus) {
        ITEMS.register(event_bus);
    }
}
