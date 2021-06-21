package vivatech;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.util.registry.Registry;
import vivatech.entity.ai.AutomatonData;
import vivatech.entity.AutomatonEntity;
import vivatech.entity.ai.sensor.AutomatonSensor;
import vivatech.mixin.SensorTypeAccessor;

import static vivatech.util.IdUtil.id;

public class Vivatech implements ModInitializer {
    public static final String MOD_ID = "vivatech";
    public static final EntityType<AutomatonEntity> AUTOMATON = Registry
        .register(Registry.ENTITY_TYPE, VivatechIds.AUTOMATON, FabricEntityTypeBuilder
            .create(SpawnGroup.CREATURE, AutomatonEntity::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
        );
    public static SensorType<AutomatonSensor> SENSOR;

    @Override
    public void onInitialize() {
        VivatechRegistry.init();
        FabricDefaultAttributeRegistry.register(AUTOMATON, AutomatonEntity.createMobAttributes());
        TrackedDataHandlerRegistry.register(AutomatonData.HANDLER);
        SENSOR = Registry.register(Registry.SENSOR_TYPE, id("sensor"), SensorTypeAccessor.init(AutomatonSensor::new));
    }
}
