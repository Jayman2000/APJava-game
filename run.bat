@echo off

call build
set current_dir=%cd%
cd build
java Arcade
cd %current_dir%