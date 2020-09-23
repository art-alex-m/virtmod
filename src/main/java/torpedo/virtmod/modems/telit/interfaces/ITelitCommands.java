package torpedo.virtmod.modems.telit.interfaces;

/**
 * Интерфейс описавает список доступных для обработки AT комманд эмуляции модема Telit.
 */
public interface ITelitCommands {
    String CMD_ALL_NEW_SMS = "at+cmgl=0"; /// отдать список новых смс
    String CMD_REBOOT_NOW = "at#enhrst=1,0"; /// перезагрузить модем немедленно
    String CMD_GET_BALANCE = "at+cusd=1,*100#,15"; /// запросить баланс *100#
}
