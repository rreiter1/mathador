@REM ----------------------------------------------------------------------------
@REM  Copyright 2001-2006 The Apache Software Foundation.
@REM
@REM  Licensed under the Apache License, Version 2.0 (the "License");
@REM  you may not use this file except in compliance with the License.
@REM  You may obtain a copy of the License at
@REM
@REM       http://www.apache.org/licenses/LICENSE-2.0
@REM
@REM  Unless required by applicable law or agreed to in writing, software
@REM  distributed under the License is distributed on an "AS IS" BASIS,
@REM  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@REM  See the License for the specific language governing permissions and
@REM  limitations under the License.
@REM ----------------------------------------------------------------------------
@REM
@REM   Copyright (c) 2001-2006 The Apache Software Foundation.  All rights
@REM   reserved.

@echo off

set ERROR_CODE=0

:init
@REM Decide how to startup depending on the version of windows

@REM -- Win98ME
if NOT "%OS%"=="Windows_NT" goto Win9xArg

@REM set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" @setlocal

@REM -- 4NT shell
if "%eval[2+2]" == "4" goto 4NTArgs

@REM -- Regular WinNT shell
set CMD_LINE_ARGS=%*
goto WinNTGetScriptDir

@REM The 4NT Shell from jp software
:4NTArgs
set CMD_LINE_ARGS=%$
goto WinNTGetScriptDir

:Win9xArg
@REM Slurp the command line arguments.  This loop allows for an unlimited number
@REM of arguments (up to the command line limit, anyway).
set CMD_LINE_ARGS=
:Win9xApp
if %1a==a goto Win9xGetScriptDir
set CMD_LINE_ARGS=%CMD_LINE_ARGS% %1
shift
goto Win9xApp

:Win9xGetScriptDir
set SAVEDIR=%CD%
%0\
cd %0\..\.. 
set BASEDIR=%CD%
cd %SAVEDIR%
set SAVE_DIR=
goto repoSetup

:WinNTGetScriptDir
set BASEDIR=%~dp0\..

:repoSetup
set REPO=


if "%JAVACMD%"=="" set JAVACMD=java

if "%REPO%"=="" set REPO=%BASEDIR%\repo

set CLASSPATH="%BASEDIR%"\etc;"%REPO%"\commons-cli\commons-cli\1.5.0\commons-cli-1.5.0.jar;"%REPO%"\org\apache\commons\commons-csv\1.9.0\commons-csv-1.9.0.jar;"%REPO%"\gov\adlnet\jxapi\2.0.1\jxapi-2.0.1.jar;"%REPO%"\org\bouncycastle\bcprov-jdk16\1.46\bcprov-jdk16-1.46.jar;"%REPO%"\com\liferay\org\apache\commons\fileupload\com.liferay.org.apache.commons.fileupload\6.2.0.1\com.liferay.org.apache.commons.fileupload-6.2.0.1.jar;"%REPO%"\org\apache\maven\plugins\maven-gpg-plugin\1.6\maven-gpg-plugin-1.6.jar;"%REPO%"\org\apache\maven\maven-plugin-api\2.2.1\maven-plugin-api-2.2.1.jar;"%REPO%"\org\apache\maven\maven-project\2.2.1\maven-project-2.2.1.jar;"%REPO%"\org\apache\maven\maven-settings\2.2.1\maven-settings-2.2.1.jar;"%REPO%"\org\apache\maven\maven-profile\2.2.1\maven-profile-2.2.1.jar;"%REPO%"\org\apache\maven\maven-artifact-manager\2.2.1\maven-artifact-manager-2.2.1.jar;"%REPO%"\org\apache\maven\wagon\wagon-provider-api\1.0-beta-6\wagon-provider-api-1.0-beta-6.jar;"%REPO%"\backport-util-concurrent\backport-util-concurrent\3.1\backport-util-concurrent-3.1.jar;"%REPO%"\org\apache\maven\maven-plugin-registry\2.2.1\maven-plugin-registry-2.2.1.jar;"%REPO%"\org\codehaus\plexus\plexus-interpolation\1.11\plexus-interpolation-1.11.jar;"%REPO%"\org\codehaus\plexus\plexus-container-default\1.0-alpha-9-stable-1\plexus-container-default-1.0-alpha-9-stable-1.jar;"%REPO%"\classworlds\classworlds\1.1-alpha-2\classworlds-1.1-alpha-2.jar;"%REPO%"\org\apache\maven\maven-artifact\2.2.1\maven-artifact-2.2.1.jar;"%REPO%"\org\apache\maven\maven-repository-metadata\2.2.1\maven-repository-metadata-2.2.1.jar;"%REPO%"\org\apache\maven\maven-model\2.2.1\maven-model-2.2.1.jar;"%REPO%"\org\codehaus\plexus\plexus-utils\3.0.20\plexus-utils-3.0.20.jar;"%REPO%"\org\sonatype\plexus\plexus-sec-dispatcher\1.4\plexus-sec-dispatcher-1.4.jar;"%REPO%"\org\sonatype\plexus\plexus-cipher\1.4\plexus-cipher-1.4.jar;"%REPO%"\javax\mail\mail\1.4.7\mail-1.4.7.jar;"%REPO%"\javax\activation\activation\1.1\activation-1.1.jar;"%REPO%"\commons-io\commons-io\2.5\commons-io-2.5.jar;"%REPO%"\com\google\code\gson\gson\2.9.0\gson-2.9.0.jar;"%REPO%"\fr\loria\mathador\0.0.1-SNAPSHOT\mathador-0.0.1-SNAPSHOT.jar

set ENDORSED_DIR=
if NOT "%ENDORSED_DIR%" == "" set CLASSPATH="%BASEDIR%"\%ENDORSED_DIR%\*;%CLASSPATH%

if NOT "%CLASSPATH_PREFIX%" == "" set CLASSPATH=%CLASSPATH_PREFIX%;%CLASSPATH%

@REM Reaching here means variables are defined and arguments have been captured
:endInit

%JAVACMD% %JAVA_OPTS% -Xms1g -Xmx16g -classpath %CLASSPATH% -Dapp.name="run" -Dapp.repo="%REPO%" -Dapp.home="%BASEDIR%" -Dbasedir="%BASEDIR%" fr.loria.mathador.Main %CMD_LINE_ARGS%
if %ERRORLEVEL% NEQ 0 goto error
goto end

:error
if "%OS%"=="Windows_NT" @endlocal
set ERROR_CODE=%ERRORLEVEL%

:end
@REM set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" goto endNT

@REM For old DOS remove the set variables from ENV - we assume they were not set
@REM before we started - at least we don't leave any baggage around
set CMD_LINE_ARGS=
goto postExec

:endNT
@REM If error code is set to 1 then the endlocal was done already in :error.
if %ERROR_CODE% EQU 0 @endlocal


:postExec

if "%FORCE_EXIT_ON_ERROR%" == "on" (
  if %ERROR_CODE% NEQ 0 exit %ERROR_CODE%
)

exit /B %ERROR_CODE%
