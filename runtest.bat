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



mvn clean antrun:run@ant-execute compile test

