package vivatech.entity;

import com.mojang.serialization.Dynamic;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.server.network.DebugInfoSender;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

import java.util.List;

public class AutomatonEntity extends PathAwareEntity {
    private static final List<? extends SensorType<? extends Sensor<? super AutomatonEntity>>> SENSORS;
    private static final List<? extends MemoryModuleType<?>> MEMORY_MODULES;

    static {
        SENSORS = List.of();
        MEMORY_MODULES = List.of(
            MemoryModuleType.LOOK_TARGET, MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE, MemoryModuleType.PATH,
            MemoryModuleType.WALK_TARGET);
    }

    public AutomatonEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected Brain.Profile<AutomatonEntity> createBrainProfile() {
        return Brain.createProfile(MEMORY_MODULES, SENSORS);
    }

    @Override
    protected Brain<AutomatonEntity> deserializeBrain(Dynamic<?> dynamic) {
        return AutomatonBrain.create(createBrainProfile().deserialize(dynamic));
    }

    @Override
    public Brain<AutomatonEntity> getBrain() {
        //noinspection unchecked
        return (Brain<AutomatonEntity>) super.getBrain();
    }

    @Override
    protected void mobTick() {
        world.getProfiler().push("automatonBrain");
        getBrain().tick((ServerWorld) world, this);
        world.getProfiler().pop();
        world.getProfiler().push("automatonActivityUpdate");
        AutomatonBrain.updateActivities(this);
        world.getProfiler().pop();
    }

    @Override
    protected void sendAiDebugData() {
        super.sendAiDebugData();
        DebugInfoSender.sendBrainDebugData(this);
    }
}
