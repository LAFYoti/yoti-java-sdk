package com.yoti.api.client.docs.session.create;

public abstract class SdkConfigBuilder {

    public abstract SdkConfigBuilder withAllowsCamera();

    public abstract SdkConfigBuilder withAllowsCameraAndUpload();

    public abstract SdkConfigBuilder withPrimaryColour(String primaryColour);

    public abstract SdkConfigBuilder withSecondaryColour(String secondaryColour);

    public abstract SdkConfigBuilder withFontColour(String fontColour);

    public abstract SdkConfigBuilder withLocale(String locale);

    public abstract SdkConfigBuilder withPresetIssuingCountry(String presetIssuingCountry);

    public abstract SdkConfigBuilder withSuccessUrl(String successUrl);

    public abstract SdkConfigBuilder withErrorUrl(String errorUrl);

    public abstract SdkConfig build();

}
