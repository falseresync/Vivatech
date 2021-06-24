package vivatech.registry;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import vivatech.data.VivatechIds;

public class VivatechItems {
    public static final Item POSITION_LENS;

    static {
        POSITION_LENS = register(VivatechIds.POSITION_LENS, new Item(new FabricItemSettings().group(ItemGroup.MISC)));
    }

    public static void init() {}

    private static <I extends Item> I register(Identifier id, I item) {
        return Registry.register(Registry.ITEM, id, item);
    }
}
