module protune {
    requires javafx.controls;
    requires javafx.media;
    requires java.desktop;
    requires jaudiotagger;
    requires software.amazon.awssdk.auth;
    requires software.amazon.awssdk.services.s3;
    requires software.amazon.awssdk.regions;
    requires software.amazon.awssdk.awscore;
    requires aws.java.sdk.core;

    opens protune.model;
    opens protune;
    opens protune.view;
}