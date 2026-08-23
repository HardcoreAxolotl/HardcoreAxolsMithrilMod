package il.roboticaxolotl.mithril.items;

import il.roboticaxolotl.mithril.MithrilMod;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MithrilMod.MOD_ID);

    public static final DeferredItem<Item> MITHRIL_INGOT = ITEMS.registerSimpleItem("mithril_ingot");
    public static final DeferredItem<Item> MITHRIL_NUGGET = ITEMS.registerSimpleItem("mithril_nugget");
    public static final DeferredItem<Item> RAW_MITHRIL = ITEMS.registerSimpleItem("raw_mithril");
    public static final DeferredItem<Item> ENDER_TEMPLATE = ITEMS.registerSimpleItem("ender_template");

    public static void register(IEventBus event_bus) {
        ITEMS.register(event_bus);
    }
}
