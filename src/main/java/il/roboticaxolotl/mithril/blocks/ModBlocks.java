package il.roboticaxolotl.mithril.blocks;

import il.roboticaxolotl.mithril.MithrilMod;
import il.roboticaxolotl.mithril.items.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MithrilMod.MOD_ID);

    public static final DeferredBlock<Block> MITHRIL_ORE = registerBlock("mithril_ore",
            properties -> new Block(properties
                    .strength(60.f)
                    .explosionResistance(1500.f)
                    .requiresCorrectToolForDrops().sound(SoundType.STONE)
            )
    );

    public static final DeferredBlock<Block> MITHRIL_BLOCK = registerBlock("mithril_block",
            properties -> new Block(properties
                    .strength(60.f)
                    .explosionResistance(1500.f)
                    .requiresCorrectToolForDrops().sound(SoundType.NETHERITE_BLOCK)
            )
    );

    public static final DeferredBlock<Block> RAW_MITHRIL_BLOCK = registerBlock("raw_mithril_block",
            properties -> new Block(properties
                    .strength(50.f)
                    .explosionResistance(1200.f)
                    .requiresCorrectToolForDrops().sound(SoundType.STONE)
            )
    );

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.registerItem(name, properties -> new BlockItem(block.get(), properties.useBlockDescriptionPrefix()));
    }

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, T> function) {
        DeferredBlock<T> to_return = BLOCKS.registerBlock(name, function);
        registerBlockItem(name, to_return);
        return to_return;
    }

    public static void register(IEventBus event_bus) {
        BLOCKS.register(event_bus);
    }
}
