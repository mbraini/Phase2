package controller;

import model.ModelRequests;
import model.objectModel.ObjectModel;
import model.objectModel.effects.EffectModel;
import model.objectModel.frameModel.FrameModel;
import view.ViewRequest;

public class ObjectController {
    public synchronized static void removeObject(ObjectModel model) {
        ModelRequests.removeObjectModel(model.getId());
        ViewRequest.removeObjectView(model.getId());
    }

    public synchronized static void removeEffect(EffectModel effectModel) {
        ModelRequests.removeEffectModel(effectModel.getId());
        ViewRequest.removeEffectView(effectModel.getId());
    }

    public static void removeFrame(FrameModel frameModel) {
        ModelRequests.removeFrameModel(frameModel.getId());
        ViewRequest.removeFrameView(frameModel.getId());
    }
}
