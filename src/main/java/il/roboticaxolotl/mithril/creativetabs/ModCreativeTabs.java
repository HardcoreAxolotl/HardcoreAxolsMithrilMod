package il.roboticaxolotl.mithril.creativetabs;

import il.roboticaxolotl.mithril.MithrilMod;
import il.roboticaxolotl.mithril.blocks.ModBlocks;
import il.roboticaxolotl.mithril.items.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MithrilMod.MOD_ID);

    public static final Supplier<CreativeModeTab> MITHRIL_ITEMS_TAB = CREATIVE_MODE_TABS.register("mithril_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.MITHRIL_INGOT.get()))
                    .title(Component.translatable("creativetab.mithril.name"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.ENDER_TEMPLATE);
                        output.accept(ModItems.MITHRIL_INGOT);
                        output.accept(ModItems.MITHRIL_NUGGET);
                        output.accept(ModItems.RAW_MITHRIL_ALLOY);
                        output.accept(ModItems.MITHRIL_POWDER);
                        output.accept(ModBlocks.MITHRIL_ORE);
                        output.accept(ModBlocks.MITHRIL_BLOCK);
                        output.accept(ModBlocks.RAW_MITHRIL_BLOCK);

                        output.accept(ModItems.MITHRIL_SWORD);
                        output.accept(ModItems.MITHRIL_PICKAXE);
                        output.accept(ModItems.MITHRIL_AXE);
                        output.accept(ModItems.MITHRIL_SHOVEL);
                        output.accept(ModItems.MITHRIL_HOE);
                        output.accept(ModItems.MITHRIL_SPEAR);

                        output.accept(ModItems.MITHRIL_HELMET);
                        output.accept(ModItems.MITHRIL_CHESTPLATE);
                        output.accept(ModItems.MITHRIL_LEGGINGS);
                        output.accept(ModItems.MITHRIL_BOOTS);

                        output.accept(ModItems.MITHRIL_HORSE_ARMOR);
                        output.accept(ModItems.MITHRIL_NAUTILUS_ARMOR);
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
