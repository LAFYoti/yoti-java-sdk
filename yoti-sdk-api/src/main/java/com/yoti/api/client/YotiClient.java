package com.yoti.api.client;

import com.yoti.api.client.aml.AmlProfile;
import com.yoti.api.client.aml.AmlResult;
import com.yoti.api.client.docs.session.create.CreateSessionResult;
import com.yoti.api.client.docs.session.create.SessionSpec;
import com.yoti.api.client.docs.YotiDocsException;
import com.yoti.api.client.docs.session.retrieve.YotiDocsSession;
import com.yoti.api.client.shareurl.DynamicShareException;
import com.yoti.api.client.shareurl.ShareUrlResult;
import com.yoti.api.client.shareurl.DynamicScenario;

/**
 * <p>
 * Entry point to interact with the Yoti Connect API.
 * </p>
 * <p>
 * It can be safely cached and shared even by multiple threads.
 * </p>
 *
 */
public interface YotiClient {

    /**
     * Get the activity details for a token. Amongst others contains the user profile with the user's attributes you
     * have selected in your application configuration on Yoti Portal.
     *
     * <b>Note: encrypted tokens should only be used once. You should not invoke this method multiple times with the same token.</b>
     *
     * @param encryptedYotiToken
     *            encrypted Yoti token (can be only decrypted with your application's private key). Note that this token must only be used once.
     * @return an {@link ActivityDetails} instance holding the user's attributes
     *
     * @throws ProfileException
     *             aggregate exception signalling issues during the call
     */
    ActivityDetails getActivityDetails(String encryptedYotiToken) throws ProfileException;

    /**
     * Request an AML check for the given profile.
     *
     * @param amlProfile
     *            Details of the profile to search for when performing the AML check
     * @return an {@link AmlProfile} with the results of the check
     *
     * @throws AmlException
     *             aggregate exception signalling issues during the call
     */
    AmlResult performAmlCheck(AmlProfile amlProfile) throws AmlException;

    /**
     * Initiate a sharing process based on a dynamic scenario and policy
     *
     * @param  dynamicScenario
     *             Details of the device's callback endpoint, dynamic policy and extensions for the application
     *
     * @return an {@link ShareUrlResult}
     *             sharing url and reference id
     *
     * @throws DynamicShareException
     *             aggregate exception signalling issues during the call
     */
    ShareUrlResult createShareUrl(DynamicScenario dynamicScenario) throws DynamicShareException;

    /**
     * Creates a Yoti Doc Scan session using the supplied session specification
     *
     * @param sessionSpec the Doc Scan session specification
     * @return {@link CreateSessionResult} the session creation result
     * @throws YotiDocsException
     */
    CreateSessionResult createYotiDocsSession(SessionSpec sessionSpec) throws YotiDocsException;

    /**
     * Retrieves the state of a previously created Yoti Doc Scan session
     *
     * @param sessionId the session ID
     * @return {@link YotiDocsSession} the session state
     * @throws YotiDocsException
     */
    YotiDocsSession getYotiDocsSession(String sessionId) throws YotiDocsException;

    /**
     * Deletes a previously created Yoti Doc Scan session and all
     * of its related resources
     *
     * @param sessionId the session ID
     * @throws YotiDocsException
     */
    void deleteYotiDocsSession(String sessionId) throws YotiDocsException;

    /**
     * Retrieves media related to a Yoti Doc Scan session based
     * on the supplied media ID
     *
     * @param sessionId the session ID
     * @param mediaId the media ID
     * @return {@link Media} the media
     * @throws YotiDocsException
     */
    Media getYotiDocsMedia(String sessionId, String mediaId) throws YotiDocsException;

    /**
     * Deletes media related to a Yoti Doc Scan session based
     * on the supplied media ID
     *
     * @param sessionId the session ID
     * @param mediaId the media ID
     * @throws YotiDocsException
     */
    void deleteYotiDocsMedia(String sessionId, String mediaId) throws YotiDocsException;

}
