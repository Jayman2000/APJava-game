@echo off

call build
set current_dir=%cd%
cd build
java TestUI
cd %current_dir%