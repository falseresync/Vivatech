package vivatech.entity.ai.task;

import net.minecraft.entity.ai.brain.MemoryModuleState;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.Task;
import net.minecraft.server.world.ServerWorld;
import vivatech.entity.AutomatonEntity;

import java.util.Map;

public class AutomatonTask extends Task<AutomatonEntity> {
    public AutomatonTask() {
        super(Map.of(MemoryModuleType.JOB_SITE, MemoryModuleState.VALUE_PRESENT));
    }

    @Override
    protected void run(ServerWorld world, AutomatonEntity entity, long time) {
        entity.getBrain().getOptionalMemory(MemoryModuleType.JOB_SITE).ifPresent(globalPos -> {
            world.breakBlock(globalPos.getPos(), true, entity);
        });
    }

    @Override
    protected boolean shouldRun(ServerWorld world, AutomatonEntity entity) {
        return entity.getBrain().isMemoryInState(MemoryModuleType.JOB_SITE, MemoryModuleState.VALUE_PRESENT);
    }
}
