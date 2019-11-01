@echo off
set JLINK_VM_OPTIONS=
set DIR=%~dp0
"%DIR%\java" %JLINK_VM_OPTIONS% -m edu.uab.praise24.p5/edu.uab.praise24.p5.CalendarApp %*
