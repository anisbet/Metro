REM Installs (updates) Service (US) Metro.
c:\metro\windows\prunsrv.exe //US//Metro --Jvm=auto --Startup=auto --StartMode=jvm --Classpath=c:\metro\dist\MeCard.jar --StartClass=mecard.MetroService --StartMethod=start ++StartParams=-c;c:\metro --StopMode=jvm --StopClass=mecard.MetroService --StopMethod=stop --LogPath=c:\metro\logs --LogLevel=Info --LogPrefix=metro --StdOutput=c:\metro\logs\stdout.txt --StdError=c:\metro\logs\stderr.txt
