echo "Run Test"

cd c:\projects\automation

taskkill /IM chrome.exe /F

mvn compile test 

