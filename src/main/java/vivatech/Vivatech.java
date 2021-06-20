package vivatech;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.registry.Registry;
import vivatech.entity.AutomatonEntity;

public class Vivatech implements ModInitializer {
    public static final String MOD_ID = "vivatech";

    public static final EntityType<AutomatonEntity> AUTOMATON = Registry.register(Registry.ENTITY_TYPE, VtIds.AUTOMATON, FabricEntityTypeBuilder
        .create(SpawnGroup.CREATURE, AutomatonEntity::new)
        .dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
    );

    @Override
    public void onInitialize() {
        FabricDefaultAttributeRegistry.register(AUTOMATON, AutomatonEntity.createMobAttributes());
    }
}
