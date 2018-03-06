@echo off

IF NOT EXIST build (
    mkdir build
)

javac -d build src/*.java
