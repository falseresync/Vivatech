package vivatech.data;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.Identifier;

import static vivatech.util.IdUtil.id;
import static vivatech.util.IdUtil.texId;

public class VivatechIds {
    public static final Identifier AUTOMATA_JOBS = id("automata_jobs");
    public static final Identifier AUTOMATA_PARTS = id("automata_parts");
    public static final Identifier AUTOMATON = id("automaton");
    public static final Identifier NONE = id("none");
    public static final Identifier POSITION_LENS = id("position_lens");

    @Environment(EnvType.CLIENT)
    public static class Client {
        public static final Identifier AUTOMATON_TEX = texId("entity/automaton");
    }
}
