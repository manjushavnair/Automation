echo "Run Test"

cd c:\projects\automation

taskkill /IM chrome.exe /F
taskkill /IM chromedriver.exe /F

mvn clean antrun:run@ant-execute compile test

