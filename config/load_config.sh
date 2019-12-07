#!/bin/bash
curl -L -o config.zip "$1"
unzip -P "$2" -o config.zip
mv tmdb_release.keystore ./config
rm config.zip