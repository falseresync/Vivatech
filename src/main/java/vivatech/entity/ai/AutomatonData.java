package vivatech.entity.ai;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.entity.data.TrackedDataHandler;
import net.minecraft.network.PacketByteBuf;
import vivatech.VivatechRegistry;

public record AutomatonData(AutomatonProfession profession) {
    public static final Codec<AutomatonData> CODEC = RecordCodecBuilder.create(instance -> instance
        .group(
            VivatechRegistry.AUTOMATA_PROFESSIONS.fieldOf("Profession").orElse(AutomatonProfession.NONE).forGetter(AutomatonData::profession)
        )
        .apply(instance, AutomatonData::new)
    );
    public static final TrackedDataHandler<AutomatonData> HANDLER = new TrackedDataHandler<>() {
        @Override
        public void write(PacketByteBuf buf, AutomatonData value) {
            buf.writeVarInt(VivatechRegistry.AUTOMATA_PROFESSIONS.getRawId(value.profession()));
        }

        @Override
        public AutomatonData read(PacketByteBuf buf) {
            return new AutomatonData(VivatechRegistry.AUTOMATA_PROFESSIONS.get(buf.readVarInt()));
        }

        @Override
        public AutomatonData copy(AutomatonData value) {
            return value;
        }
    };
}
