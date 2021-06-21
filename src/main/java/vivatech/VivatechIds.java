package vivatech;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.Identifier;

import static vivatech.util.IdUtil.id;
import static vivatech.util.IdUtil.texId;

public class VivatechIds {
    public static final Identifier AUTOMATON = id("automaton");
    public static final Identifier AUTOMATA_PROFESSIONS = id("automata_professions");
    public static final Identifier NONE = id("none");

    @Environment(EnvType.CLIENT)
    public static class Client {
        public static final Identifier AUTOMATON_TEX = texId("entity/automaton");
    }
}
