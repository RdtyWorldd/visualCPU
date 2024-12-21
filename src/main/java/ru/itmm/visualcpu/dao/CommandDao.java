package ru.itmm.visualcpu.dao;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.itmm.visualcpu.models.commands.Command;
import ru.itmm.visualcpu.models.commands.Instruction;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

@Component("command_dao")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CommandDao implements CrudDAO<Command> {

    private Connection connection=null;
    private List<Command> commads = new ArrayList<>();

    public CommandDao() {
        connect();
        updateList();
    }

    @Override
    public void add(Command command) {
        try {
            //SQL
            PreparedStatement pst = connection.prepareStatement(
                    "INSERT INTO AllCommand(prog_id, instruction, arg1, arg2) VALUES (?,?,?,?)");
            pst.setInt(1, 1);
            pst.setString(2, command.getInstruction().name());
            if(command.getArgs() == null) {
                pst.setInt(3,-1);
                pst.setInt(4, -1);
            } else {
                pst.setInt(3,command.getArgs()[0]);
                pst.setInt(4, command.getArgs()[1]);
            }
            pst.executeUpdate();
            updateList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Command command) {
        delete(command.getId());
    }

    @Override
    public void update(Command command) {
        try {
            //SQL
            PreparedStatement pst = connection.prepareStatement(
                    "UPDATE AllCommand SET instruction = ?, arg1 = ?, arg2 = ? WHERE id = ?;");
            pst.setString(1, command.getInstruction().name());
            if(command.getArgs() == null) {
                pst.setInt(2,-1);
                pst.setInt(3, -1);
            } else {
                pst.setInt(2,command.getArgs()[0]);
                pst.setInt(3, command.getArgs()[1]);
            }
            pst.setInt(4, command.getId());
            pst.executeUpdate();
            updateList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try {
            //SQL
            PreparedStatement pst = connection.prepareStatement(
                    "DELETE FROM AllCommand * WHERE id = ?");
            pst.setInt(1, id);
            pst.executeUpdate();
            updateList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateAll(List<Command> list) {
        deleteAll();
        list.forEach(this::add);

    }

    @Override
    public void deleteAll() {
        try {
            //SQL
            PreparedStatement pst = connection.prepareStatement(
                    "DELETE FROM AllCommand *");
            pst.executeUpdate();
            updateList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Command> getAll() {
        return commads;
    }

    private void connect() {
        try(InputStream in = new FileInputStream("src/main/resources/postgres.properties")) {
            Properties properties = new Properties();
            properties.load(in);
            String url = properties.getProperty("url");
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, properties);
        }catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void updateList() {
        commads.clear();
        try {
            Statement st = connection.createStatement();
            ResultSet r = st.executeQuery(
                    "select * from AllCommand");
            while (r.next()) {
                commads.add(new Command(
                        Instruction.valueOf(r.getString("instruction")),
                        r.getInt("arg1"),
                        r.getInt("arg2"),
                        r.getInt("id")
                ));
            }
            Collections.sort(commads, (o2, o1) -> {
                return o2.getId() - o1.getId();
            });
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
