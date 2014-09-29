; Script generated by the Inno Setup Script Wizard.
; SEE THE DOCUMENTATION FOR DETAILS ON CREATING INNO SETUP SCRIPT FILES!

#define MyAppName "MeCard Metro Server"
#define MyAppVersion "0.9.00_01"
#define MyAppPublisher "Edmonton Public Library"
#define MyAppURL "https://github.com/Edmonton_Public_Library/Metro"
#define MyAppExeName "install.bat"



[Setup]
; NOTE: The value of AppId uniquely identifies this application.
; Do not use the same AppId value in installers for other applications.
; (To generate a new GUID, click Tools | Generate GUID inside the IDE.)
AppId={{F4A645ED-E779-4B7C-978D-8C14FDFEE18B}
AppName={#MyAppName}
AppVersion={#MyAppVersion}
;AppVerName={#MyAppName} {#MyAppVersion}
AppPublisher={#MyAppPublisher}
AppPublisherURL={#MyAppURL}
AppSupportURL={#MyAppURL}
AppUpdatesURL={#MyAppURL}
DefaultDirName=c:\metro
DefaultGroupName={#MyAppName}
LicenseFile=C:\Users\ANisbet\Dropbox\development\MeCard\LICENSE.txt
InfoBeforeFile=C:\Users\ANisbet\Dropbox\development\MeCard\README
OutputBaseFilename=setup
Compression=lzma
SolidCompression=yes
AppCopyright=Edmonton Public Library
InfoAfterFile=C:\Users\ANisbet\Dropbox\development\MeCard\after_install.txt

[Languages]
Name: "english"; MessagesFile: "compiler:Default.isl"

[Tasks]
Name: "desktopicon"; Description: "{cm:CreateDesktopIcon}"; GroupDescription: "{cm:AdditionalIcons}"; Flags: unchecked

[Files]
;Source: "C:\Users\ANisbet\Dropbox\development\MeCard\windows\install.bat"; DestDir: "{app}\windows"; Flags: ignoreversion
; NOTE: Don't use "Flags: ignoreversion" on any shared system files
Source: "C:\Users\ANisbet\Dropbox\development\MeCard\dist\MeCard.jar"; DestDir: "{app}\dist"; DestName: "MeCard.jar"
Source: "C:\Users\ANisbet\Dropbox\development\MeCard\dist\README.TXT"; DestDir: "{app}\dist"
Source: "C:\Users\ANisbet\Dropbox\development\MeCard\dist\lib\commons-cli-1.2.jar"; DestDir: "{app}\dist\lib"
Source: "C:\Users\ANisbet\Dropbox\development\MeCard\dist\lib\commons-daemon-1.0.15.jar"; DestDir: "{app}\dist\lib"
Source: "C:\Users\ANisbet\Dropbox\development\MeCard\dist\lib\gson-2.2.4.jar"; DestDir: "{app}\dist\lib"
Source: "C:\Users\ANisbet\Dropbox\development\MeCard\dist\lib\commons-codec-1.8.jar"; DestDir: "{app}\dist\lib"
Source: "C:\Users\ANisbet\Dropbox\development\MeCard\dist\lib\fluent-hc-4.3.4.jar"; DestDir: "{app}\dist\lib"
Source: "C:\Users\ANisbet\Dropbox\development\MeCard\dist\lib\httpclient-4.3.4.jar"; DestDir: "{app}\dist\lib"
Source: "C:\Users\ANisbet\Dropbox\development\MeCard\dist\lib\commons-logging-1.1.3.jar"; DestDir: "{app}\dist\lib"
Source: "C:\Users\ANisbet\Dropbox\development\MeCard\dist\lib\mysql-connector-java-5.1.31-bin.jar"; DestDir: "{app}\dist\lib"
Source: "C:\Users\ANisbet\Dropbox\development\MeCard\dist\lib\sqljdbc4.jar"; DestDir: "{app}\dist\lib"
Source: "C:\Users\ANisbet\Dropbox\development\MeCard\dist\lib\sqljdbc.jar"; DestDir: "{app}\dist\lib"
; Once Polaris comes on line we need to include this lib for PAPI security.
;Source: "C:\Users\ANisbet\Dropbox\development\MeCard\dist\lib\commons-codec-1.8.jar"; DestDir: "{app}\dist\lib"
;Source: "C:\Users\ANisbet\Dropbox\development\MeCard\windows\manager.bat"; DestDir: "{app}\windows"
Source: "C:\Users\ANisbet\Dropbox\development\MeCard\windows\prunmgr.exe"; DestDir: "{app}\windows"
Source: "C:\Users\ANisbet\Dropbox\development\MeCard\windows\prunsrv.exe"; DestDir: "{app}\windows"
;Source: "C:\Users\ANisbet\Dropbox\development\MeCard\windows\start.bat"; DestDir: "{app}\windows"
;Source: "C:\Users\ANisbet\Dropbox\development\MeCard\windows\stop.bat"; DestDir: "{app}\windows"
;Source: "C:\Users\ANisbet\Dropbox\development\MeCard\windows\uninstall.bat"; DestDir: "{app}\windows"
Source: "C:\Users\ANisbet\Dropbox\development\MeCard\windows\MssqlClient.exe"; DestDir: "{app}\MSSQLClient"
Source: "C:\Users\ANisbet\Dropbox\development\MeCard\windows\bimport\BImport.exe"; DestDir: "{app}\bimport"
Source: "C:\Users\ANisbet\Dropbox\development\MeCard\windows\bimport\DbCore75.dll"; DestDir: "{app}\bimport"
Source: "C:\Users\ANisbet\Dropbox\development\MeCard\windows\bimport\MqDb75.dll"; DestDir: "{app}\bimport"
Source: "C:\Users\ANisbet\Dropbox\development\MeCard\windows\bimport\MqOs275.dll"; DestDir: "{app}\bimport"
Source: "C:\Users\ANisbet\Dropbox\development\MeCard\windows\bimport\MqSys75.dll"; DestDir: "{app}\bimport"
Source: "C:\Users\ANisbet\Dropbox\development\MeCard\config_templates\bimport.properties"; DestDir: "{app}\config"
Source: "C:\Users\ANisbet\Dropbox\development\MeCard\config_templates\city_st.properties"; DestDir: "{app}\config"
Source: "C:\Users\ANisbet\Dropbox\development\MeCard\config_templates\debug.properties"; DestDir: "{app}\config"
Source: "C:\Users\ANisbet\Dropbox\development\MeCard\config_templates\environment.properties"; DestDir: "{app}\config"
Source: "C:\Users\ANisbet\Dropbox\development\MeCard\config_templates\sip2.properties"; DestDir: "{app}\config"
Source: "C:\Users\ANisbet\Dropbox\development\MeCard\config_templates\sysvar.properties"; DestDir: "{app}\config"
;Source: "C:\Users\ANisbet\Dropbox\development\MeCard\windows\bimport\header.txt"; DestDir: "{app}\logs\Customers"; Flags: confirmoverwrite
Source: "C:\Users\ANisbet\Dropbox\development\MeCard\windows\metroschedule.bat"; DestDir: "{app}\windows"
;Source: "C:\Users\ANisbet\Dropbox\development\MeCard\windows\metro-template-header.txt"; DestDir: "{app}\windows"

[Icons]
Name: "{group}\{#MyAppName}"; Filename: "{app}\{#MyAppExeName}"
Name: "{group}\{cm:UninstallProgram,{#MyAppName}}"; Filename: "{uninstallexe}"
Name: "{commondesktop}\{#MyAppName}"; Filename: "{app}\{#MyAppExeName}"; Tasks: desktopicon

[Dirs]
Name: "{app}\logs"; Permissions: authusers-full
Name: "{app}\dist"; Permissions: authusers-full
Name: "{app}\dist\lib"; Permissions: authusers-full
Name: "{app}\windows"; Permissions: authusers-full
Name: "{app}\logs\Customers"; Permissions: authusers-full
Name: "{app}\config"; Permissions: authusers-full
Name: "{app}\bimport"; Permissions: authusers-full
Name: "{app}\MSSQLClient"; Permissions: authusers-full

[UninstallRun]
Filename: "{app}\windows\uninstall.bat"; WorkingDir: "{app}"; Flags: shellexec

[UninstallDelete]
Type: filesandordirs; Name: "{app}\*"

[Code]
// Install batch creation
function CreateInstallBatch(): boolean;
var
  fileName : string;
  filePath : string;
  serverName : string;
  lines : TArrayOfString;
begin
  Result := true;
  fileName := ExpandConstant('{app}\windows\install.bat');
  filePath := ExpandConstant('{app}');
  serverName := ExpandConstant('{#MyAppName}');
  SetArrayLength(lines, 3);
  lines[0] := 'REM Installs (updates) {#MyAppName}.';
  lines[1] := filePath+'\windows\prunsrv.exe //US//Metro --Description="{#MyAppName}" --Jvm=auto --Startup=auto --StartMode=jvm --Classpath='+filePath+'\dist\MeCard.jar --StartClass=mecard.MetroService --StartMethod=start ++StartParams=-c;'+filePath+' --StopMode=jvm --StopClass=mecard.MetroService --StopMethod=stop --LogPath='+filePath+'\logs --LogLevel=Info --LogPrefix=metro --StdOutput='+filePath+'\logs\stdout.txt --StdError='+filePath+'\logs\stderr.txt';
  lines[2] := 'exit';
  Result := SaveStringsToFile(filename,lines,true);
  exit;
end;

// Start batch creation
function CreateStartBatch(): boolean;
var
  fileName : string;
  filePath : string;
  serverName : string;
  lines : TArrayOfString;
begin
  Result := true;
  fileName := ExpandConstant('{app}\windows\start.bat');
  filePath := ExpandConstant('{app}');
  serverName := ExpandConstant('{#MyAppName}');
  SetArrayLength(lines, 3);
  lines[0] := 'REM Starts {#MyAppName}.';
  //ES//Metro --Jvm=auto --Startup=auto --StartMode=jvm --Classpath=c:\metro\dist\MeCard.jar     --StartClass=mecard.MetroService --StartMethod=start ++StartParams=-c;c:\metro\    --StopMode=jvm --StopClass=mecard.MetroService --StopMethod=stop --LogPath=c:\metro\logs --LogLevel=Info     --LogPrefix=metro --StdOutput=c:\metro\logs\stdout.txt --StdError=c:\metro\logs\stderr.txt
  lines[1] := filePath+'\windows\prunsrv.exe //ES//Metro --Jvm=auto --Startup=auto --StartMode=jvm --Classpath='+filePath+'\dist\MeCard.jar --StartClass=mecard.MetroService --StartMethod=start ++StartParams=-c;'+filePath+' --StopMode=jvm --StopClass=mecard.MetroService --StopMethod=stop --LogPath='+filePath+'\logs --LogLevel=Info --LogPrefix=metro --StdOutput='+filePath+'\logs\stdout.txt --StdError='+filePath+'\logs\stderr.txt';
  lines[2] := 'exit';
  Result := SaveStringsToFile(filename,lines,true);
  exit;
end;


// Stop batch creation
function CreateStopBatch(): boolean;
var
  fileName : string;
  filePath : string;
  serverName : string;
  lines : TArrayOfString;
begin
  Result := true;
  fileName := ExpandConstant('{app}\windows\stop.bat');
  filePath := ExpandConstant('{app}');
  serverName := ExpandConstant('{#MyAppName}');
  SetArrayLength(lines, 3);
  lines[0] := 'REM Stops {#MyAppName}.';
  // c:\metro\windows\prunsrv.exe //SS//Metro            --Jvm=auto --Classpath=c:\metro\dist\MeCard.jar     --StopMode=jvm --StopClass=mecard.MetroService --StopMethod=stop
  lines[1] := filePath+'\windows\prunsrv.exe //SS//Metro --Jvm=auto --Classpath='+filePath+'\dist\MeCard.jar --StopMode=jvm --StopClass=mecard.MetroService --StopMethod=stop'; 
  lines[2] := 'exit';
  Result := SaveStringsToFile(filename,lines,true);
  exit;
end;


// Uninstall batch creation
function CreateUninstallBatch(): boolean;
var
  fileName : string;
  filePath : string;
  serverName : string;
  lines : TArrayOfString;
begin
  Result := true;
  fileName := ExpandConstant('{app}\windows\uninstall.bat');
  filePath := ExpandConstant('{app}');
  serverName := ExpandConstant('{#MyAppName}');
  SetArrayLength(lines, 3);
  lines[0] := 'REM Stops {#MyAppName}.';
  // c:\metro\windows\prunsrv.exe //DS//Metro
  lines[1] := filePath+'\windows\prunsrv.exe //DS//Metro'; 
  lines[2] := 'exit';
  Result := SaveStringsToFile(filename,lines,true);
  exit;
end;


// manager batch creation
function CreateManagerBatch(): boolean;
var
  fileName : string;
  filePath : string;
  serverName : string;
  lines : TArrayOfString;
begin
  Result := true;
  fileName := ExpandConstant('{app}\windows\manager.bat');
  filePath := ExpandConstant('{app}');
  serverName := ExpandConstant('{#MyAppName}');
  SetArrayLength(lines, 3);
  lines[0] := 'REM opens, manages {#MyAppName}.';
  // c:\metro\windows\prunmgr.exe //ES//Metro
  lines[1] := filePath+'\windows\prunmgr.exe //ES//Metro'; 
  lines[2] := 'exit';
  Result := SaveStringsToFile(filename,lines,true);
  exit;
end;

// Scheduler batch creation
function CreateScheduleBatch(): boolean;
var
  fileName : string;
  filePath : string;
  serverName : string;
  lines : TArrayOfString;
begin
  Result := true;
  fileName := ExpandConstant('{app}\windows\metroschedule.bat');
  filePath := ExpandConstant('{app}');
  serverName := ExpandConstant('{#MyAppName}');
  SetArrayLength(lines, 3);
  lines[0] := 'REM runs schedule batch bimport load {#MyAppName}.';
  lines[1] := 'start /b java -cp '+filePath+'\dist\MeCard.jar mecard.BImportCustomerLoader -c '+filePath+' -d -U -p'+filePath; 
  lines[2] := 'exit';
  Result := SaveStringsToFile(filename,lines,true);
  exit;
end;

 
procedure CurStepChanged(CurStep: TSetupStep);
begin
  if  CurStep=ssPostInstall then
    begin
         CreateInstallBatch();
         CreateStartBatch();
         CreateStopBatch();
         CreateUninstallBatch();
         CreateManagerBatch();
         CreateScheduleBatch();
    end
end;
