package vivatech;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import vivatech.entity.AutomatonEntityModel;
import vivatech.entity.AutomatonEntityRenderer;

@Environment(EnvType.CLIENT)
public class VivatechClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.INSTANCE.register(Vivatech.AUTOMATON, AutomatonEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(AutomatonEntityModel.MAIN_LAYER, AutomatonEntityModel::getTexturedModelData);
    }
}
