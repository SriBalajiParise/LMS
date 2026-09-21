@echo off
echo ====================================
echo  Library Management System
echo  Compilation Script
echo ====================================
echo.

echo Compiling Java files...
javac Main.java models/*.java services/*.java controllers/*.java datastructures/*.java ui/*.java utils/*.java

if %ERRORLEVEL% EQU 0 (
    echo.
    echo ✓ Compilation successful!
    echo.
    echo Starting Library Management System...
    echo ====================================
    echo.
    java Main
) else (
    echo.
    echo ✗ Compilation failed!
    echo Please check for errors above.
)

echo.
pause
