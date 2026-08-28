package il.roboticaxolotl.mithril.armor;

import il.roboticaxolotl.mithril.MithrilMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.minecraft.world.item.equipment.trim.MaterialAssetGroup;
import net.minecraft.world.item.equipment.trim.TrimMaterial;
import java.util.Map;

public class ModTrimMaterials {

    public static final ResourceKey<TrimMaterial> MITHRIL = ResourceKey.create(
            Registries.TRIM_MATERIAL,
            Identifier.fromNamespaceAndPath(MithrilMod.MOD_ID, "mithril")
    );

    public static final MaterialAssetGroup MITHRIL_ASSETS = MaterialAssetGroup.create(
            "mithril",
            Map.of(EquipmentAssets.NETHERITE, "mithril_darker")
    );

    public static void bootstrap(BootstrapContext<TrimMaterial> context) {
        context.register(MITHRIL, new TrimMaterial(
                MITHRIL_ASSETS,
                Component.translatable("trim_material.mithril.mithril")
        ));
    }
}
