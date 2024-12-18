package ru.itmm.visualcpu.models;

import ru.itmm.visualcpu.controllers.IObserver;
import ru.itmm.visualcpu.models.commands.Command;
import ru.itmm.visualcpu.models.commands.Instruction;

import java.util.*;

public class ProgramModel implements Iterable<Command>{
    private List<Command> commands = new ArrayList<>();
    private Set<Integer> memoryAdresses = new HashSet<>();
    private List<IObserver> observers = new ArrayList<>();

    public void addCommand(Command command) {
        if(command.getInstruction() == Instruction.INIT)
            memoryAdresses.add(command.getArgs()[0]);
        else if(command.getInstruction() == Instruction.ST)
            memoryAdresses.add(command.getArgs()[1]);
        commands.add(command);
        eventCall();
    }

    public void removeCommand(int i) {
        Command command = commands.get(i);
        if(command.getInstruction() == Instruction.INIT)
            memoryAdresses.remove(command.getArgs()[0]);
        commands.remove(i);
        eventCall();
    }

    public void reset() {
        commands.clear();
        eventCall();
    }

    public Command getCommand(int i) {
        return commands.get(i);
    }

    public int getCommandCount() {
        return commands.size();
    }

    public void upCommand(int commandPosition) {
        Command current = commands.get(commandPosition);
        Command tmp = commands.get(commandPosition - 1);
        commands.set(commandPosition - 1, current);
        commands.set(commandPosition, tmp);
        eventCall();
    }

    public void downCommand(int commandPosition) {
        Command current = commands.get(commandPosition);
        Command tmp = commands.get(commandPosition + 1);
        commands.set(commandPosition + 1, current);
        commands.set(commandPosition, tmp);
        eventCall();
    }

    public Set<Integer> getMemoryAdresses() {
        return memoryAdresses;
    }

    public Instruction MostPopularInstruction()
    {
        Map<Instruction, Integer> map = new HashMap<>();
        for(Instruction inst : Instruction.values())
            map.put(inst, 0);

        commands.forEach(command ->
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
    }

    public void eventCall() {
        observers.forEach(x -> x.event(this));

    }

    @Override
    public Iterator<Command> iterator() {
        return commands.iterator();
    }
}
