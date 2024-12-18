package ru.itmm.visualcpu.dao;

import ru.itmm.visualcpu.models.commands.Command;

public class BDAO {
    private static CrudDAO<Command> dao = new CommandDao();
    public static CrudDAO<Command> getDAO() { return dao; }
}
