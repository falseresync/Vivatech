package vivatech.entity;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.util.math.MatrixStack;
import vivatech.VtIds;

public class AutomatonEntityModel extends EntityModel<AutomatonEntity> {
    public static final EntityModelLayer MAIN_LAYER = new EntityModelLayer(VtIds.AUTOMATON, "main");
    private final ModelPart body;

    public AutomatonEntityModel(ModelPart root) {
        this.body = root.getChild(EntityModelPartNames.BODY);
    }

    public static TexturedModelData getTexturedModelData() {
        var modelData = new ModelData();
        var modelPartData = modelData.getRoot();
        modelPartData.addChild(
            EntityModelPartNames.BODY,
            ModelPartBuilder.create()
                .uv(0, 9)
                .cuboid(-3.0F, -4.0F, -3.0F, 6.0F, 8.0F, 6.0F),
            ModelTransform.of(0.0F, 16.0F, 0.0F, 1.5707964F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 32);
    }

    @Override
    public void setAngles(AutomatonEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        body.render(matrices, vertices, light, overlay, red, green, blue, alpha);
    }
}
