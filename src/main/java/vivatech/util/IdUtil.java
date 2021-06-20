package vivatech.util;

import net.minecraft.util.Identifier;
import vivatech.Vivatech;

public class IdUtil {
    public static Identifier id(String id) {
        return new Identifier(Vivatech.MOD_ID, id);
    }

    public static Identifier texId(String id) {
        return id("textures/" + id + ".png");
    }
}
