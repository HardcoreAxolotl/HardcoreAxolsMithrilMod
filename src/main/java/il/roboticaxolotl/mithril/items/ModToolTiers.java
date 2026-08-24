package il.roboticaxolotl.mithril.items;

import il.roboticaxolotl.mithril.tags.ModTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ToolMaterial;

public class ModToolTiers {
    public static final ToolMaterial MITHRIL = new ToolMaterial(
            ModTags.Blocks.INCORRECT_FOR_MITHRIL_TOOL,
            3062,
            12.0F,
            5.0F,
            22,
            ModTags.Items.MITHRIL_REPAIRABLE
    );
}