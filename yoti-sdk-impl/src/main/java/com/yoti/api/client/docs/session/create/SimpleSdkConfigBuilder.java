package com.yoti.api.client.docs.session.create;

import com.yoti.api.client.docs.Constants;

public class SimpleSdkConfigBuilder extends SdkConfigBuilder {

    private String allowedCaptureMethods;
    private String primaryColour;
    private String secondaryColour;
    private String fontColour;
    private String locale;
    private String presetIssuingCountry;
    private String successUrl;
    private String errorUrl;

    // FIXME: Do we need to allow them to set ANY allowedCaptureMethods value?

    @Override
    public SdkConfigBuilder withAllowsCamera() {
        this.allowedCaptureMethods = Constants.CAMERA;
        return this;
    }

    @Override
    public SdkConfigBuilder withAllowsCameraAndUpload() {
        this.allowedCaptureMethods = Constants.CAMERA_AND_UPLOAD;
        return this;
    }

    @Override
    public SdkConfigBuilder withPrimaryColour(String primaryColour) {
        this.primaryColour = primaryColour;
        return this;
    }

    @Override
    public SdkConfigBuilder withSecondaryColour(String secondaryColour) {
        this.secondaryColour = secondaryColour;
        return this;
    }

    @Override
    public SdkConfigBuilder withFontColour(String fontColour) {
        this.fontColour = fontColour;
        return this;
    }

    @Override
    public SdkConfigBuilder withLocale(String locale) {
        this.locale = locale;
        return this;
    }

    @Override
    public SdkConfigBuilder withPresetIssuingCountry(String presetIssuingCountry) {
        this.presetIssuingCountry = presetIssuingCountry;
        return this;
    }

    @Override
    public SdkConfigBuilder withSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
        return this;
    }

    @Override
    public SdkConfigBuilder withErrorUrl(String errorUrl) {
        this.errorUrl = errorUrl;
        return this;
    }

    @Override
    public SdkConfig build() {
        return new SimpleSdkConfig(allowedCaptureMethods, primaryColour, secondaryColour, fontColour, locale, presetIssuingCountry, successUrl, errorUrl);
    }
}
