; Script generated by the Inno Setup Script Wizard.
; SEE THE DOCUMENTATION FOR DETAILS ON CREATING INNO SETUP SCRIPT FILES!

#define MyAppName "MeCard Metro Server"
#define MyAppVersion "0.1"
#define MyAppPublisher "Edmonton Public Library"
#define MyAppURL "https://github.com/anisbet/Metro"
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
Source: "C:\Users\ANisbet\Dropbox\development\MeCard\windows\install.bat"; DestDir: "{app}\windows"; Flags: ignoreversion
; NOTE: Don't use "Flags: ignoreversion" on any shared system files
Source: "C:\Users\ANisbet\Dropbox\development\MeCard\dist\MeCard.jar"; DestDir: "{app}\dist"; DestName: "MeCard.jar"
Source: "C:\Users\ANisbet\Dropbox\development\MeCard\dist\README.TXT"; DestDir: "{app}\dist"
Source: "C:\Users\ANisbet\Dropbox\development\MeCard\dist\lib\commons-cli-1.2.jar"; DestDir: "{app}\dist\lib"
Source: "C:\Users\ANisbet\Dropbox\development\MeCard\dist\lib\commons-daemon-1.0.15.jar"; DestDir: "{app}\dist\lib"
Source: "C:\Users\ANisbet\Dropbox\development\MeCard\dist\lib\gson-2.2.4.jar"; DestDir: "{app}\dist\lib"
Source: "C:\Users\ANisbet\Dropbox\development\MeCard\windows\manager.bat"; DestDir: "{app}\windows"
Source: "C:\Users\ANisbet\Dropbox\development\MeCard\windows\prunmgr.exe"; DestDir: "{app}\windows"
Source: "C:\Users\ANisbet\Dropbox\development\MeCard\windows\prunsrv.exe"; DestDir: "{app}\windows"
Source: "C:\Users\ANisbet\Dropbox\development\MeCard\windows\start.bat"; DestDir: "{app}\windows"
Source: "C:\Users\ANisbet\Dropbox\development\MeCard\windows\stop.bat"; DestDir: "{app}\windows"
Source: "C:\Users\ANisbet\Dropbox\development\MeCard\windows\uninstall.bat"; DestDir: "{app}\windows"
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

[Icons]
Name: "{group}\{#MyAppName}"; Filename: "{app}\{#MyAppExeName}"
Name: "{group}\{cm:UninstallProgram,{#MyAppName}}"; Filename: "{uninstallexe}"
Name: "{commondesktop}\{#MyAppName}"; Filename: "{app}\{#MyAppExeName}"; Tasks: desktopicon

[Dirs]
Name: "{app}\logs"
Name: "{app}\dist"
Name: "{app}\dist\lib"
Name: "{app}\windows"
Name: "{app}\logs\Customers"
Name: "{app}\config"
Name: "{app}\bimport"
Name: "{app}\MSSQLClient"

[UninstallRun]
Filename: "{app}\windows\uninstall.bat"; WorkingDir: "{app}"; Flags: shellexec

[UninstallDelete]
Type: filesandordirs; Name: "{app}\*"
