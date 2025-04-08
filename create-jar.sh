#!/usr/bin/env bash

./build.sh
if [ -d "dist" ]; then
    rm -r dist
fi
mkdir dist

cd build
jar cfm ../dist/Yours.jar ../src/manifest.txt *.class
cd ..
chmod +x dist/Yours.jar
# Copy the assets
cp -r assets dist/assets
