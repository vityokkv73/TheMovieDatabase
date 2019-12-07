#!/bin/bash

curl curl -L -o config.zip "$DRIVE_URL" --output - \
    && unzip -P "$ARCHIVE" config.zip \
    && mv config/config.properties ./ \
    && mv config/tmdb_release.keystore ./config \
    && rm config.zip