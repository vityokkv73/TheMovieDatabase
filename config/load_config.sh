#!/bin/bash
DRIVE_URL=$1
ARCHIVE=$2
echo "$0"
echo "$1"
echo "$2"

echo "______$DRIVE_URL____"
echo "____________$ARCHIVE___________"
curl -L -o config.zip "$1" \
    && unzip -P "$2" -o ./config.zip
echo $PWD
mv config/config.properties ./
mv config/tmdb_release.keystore ./config
rm config.zip