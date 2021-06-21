package vivatech.entity;

import com.mojang.serialization.Dynamic;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtOps;
import net.minecraft.server.network.DebugInfoSender;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import vivatech.Vivatech;
import vivatech.entity.ai.AutomatonBrain;
import vivatech.entity.ai.AutomatonData;
import vivatech.entity.ai.AutomatonProfession;

import java.util.List;

public class AutomatonEntity extends PathAwareEntity {
    private static final List<? extends SensorType<? extends Sensor<? super AutomatonEntity>>> SENSORS;
    private static final List<? extends MemoryModuleType<?>> MEMORY_MODULES;
    private static final TrackedData<AutomatonData> AUTOMATON_DATA;

    static {
        SENSORS = List.of(Vivatech.SENSOR);
        MEMORY_MODULES = List.of(
            MemoryModuleType.LOOK_TARGET, MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE, MemoryModuleType.PATH,
            MemoryModuleType.WALK_TARGET);
        AUTOMATON_DATA = DataTracker.registerData(AutomatonEntity.class, AutomatonData.HANDLER);
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
        super.mobTick();
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

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        dataTracker.startTracking(AUTOMATON_DATA, new AutomatonData(AutomatonProfession.NONE));
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        AutomatonData.CODEC
            .encodeStart(NbtOps.INSTANCE, dataTracker.get(AUTOMATON_DATA))
            .resultOrPartial(LOGGER::error)
            .ifPresent(element -> nbt.put("AutomatonData", element));
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains("AutomatonData", NbtElement.COMPOUND_TYPE)) {
            AutomatonData.CODEC
                .parse(new Dynamic<>(NbtOps.INSTANCE, nbt.get("AutomatonData")))
                .resultOrPartial(LOGGER::error)
                .ifPresent(data -> dataTracker.set(AUTOMATON_DATA, data));
        }
    }
}
