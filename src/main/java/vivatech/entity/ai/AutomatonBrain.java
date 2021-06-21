package vivatech.entity.ai;

import com.google.common.collect.ImmutableList;
import net.minecraft.entity.ai.brain.Activity;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.task.LookAroundTask;
import net.minecraft.entity.ai.brain.task.StrollTask;
import net.minecraft.entity.ai.brain.task.WanderAroundTask;
import vivatech.entity.AutomatonEntity;
import vivatech.entity.ai.task.AutomatonTask;

import java.util.Set;

public class AutomatonBrain {
    public static Brain<AutomatonEntity> create(Brain<AutomatonEntity> brain) {
        addCoreActivities(brain);
        addIdleActivities(brain);
        brain.setCoreActivities(Set.of(Activity.CORE));
        brain.setDefaultActivity(Activity.IDLE);
        brain.resetPossibleActivities();
        return brain;
    }

    private static void addCoreActivities(Brain<AutomatonEntity> brain) {
        brain.setTaskList(
            Activity.CORE,
            0,
            ImmutableList.of(
                new WanderAroundTask(),
                new AutomatonTask()
            )
        );
    }

    private static void addIdleActivities(Brain<AutomatonEntity> brain) {
        brain.setTaskList(
            Activity.IDLE,
            10,
            ImmutableList.of(
                new LookAroundTask(45, 90),
                new StrollTask(0.15F, 5, 2)
            )
        );
    }

    public static void updateActivities(AutomatonEntity entity) {
        var brain = entity.getBrain();
        var activity = brain.getFirstPossibleNonCoreActivity().orElse(null);
//        if (activity != Activity.PLAY_DEAD) {
//            brain.resetPossibleActivities(List.of(Activity.IDLE));
//            if (activity == Activity.FIGHT && brain.getFirstPossibleNonCoreActivity().orElse(null) != Activity.FIGHT) {
//                brain.remember(MemoryModuleType.HAS_HUNTING_COOLDOWN, true, 2400L);
//            }
//        }

    }
}
