@echo off

IF NOT EXIST build (
    mkdir build
)

javac -d build src/*.java

REM Copy the assets
IF EXIST build/assets (
    rmdir /S /Q build\assets
)

mkdir build\assets
copy assets build\assets\ >NUL
REM send stdout to NUL for a silent copy
