@ECHO OFF
SETLOCAL

SET "MAVEN_PROJECTBASEDIR=%~dp0"
@REM Remove trailing backslash
IF "%MAVEN_PROJECTBASEDIR:~-1%"=="\" SET "MAVEN_PROJECTBASEDIR=%MAVEN_PROJECTBASEDIR:~0,-1%"

SET "WRAPPER_JAR=%MAVEN_PROJECTBASEDIR%\.mvn\wrapper\maven-wrapper.jar"

@REM Find java.exe
IF DEFINED JAVA_HOME (
  SET "JAVA_CMD=%JAVA_HOME%\bin\java.exe"
  IF NOT EXIST "%JAVA_CMD%" (
    SET "JAVA_CMD=%JAVA_HOME%\bin\java"
  )
) ELSE (
  SET "JAVA_CMD=java"
)

"%JAVA_CMD%" ^
  -Dmaven.multiModuleProjectDirectory="%MAVEN_PROJECTBASEDIR%" ^
  -classpath "%WRAPPER_JAR%" ^
  org.apache.maven.wrapper.MavenWrapperMain %*

ENDLOCAL
