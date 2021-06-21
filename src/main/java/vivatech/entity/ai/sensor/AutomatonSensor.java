package vivatech.entity.ai.sensor;

import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.dynamic.GlobalPos;
import vivatech.entity.AutomatonEntity;

import java.util.Set;

public class AutomatonSensor extends Sensor<AutomatonEntity> {
    @Override
    protected void sense(ServerWorld world, AutomatonEntity entity) {
        var pos = entity.getBlockPos();
        var memory = entity.getBrain().getOptionalMemory(MemoryModuleType.JOB_SITE);
        if (memory.isEmpty() || !world.getBlockState(memory.get().getPos()).isIn(BlockTags.GOLD_ORES)) {
            entity.getBrain().forget(MemoryModuleType.JOB_SITE);
            for (var i = -4; i <= 4; i++) {
                for (var j = -1; j <= 1; j++) {
                    for (var k = -4; k <= 4; k++) {
                        var pos2 = pos.add(i, j, k);
                        if (world.getBlockState(pos2).isIn(BlockTags.GOLD_ORES)) {
                            entity.getBrain().remember(MemoryModuleType.JOB_SITE, GlobalPos.create(world.getRegistryKey(), pos2));
                            return;
                        }
                    }
                }
            }
        }
    }

    @Override
    public Set<MemoryModuleType<?>> getOutputMemoryModules() {
        return Set.of(MemoryModuleType.JOB_SITE);
    }
}
