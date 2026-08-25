package il.roboticaxolotl.mithril.datagen;

import il.roboticaxolotl.mithril.MithrilMod;
import il.roboticaxolotl.mithril.armor.ModEquipmentAssets;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

/**
 * EQUIPMENT ASSET DATA GENERATOR
 *
 * This data generator creates the equipment asset JSON file that maps an EquipmentAsset
 * key to the texture layers used for the 3D armor model.
 *
 * Generated file: `assets/mithril/equipment/mithril.json`
 *
 * ============================================================================
 * WHAT THIS FILE DOES:
 * ============================================================================
 *
 * The generated JSON tells the game which texture files to use for each layer of the
 * 3D armor model when an armor piece with our EquipmentAsset key is worn.
 *
 * For example, `addHumanoidLayers(Identifier, boolean)` adds standard humanoid armor
 * layers (outer layer, overlay, etc.) pointing to textures at:
 *   - `assets/mithril/textures/models/armor/mithril_1.png` (layer 1 - boots/leggings)
 *   - `assets/mithril/textures/models/armor/mithril_2.png` (layer 2 - chest/helmet)
 *
 * The boolean parameter (false = no trim overlay layers in the equipment asset itself).
 * Trim overlays are handled separately by the trim rendering system.
 *
 * ============================================================================
 * RELATIONSHIP TO TRIMS:
 * ============================================================================
 *
 * The EquipmentAsset key registered here (ModEquipmentAssets.MITHRIL) is used by:
 *
 * 1. ModArmorMaterials.MITHRIL → Links the armor material to this 3D model
 *
 * 2. ModTrimMaterials.MITHRIL_ASSETS → The trim system uses the equipment asset key
 *    to look up which palette suffix to use. Since our key is "mithril:mithril" (not
 *    in the overrides map), it uses the default suffix "mithril".
 *
 * 3. At render time, the armor renderer:
 *    a) Loads the 3D model using this equipment asset's textures
 *    b) Loads the trim overlay from the armor_trims atlas
 *    c) Recolors the overlay using the palette texture (e.g. trims/color_palettes/mithril.png)
 *    d) Composites the trim overlay on top of the base armor model
 *
 * ============================================================================
 * HOW TO ADD YOUR OWN ARMOR TEXTURES:
 * ============================================================================
 *
 * 1. Create texture files at:
 *    - assets/<modid>/textures/models/armor/<name>_1.png (boots/leggings layer)
 *    - assets/<modid>/textures/models/armor/<name>_2.png (chestplate/helmet layer)
 *
 * 2. These textures follow the standard Minecraft armor model UV layout.
 *    You can base them on vanilla textures (e.g. copy netherite_2.png and recolor).
 */
public class ModEquipmentAssetProvider implements DataProvider {
    private final PackOutput.PathProvider pathProvider;

    public ModEquipmentAssetProvider(PackOutput packProvider) {
        this.pathProvider = packProvider.createPathProvider(PackOutput.Target.RESOURCE_PACK, "equipment");
    }

    private static void bootstrap(BiConsumer<ResourceKey<EquipmentAsset>, EquipmentClientInfo> output) {
        // Register our mithril equipment asset with humanoid armor layers.
        // The Identifier points to the base texture: assets/mithril/textures/models/armor/mithril.png
        // The game appends "_1" and "_2" suffixes for the two armor model layers.
        output.accept(ModEquipmentAssets.MITHRIL, EquipmentClientInfo.builder()
                        .addHumanoidLayers(Identifier.fromNamespaceAndPath(MithrilMod.MOD_ID, "mithril"), false)
                        .addLayers(EquipmentClientInfo.LayerType.HORSE_BODY,
                                new EquipmentClientInfo.Layer(Identifier.fromNamespaceAndPath(MithrilMod.MOD_ID, "mithril")))
                        .addLayers(EquipmentClientInfo.LayerType.NAUTILUS_BODY,
                                new EquipmentClientInfo.Layer(Identifier.fromNamespaceAndPath(MithrilMod.MOD_ID, "mithril")))
                .build());
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        Map<ResourceKey<EquipmentAsset>, EquipmentClientInfo> equipmentAssets = new HashMap<>();
        bootstrap((id, asset) -> {
            if (equipmentAssets.putIfAbsent(id, asset) != null) {
                throw new IllegalStateException("Tried to register equipment asset twice for id: " + id);
            }
        });
        return DataProvider.saveAll(cache, EquipmentClientInfo.CODEC, this.pathProvider::json, equipmentAssets);
    }

    @Override
    public String getName() {
        return "MithrilMod Equipment Definitions";
    }
}
