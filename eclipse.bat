@echo off¡¡¡¡¡¡
set "p=%~p0"
set "p=%p:\= %"
for %%a in (%p%) do set projectName=%%a

call mvn eclipse:clean eclipse:eclipse -U
@pause