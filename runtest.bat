cls

echo "Run Test"

cd c:\projects\automation

taskkill /IM chrome.exe /F
taskkill /IM chromedriver.exe /F
taskkill /IM IEDriverServer.exe /F
taskkill /IM firefox.exe /F
taskkill /IM java /F


echo "pulling code from git"

git pull

@rem T 2 for use 2 cores of CPU
@rem dependencyLocationsEnabled all external denendencies are ignored
@rem mailpass password of smtp to sent mail
@rem ant execute target will create directories while build
@rem report will create the test results

mvn -T 2   -DdependencyLocationsEnabled=false -Dmailpass=manjusha  antrun:run@ant-execute clean compile test surefire-report:report


@rem mvn -Dmailpass=hari123$  antrun:run@ant-execute clean compile test surefire-report:report postman:send-mail
