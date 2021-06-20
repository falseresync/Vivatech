package vivatech.entity;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.brain.Activity;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.MemoryModuleState;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.*;
import net.minecraft.util.math.intprovider.UniformIntProvider;

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
                new LookAroundTask(45, 90),
                new WanderAroundTask()
            )
        );
    }

    private static void addIdleActivities(Brain<AutomatonEntity> brain) {
        brain.setTaskList(
            Activity.IDLE,
            0,
            ImmutableList.of(
                new LookAroundTask(45, 90),
                new WanderAroundTask()
            )
        );
    }

    public static void updateActivities(AutomatonEntity entity) {
        Brain<AutomatonEntity> brain = entity.getBrain();
        Activity activity = brain.getFirstPossibleNonCoreActivity().orElse(null);
//        if (activity != Activity.PLAY_DEAD) {
//            brain.resetPossibleActivities(List.of(Activity.IDLE));
//            if (activity == Activity.FIGHT && brain.getFirstPossibleNonCoreActivity().orElse(null) != Activity.FIGHT) {
//                brain.remember(MemoryModuleType.HAS_HUNTING_COOLDOWN, true, 2400L);
//            }
//        }

    }
}
