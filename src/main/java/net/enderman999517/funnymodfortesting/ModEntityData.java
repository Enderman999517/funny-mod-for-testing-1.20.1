package net.enderman999517.funnymodfortesting;

public interface ModEntityData {
    boolean isHidden();
    void setHidden(boolean hidden);
    boolean isRenderingOverlay();
    void setRenderingOverlay(boolean renderingOverlay);
    boolean hasRingInOffhand();
    boolean isBeingImpersonated();
    void setBeingImpersonated(boolean beingImpersonated);
    boolean isImpersonating();
    void setImpersonating(boolean impersonating);
    String getCameraTargetEntityUuid();
    void setCameraTargetEntityUuid(String cameraTargetEntityUuid);
    String getCurrentAppliedShader();
    void setCurrentAppliedShader(String clientShader);
    String getPreviousShader();
    void setPreviousShader(String clientShader);
}
