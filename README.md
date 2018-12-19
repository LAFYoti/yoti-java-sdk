# Yoti Java SDK

Welcome to the Yoti Java SDK. This repo contains the tools and step by step instructions you need to quickly integrate your Java back-end with Yoti so that your users can share their identity details with your application in a secure and trusted way.

## Table of Contents

1) [An Architectural view](#an-architectural-view) -
High level overview of integration

1) [Requirements](#requirements) -
Everything you need to get started

1) [Building From Source](#building-from-source) -
Everything you need to build from source

1) [Enabling the SDK](#enabling-the-sdk) -
Description on importing your SDK

1) [Client Initialisation](#client-initialisation) -
Description on setting up your SDK

1) [Profile Retrieval](#profile-retrieval) -
Description on setting up profile

1) [Handling Users](#handling-users) -
Description on handling user logons

1) [Connectivity Requirements](#connectivity-requirements) -
Description of network connectivity requirements

1) [AML Integration](#aml-integration) -
How to integrate with Yoti's AML (Anti Money Laundering) service

1) [Modules](#modules) -
The Modules above explained

1) [Spring Boot Auto Configuration](#spring-boot-auto-configuration) -
Description of utilising Spring Boot

1) [Running the Example](#running-the-example) -
Running the Spring example project

1) [Breaking changes and enhancements made in v2.0.0](#breaking-changes-and-enhancements-made-in-v2.0.0) -
Things you need to know when migrating from 1.x to 2.x

1) [Spring Security Integration](#spring-security-integration) -
Integrating Yoti Authentication with Spring Boot.

1) [Misc](#misc) -
Miscellaneous items

1) [Known Issues](#known-issues) -
Known issues using the libraries

1) [Support](#support) -
Please feel free to reach out

## An Architectural View

To integrate your application with Yoti, your back-end must expose a GET endpoint that Yoti will use to forward tokens.
The endpoint can be configured in Yoti Dashboard when you create/update your application.

The image below shows how your application back-end and Yoti integrate in the context of a Login flow.
Use the Yoti SDK to complete steps 6 through 9 for you, including profile decryption and communication with backend services.

![alt text](login_flow.png "Login flow")

Yoti also allows you to enable user details verification from your mobile app by means of the Android (TBA) and iOS (TBA) SDKs. In that scenario, your Yoti-enabled mobile app is playing both the role of the browser and the Yoti app. By the way, your back-end doesn't need to handle these cases in a significantly different way. You might just decide to handle the `User-Agent` header in order to provide different responses for web and mobile clients.

## Requirements

* Java 1.7 or higher
* SLF4J

## Building From Source

Building from source is not necessary since artifacts are published in Maven Central. However, if you want to build from source use the [Maven Wrapper](https://github.com/takari/maven-wrapper) bundled with this distribution. For those familiar with Gradle this is much like the Gradle Wrapper and ensures that the correct version of Maven is being used.

From the top level directory:

```bash
./mvnw clean install -Dgpg.skip=true
```

Notable flags that you may wish to use to skip certain static analysis/code quality tools are listed below.

* `-Dfindbugs.skip=true`: skips findbugs and the findbugs security extension.
* `-Ddependency-check.skip=true`: skips the OWASP dependency scanner.

### Example Usage

```bash
./mvnw clean install -Dfindbugs.skip=true -Ddependency-check.skip=true
```

## Enabling the SDK

To include the Yoti SDK in your project, you can use your favourite dependency management system.
If you are using Maven, you need to add the following dependency:

```xml
<dependency>
    <groupId>com.yoti</groupId>
    <artifactId>yoti-sdk-impl</artifactId>
    <version>2.3.0-SNAPSHOT</version>
</dependency>
```

If you are using Gradle, here is the dependency to add:

`compile group: 'com.yoti', name: 'yoti-sdk-impl', version: '2.3.0-SNAPSHOT'`

You will find all classes packaged under `com.yoti.api`

## Client Initialisation

The entry point of the SDK is `com.yoti.api.client.YotiClient`.  To initialise it you need to include the following snippet:

```java
import com.yoti.api.client.YotiClient;
import com.yoti.api.client.YotiClientBuilder;
import static com.yoti.api.client.FileKeyPairSource.fromFile;

YotiClient client = YotiClientBuilder.newInstance()
    .forApplication(<YOUR_CLIENT_SDK_ID>)
    .withKeyPair(fromFile(new java.io.File("<PATH/TO/YOUR/APPLICATION/KEY_PAIR.pem>")))
    .build();
```

Where:

* `YOUR_CLIENT_SDK_ID` is the identifier generated by Yoti Dashboard when you create your app.
* `PATH/TO/YOUR/APPLICATION/KEY_PAIR.pem` is the path to the pem file your browser generates for you, when you create your app on Yoti Dashboard.

## Profile Retrieval

### Callback URl

To allow sharing a Users profile with your application, you must expose a GET endpoint over HTTPS that Yoti will use to push a one time use token to your back end.

You can use any public domain name or localhost, as well as any path or port, but the resulting url must be served over HTTPS. Your endpoint must consume a query string parameter named 'token' - that's the value you will need for retrieving the shared profile.

You configure the callback URL for your application on the Integration page of the Yoti Dashboard.

**Important: these tokens only allow a single use. You cannot retrieve a profile using the same token more than once.**

### ActivityDetails

When your application receives a one time use token from Yoti, you retrieve details of the share with the following:

```java
import com.yoti.api.client.ActivityDetails;

ActivityDetails activityDetails = client.getActivityDetails(encryptedToken);
```

or, for a more detailed example:

```java
import com.yoti.api.client.YotiClient;
import com.yoti.api.client.ActivityDetails;
import com.yoti.api.client.HumanProfile;
import com.yoti.api.client.ProfileException;

try {
    final ActivityDetails activityDetails = client.getActivityDetails(token);
    final HumanProfile profile = activityDetails.getUserProfile();
    //use the profile to extract attributes
} catch (ProfileException e) {
    LOG.info("Could not get profile", e);
    // do something meaningful
}
```

`com.yoti.api.client.ActivityDetails` gives you access to the `com.yoti.api.client.HumanProfile` of the user that has shared with you. 

## Handling Users

### Profiles

User profiles are returned to applications with a user ID unique to that application.  This user ID will be fixed for your app,
so can use it to determine whether a user is new to you.  For example:

```java
ActivityDetails activityDetails;
try {
    activityDetails = client.getActivityDetails(token);
    Optional<YourAppUserClass> user = yourUserSearchMethod(activityDetails.getRememberMeId());
    if (user.isPresent()) {
        String rememberMeId = activityDetails.getRememberMeId();

        Attribute<Image> selfie = profile.getSelfie();
        if (selfie != null) {
            Image selfieValue = selfie.getValue();
            String base64Selfie = selfieValue.getBase64Content();
        }
        Attribute<String> fullName = profile.getFullName();
        Attribute<String> givenNames = profile.getGivenNames();
        Attribute<String> familyName = profile.getFamilyName();
        Attribute<String> phoneNumber = profile.getPhoneNumber();
        Attribute<String> emailAddress = profile.getEmailAddress();
        AgeVerification over18Verification = profile.findAgeOverVerification(18);
        if (over18Verification != null) {
            boolean isAgedOver18 = over18Verification.getResult();
        }
        Attribute<Date> dateOfBirth = profile.getDateOfBirth();
        Attribute<String> gender = profile.getGender();
        Attribute<String> postalAddress = profile.getPostalAddress();
        Attribute<String> nationality = profile.getNationality();
  } else {
      // handle registration
  }
} catch (ProfileException e) {
  LOG.info("Could not get profile", e);
  return "error";
}
```

Where `yourUserSearchMethod` is a method in your app that finds the user for the given rememberMeId.
Regardless of whether the user is new to your app, Yoti will always provide the profile.  So you don't necessarily need to store it.

### Attributes

User details are provided by a list of attributes on the `com.yoti.api.client.HumanProfile` class.  Whether a given attribute is present or not depends on the settings you have applied on Yoti Dashboard.

Attributes are returned as an instance of `com.yoti.api.client.Attribute<T>`.  Since v2.0.0 of the SDK, Attribute is a generic class where <T> represents the type of the Attribute value returned.

### Anchors, Sources and Verifiers

`com.yoti.api.client.Anchor` represents how a given `Attribute<T>` has been _sourced_ or _verified_.  These values are created and signed whenever a Profile Attribute is created, or verified with an external party.

For example, an attribute value that was _sourced_ from a Passport might have the following values:

`Anchor` property | Example value
-----|------
type | SOURCE
value | PASSPORT
subType | OCR
signedTimestamp | 2017-10-31, 19:45:59.123789

Similarly, an attribute _verified_ against the data held by an external party will have an `Anchor` of type _VERIFIER_, naming the party that verified it. 

Attribute Anchors are returned as `List<Anchor>` from `Attribute<T>.getSources` and `Attribute<T>.getVerifiers`.    

## Connectivity Requirements

Using the `com.yoti.api.client.YotiClient` to get `com.yoti.api.client.ActivityDetails` requires your app to establish an outbound TCP connection to port 443 on the Yoti servers at `https://api.yoti.com` (by default - see the [Misc](#misc) section).

By default the Yoti Client will block indefinitely when connecting to the remote server or reading data. Consequently it is **possible that your application thread could be blocked**.

Since version 1.1 of the `yoti-sdk-impl` you can set the following two system properties to control this behaviour:

* `yoti.client.connect.timeout.ms` - the number of milliseconds that you are prepared to wait for the connection to be established. Zero is interpreted as an infinite timeout.
* `yoti.client.read.timeout.ms` - the number of milliseconds that you are prepared to wait for data to become available to read in the response stream. Zero is interpreted as an infinite timeout.

## AML Integration

Yoti provides an AML (Anti Money Laundering) check service to allow a deeper KYC process to prevent fraud. This is a chargeable service, so please contact [sdksupport@yoti.com](mailto:sdksupport@yoti.com) for more information.

Yoti will provide a boolean result on the following checks:

* PEP list - Verify against Politically Exposed Persons list
* Fraud list - Verify against  US Social Security Administration Fraud (SSN Fraud) list
* Watch list - Verify against watch lists from the Office of Foreign Assets Control

To use this functionality you must ensure your application is assigned to your Organisation in the Yoti Dashboard - please see [here](https://www.yoti.com/developers/documentation/#1-creating-an-organisation) for further information.

For the AML check you will need to provide the following:

* Data provided by Yoti (please ensure you have selected the Given name(s) and Family name attributes from the Data tab in the Yoti Dashboard)
  * Given name(s)
  * Family name
* Data that must be collected from the user:
  * Country of residence (must be an ISO 3166 3-letter code)
  * Social Security Number (US citizens only)
  * Postcode/Zip code (US citizens only)

### Consent

Performing an AML check on a person *requires* their consent.
**You must ensure you have user consent *before* using this service.**

### Code Example

Given a YotiClient initialised with your SDK ID and KeyPair (see [Client Initialisation](#client-initialisation)) performing an AML check is a straightforward case of providing basic profile data.

```java
// POJOs for the data to check
AmlAddress amlAddress = new AmlAddressBuilder()
                            .withCountry("GBR")
                            .build();
AmlProfile amlProfile = new AmlProfileBuilder()
                            .withGivenNames("Edward Richard George")
                            .withFamilyName("Heath")
                            .withAddress(amlAddress)
                            .build();

// Perform the check
AmlResult amlResult = client.performAmlCheck(amlProfile);

// Result returned in a POJO
System.out.println(amlResult.isOnFraudList());
System.out.println(amlResult.isOnWatchList());
System.out.println(amlResult.isOnPepList());
```

## Modules

The SDK is split into a number of modules for easier use and future extensibility.

### yoti-sdk-api

Being the only interface, you need to explicitly couple your code to this module. Exposes the core classes:

Class | Description
----- | -----------
HumanProfile  | The set of attributes the user has configured for the transaction.
YotiClientBuilder  | Builds a YotiClient instance by automatically selecting the available implementations on the class path.
YotiClient | Allows your app to retrieve a user profile, given an encrypted token.
KeyPairSource and its implementations | A set of classes responsible for working with different sources (e.g. files, classpath resources, URLs) to load the private/public keypair.

### yoti-sdk-impl

Real SDK implementation that takes care of decrypting the token, fetching the user profile from Yoti servers by issuing a signed request and finally decrypting the fetched profile.

### yoti-sdk-spring-boot-auto-config

A module that can be used in Spring Boot applications to automatically configure the YotiClient and KeyPairSource with standard application properties.

### yoti-sdk-spring-security

A module that can be used in Spring applications that use Spring Security to add Yoti authentication.

## Spring Boot Auto Configuration

As a convenience, if your application happens to use Spring Boot, you can utilise the Spring Boot auto configuration module that will take care of configuring the Yoti Client and Key Pair for you based on standard application properties.

For more information and to see an example of this in use take a look at the [Spring Boot Auto Configuration module](/yoti-sdk-spring-boot-auto-config).

## Running the Example

Instructions on how to run the Spring example project can be found in the [yoti-sdk-spring-boot-example](/yoti-sdk-spring-boot-example) folder.

## Breaking changes and enhancements made in v2.0.0

As well as exposing significant new functionality, we've taken this opportunity to clean up and simplify our public API.

### Dropped support for Java 6
Minimum supported Java version is now 7.

### New interfaces - Attribute<T> and Anchor
The old `com.yoti.api.client.Attribute` class has been replaced with `com.yoti.api.client.Attribute<T>`, which now exposes a `List<Anchor>` for the sources and verifiers of the attribute.
The old `Set<String> getSources()` and `Set<String> getVerifiers()` methods have been replaced with `List<Anchor> getSources()` and `List<Anchor> getVerifiers()`.

### Changes to HumanProfile and ApplicationProfile
All attributes are now returned as an an instance of `Attribute<T>`.  Use the `Attribute<T>.getValue()` method to determine the actual value of an attribute.
Consequently, all the `getXXXSources` and `getXXXVerifiers ` methods have been removed, as well as the `is(name, defaultValue)` helper method

### No Enums returned by the public API
Yoti are adding new functionality all the time.  To avoid the risk the new values returned to the SDK will cause it to break, the SDK will no longer try to map returned values to Enums.  Each SDK release will define String constants in the public api for the possible values known at the time of release. 
The two Enums removed are:
* DocumentType - the `DocumentDetails.getType()` method now returns a String.  The possible values are exposed as constants on the `com.yoti.api.client.DocumentDetails` interface.
* HumanProfile.Gender - `HumanProfile.getGender()` now returns an `Attribute<String>`.  Possible values are defined in `com.yoti.api.client.HumanProfile`.

### Removed toString() methods
We've removed implementations of toString() from all the DTOs returned through the public api.

## Spring Security Integration

If you use Spring Security you can use the `yoti-sdk-spring-security` module to make integration easier. You are provided with some classes that fit into Spring Security's existing authentication model.

Combining this with the Spring Boot Auto Configuration can make integration very easy with very little code needing to be written.

## Misc

* By default, Yoti SDKs fetch profiles from [https://api.yoti.com/api/v1](https://api.yoti.com/api/v1). If necessary, this can be overridden by setting the `yoti.api.url` system property.
* This SDK uses AES-256 encryption. If you are using the Oracle JDK, this key length is not enabled by default. The following stack overflow question explains how to fix this: [http://stackoverflow.com/questions/6481627/java-security-illegal-key-size-or-default-parameters](http://stackoverflow.com/questions/6481627/java-security-illegal-key-size-or-default-parameters)
* To find out how to set up your Java project in order to use this SDK, you can check the Spring Boot example in this repo.
* Windows users - if you see `unmappable character for encoding Cp1252` when running `mvn clean install`, you need to set the default encoding to be UTF-8 before proceeding. This can be done by setting the `JAVA_TOOL_OPTIONS` variable from the Command Prompt: `set JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF8`

## Known Issues

### Age Verification

#### Affects

* Versions prior to 1.4.2

#### Description

A bug in the `isAgeVerified()` property of the profile meant that `isAgeVerified()` would always return null (i.e. not checked).

#### How to fix

Use SDK version 1.4.2 or later.

### Loading Private Keys

#### Affects

* Version 1.1 onwards.

#### Description

There was a known issue with the encoding of RSA private key PEM files that were issued in the past by Yoti Dashboard (most likely where you downloaded the private key for your application).

Some software is more accepting that others and will have been able to cope with the incorrect encoding, whereas some stricter libraries will not accept this encoding.

At version `1.1` of this client the Java Security Provider that we use (`Bouncy Castle`) was [upgraded](https://www.bouncycastle.org/releasenotes.html) from `1.51` -> `1.57`. This upgrade appears to have made the key parser more strict in terms of encoding since it no longer accepts these incorrectly encoded keys.

#### Symptoms

This error usually manifests itself when constructing and instance of the Yoti Client to read the private key.

Generally you'll encounter an exception with an message and stack trace as follows:

```java
com.yoti.api.client.InitialisationException: Cannot load key pair
    at com.yoti.api.client.spi.remote.SecureYotiClient.loadKeyPair(SecureYotiClient.java:99)
    at com.yoti.api.client.spi.remote.SecureYotiClient.<init>(SecureYotiClient.java:73)
    at com.yoti.api.client.spi.remote.SecureYotiClientFactory.getInstance(SecureYotiClientFactory.java:25)
    at com.yoti.api.client.ServiceLocatorYotiClientBuilder.build(ServiceLocatorYotiClientBuilder.java:40)
    at com.yoti.api.spring.YotiClientAutoConfiguration.yotiClient(YotiClientAutoConfiguration.java:48)

Caused by: org.bouncycastle.openssl.PEMException: problem creating RSA private key: java.lang.IllegalArgumentException: failed to construct sequence from byte[]: corrupted stream detected
    at org.bouncycastle.openssl.PEMParser$KeyPairParser.parseObject(Unknown Source)
    at org.bouncycastle.openssl.PEMParser.readObject(Unknown Source)
    at com.yoti.api.client.spi.remote.SecureYotiClient$KeyStreamVisitor.findKeyPair(SecureYotiClient.java:269)
    at com.yoti.api.client.spi.remote.SecureYotiClient$KeyStreamVisitor.accept(SecureYotiClient.java:260)
    at com.yoti.api.spring.SpringResourceKeyPairSource.getFromStream(SpringResourceKeyPairSource.java:28)
    at com.yoti.api.client.spi.remote.SecureYotiClient.loadKeyPair(SecureYotiClient.java:97)
    ... 52 common frames omitted

Caused by: org.bouncycastle.openssl.PEMException: problem creating RSA private key: java.lang.IllegalArgumentException: failed to construct sequence from byte[]: corrupted stream detected
    at org.bouncycastle.openssl.PEMParser$RSAKeyPairParser.parse(Unknown Source)
    ... 58 common frames omitted

Caused by: java.lang.IllegalArgumentException: failed to construct sequence from byte[]: corrupted stream detected
    at org.bouncycastle.asn1.ASN1Sequence.getInstance(Unknown Source)
    ... 59 common frames omitted
```

#### How To Fix

You can re-encode the badly encoded PEM file using some software that is more accepting of the incorrect encoding and saving the new key.

An example of software able to do this is `OpenSSL` versions `1.0.2g` and `1.1.0` using the command:

```bash
openssl rsa -in input-file.pem -out fixed-input-file.pem
```

Using the new (correctly encoded) file should now be compatible with versions 1.1 onwards (as well as older versions like `1.0` prior to this).

## Support

For any questions or support please email [sdksupport@yoti.com](mailto:sdksupport@yoti.com).
Please provide the following to get you up and working as quickly as possible:

* Computer type
* OS version
* Version of Java being used
* Screenshot

Once we have answered your question we may contact you again to discuss Yoti products and services. If you’d prefer us not to do this, please let us know when you e-mail.
