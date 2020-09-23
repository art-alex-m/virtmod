package torpedo.virtmod.models;

import torpedo.virtmod.dto.CommandDto;
import torpedo.virtmod.interfaces.ICommandDto;
import torpedo.virtmod.interfaces.ICommandParser;

public class CommandParser implements ICommandParser {
    @Override
    public ICommandDto create(String command) {
        if (null == command) command = "null";
        return new CommandDto().setCommand(command.toLowerCase().trim());
    }
}
