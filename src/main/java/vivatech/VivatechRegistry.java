package vivatech;

import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.fabric.api.event.registry.RegistryAttribute;
import net.minecraft.util.registry.Registry;
import vivatech.entity.ai.AutomatonProfession;

public class VivatechRegistry {
    public static final Registry<AutomatonProfession> AUTOMATA_PROFESSIONS = FabricRegistryBuilder
        .createDefaulted(AutomatonProfession.class, VivatechIds.AUTOMATA_PROFESSIONS, VivatechIds.NONE)
        .attribute(RegistryAttribute.SYNCED)
        .buildAndRegister();

    public static void init() {}
}
