#!/bin/bash

if [ ! -d "build" ]; then
    mkdir build
fi

javac -d build src/*.java
