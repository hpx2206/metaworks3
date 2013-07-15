@echo off

set /p name=Enter the name:
set url=url

goto %name%

:cloud_8080
copy hudson\%name%\applicationContext.xml WebContent\WEB-INF\applicationContext.xml
copy hudson\%name%\uengine.properties src\org\uengine\uengine.properties
set url=d:\ProcessCodi_Test/webapps
goto final


:final
erase build_For_Hudson.xml

setlocal enabledelayedexpansion

for /f "tokens=*" %%i in (build_For_Hudson_Form.xml) do (
    set str=%%i
    set str=!str:url=%url%!
    echo !str! >> build_For_Hudson.xml
)

exit

