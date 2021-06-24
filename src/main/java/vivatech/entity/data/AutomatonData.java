package vivatech.entity.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.entity.data.TrackedDataHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import vivatech.data.VivatechKeys;
import vivatech.registry.VivatechRegistry;

import java.util.List;

public record AutomatonData(AutomatonJob job, List<ItemStack> parts) {
    public static final Codec<AutomatonData> CODEC = RecordCodecBuilder.create(instance -> instance
        .group(
            VivatechRegistry.AUTOMATA_JOBS.fieldOf(VivatechKeys.JOB).orElse(AutomatonJob.NONE).forGetter(AutomatonData::job),
            Codec.list(ItemStack.CODEC).fieldOf(VivatechKeys.PARTS).orElse(List.of()).forGetter(AutomatonData::parts)
        )
        .apply(instance, AutomatonData::new)
    );
    public static final TrackedDataHandler<AutomatonData> HANDLER = new TrackedDataHandler<>() {
        @Override
        public void write(PacketByteBuf buf, AutomatonData value) {
            buf.writeVarInt(VivatechRegistry.AUTOMATA_JOBS.getRawId(value.job()));
            buf.writeCollection(value.parts(), PacketByteBuf::writeItemStack);
        }

        @Override
        public AutomatonData read(PacketByteBuf buf) {
            return new AutomatonData(
                VivatechRegistry.AUTOMATA_JOBS.get(buf.readVarInt()),
                buf.readList(PacketByteBuf::readItemStack)
            );
        }

        @Override
        public AutomatonData copy(AutomatonData value) {
            return value;
        }
    };
}
