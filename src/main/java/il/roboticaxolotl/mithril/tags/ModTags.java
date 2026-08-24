package il.roboticaxolotl.mithril.tags;

import il.roboticaxolotl.mithril.MithrilMod;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_MITHRIL_TOOL = CreateTag("need_mithril_tool");
        public static final TagKey<Block> INCORRECT_FOR_MITHRIL_TOOL = CreateTag("incorrect_for_mithril_tool");

        private static TagKey<Block> CreateTag(String name) {
            return BlockTags.create(Identifier.fromNamespaceAndPath(MithrilMod.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> MITHRIL_REPAIRABLE = CreateTag("mithril_repairable");
        public static final TagKey<Item> MITHRIL_TOOL_MATERIALS = CreateTag("mithril_tool_materials");

        private static TagKey<Item> CreateTag(String name) {
            return ItemTags.create(Identifier.fromNamespaceAndPath(MithrilMod.MOD_ID, name));
        }
    }
}
