    @echo off  
    cd /d %~dp0  
    if not "%1" == "" cd /d %1  
    echo ��ǰĿ¼:%cd%  
    pause  
    for /f "delims=" %%a in ('dir . /b /ad /s ^|sort /r' ) do rd /q "%%a" 2>nul  