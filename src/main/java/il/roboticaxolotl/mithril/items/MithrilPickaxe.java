package il.roboticaxolotl.mithril.items;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class MithrilPickaxe extends Item {
    public MithrilPickaxe(Properties properties) {
        super(properties);
    }

    @Override
    public float getDestroySpeed(ItemStack itemStack, BlockState state) {
        Tool tool = (Tool)itemStack.get(DataComponents.TOOL);

        if (state.getBlock() == Blocks.DEEPSLATE) {
            return tool != null ? 40.f : 1.0F;
        }
        return tool != null ? tool.getMiningSpeed(state) : 1.0F;
    }
}