package com.yoti.api.client;

/**
 * Attribute value that holds an image such as a selfie or an application logo.
 *
 */
public interface Image extends Media {

    /**
     * Get mime type of the content.
     *
     * @return mime type
     */
    @Override
    String getMimeType();

    /**
     * Image content.
     *
     * @return image as byte[]
     */
    @Override
    byte[] getContent();

    /**
     * Base64 encoded image
     *
     * @return image as Base64 encoded String
     */
    @Override
    String getBase64Content();

}
