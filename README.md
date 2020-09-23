# INTRO
Данный проект реализует программный эмулятор GSM модема

Программа написана для версии Java 11

Запуск программы `java -jar torpedo-virtmod.jar`

Программа слушает входящие соединения на 4х портах: 13200, 13201, 13202, 13203

# CHANGELOG
##1.0.0:
Поддерживает реализацию 3х команд для модема Telit:
1. запрос баланса `at+cusd=1,*100#,15`
2. запрос всех новых входящих смс `at+cmgl=0`
3. перезагрузка модема `at#enhrst=1,0`