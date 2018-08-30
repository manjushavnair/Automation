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

mvn -Dmailpass=hari123$  antrun:run@ant-execute clean compile test surefire-report:report

@rem mvn -Dmailpass=hari123$  antrun:run@ant-execute clean compile test surefire-report:report postman:send-mail
