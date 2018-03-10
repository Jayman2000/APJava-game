#!/bin/bash

if [ ! -d "build" ]; then
    mkdir build
fi

javac -d build $* src/*.java

# Copy the assets
if [ -d "build/assets" ]; then
    rm -r build/assets
fi
cp -r assets build/assets
