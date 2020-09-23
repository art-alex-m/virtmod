package torpedo.virtmod.dto;

import torpedo.virtmod.interfaces.ICommandDto;

public class CommandDto implements ICommandDto {
    private String command;

    @Override
    public String getCommand() {
        return command;
    }

    @Override
    public ICommandDto setCommand(String command) {
        this.command = command;
        return this;
    }

    public int getHash() {
        return this.command.hashCode();
    }
}
