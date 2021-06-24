package vivatech.registry;

import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.fabric.api.event.registry.RegistryAttribute;
import net.minecraft.util.registry.Registry;
import vivatech.data.VivatechIds;
import vivatech.entity.data.AutomatonJob;

public class VivatechRegistry {
    public static final Registry<AutomatonJob> AUTOMATA_JOBS;
    public static final Registry<AutomatonPart> AUTOMATA_PARTS;

    static {
        AUTOMATA_JOBS = FabricRegistryBuilder
            .createDefaulted(AutomatonJob.class, VivatechIds.AUTOMATA_JOBS, VivatechIds.NONE)
            .attribute(RegistryAttribute.SYNCED)
            .buildAndRegister();
        AUTOMATA_PARTS = FabricRegistryBuilder
            .createSimple(AutomatonPart.class, VivatechIds.AUTOMATA_PARTS)
            .attribute(RegistryAttribute.SYNCED)
            .buildAndRegister();
    }

    public static void init() {}
}
