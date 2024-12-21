package ru.itmm.visualcpu.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.itmm.visualcpu.controllers.IObserver;
import ru.itmm.visualcpu.dao.BDAO;
import ru.itmm.visualcpu.dao.CrudDAO;
import ru.itmm.visualcpu.models.commands.Command;
import ru.itmm.visualcpu.models.commands.Instruction;

import java.util.*;

@Component("program")
@Scope("singleton")
public class ProgramModel implements Iterable<Command>{

    @Autowired
    private CrudDAO<Command> dao;
    //private Set<Integer> memoryAdresses = new HashSet<>(); //возможно придется убрать
    private List<IObserver> observers = new ArrayList<>();

    public ProgramModel() {

    }
//    @Autowired
//    public ProgramModel(CrudDAO<Command> dao) {
//        this.dao = dao;
//    }

    public void addCommand(Command command) {
        dao.add(command);
        eventCall();
    }

    public void removeCommand(int i) {
        Command command = dao.getAll().get(i);
        dao.delete(command.getId());
        eventCall();
    }

    public void reset() {
        dao.deleteAll();
        eventCall();
    }

    public Command getCommand(int i) {
        return dao.getAll().get(i);
    }

    public int getCommandCount() {
        return dao.getAll().size();
    }

    public void upCommand(int commandPosition) {
        Command current = dao.getAll().get(commandPosition);
        int current_id = current.getId();
        current = new Command(current.getInstruction(),
                current.getArgs()[0],
                current.getArgs()[1],
                current_id
        );

        Command tmp = dao.getAll().get(commandPosition - 1);
        current.setId(tmp.getId());
        tmp.setId(current_id);

        dao.update(current);
        dao.update(tmp);
        eventCall();
    }

    public void downCommand(int commandPosition) {
        Command current = dao.getAll().get(commandPosition);
        int current_id = current.getId();
        current = new Command(current.getInstruction(),
                current.getArgs()[0],
                current.getArgs()[1],
                current_id
        );

        Command tmp = dao.getAll().get(commandPosition + 1);
        current.setId(tmp.getId());
        tmp.setId(current_id);

        dao.update(current);
        dao.update(tmp);
        eventCall();
    }

    public Set<Integer> getMemoryAdresses() {
        Set<Integer> address = new HashSet<>();
        dao.getAll().forEach(x -> {
            Instruction inst = x.getInstruction();
            if(inst == Instruction.INIT)
                address.add(x.getArgs()[0]);
            else if (inst == Instruction.ST) {
                address.add(x.getArgs()[1]);
            }
        });
        return address;
    }

    public Instruction MostPopularInstruction()
    {
        Map<Instruction, Integer> map = new HashMap<>();
        for(Instruction inst : Instruction.values())
            map.put(inst, 0);

        dao.getAll().forEach(command ->
                map.put(command.getInstruction(),
                        map.get(command.getInstruction()) + 1
                )
        );
        List<Map.Entry<Instruction, Integer>> entry =
                map.entrySet().stream().toList().stream().sorted(((o1, o2) -> o2.getValue().compareTo(o1.getValue()))).toList();
        return entry.get(0).getKey();
    }

    public void addObserver(IObserver observer) {
        observers.add(observer);
        eventCall();
    }

    public void eventCall() {
        observers.forEach(x -> x.event(this));
    }

    @Override
    public Iterator<Command> iterator() {
        return dao.getAll().iterator();
    }
}
