package vivatech.entity;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import vivatech.VivatechIds;

public class AutomatonEntityRenderer extends MobEntityRenderer<AutomatonEntity, AutomatonEntityModel> {
    public AutomatonEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new AutomatonEntityModel(context.getPart(AutomatonEntityModel.MAIN_LAYER)), 0.5f);
    }

    @Override
    public Identifier getTexture(AutomatonEntity entity) {
        return VivatechIds.Client.AUTOMATON_TEX;
    }
}
