package ru.job4j.tracker;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import ru.job4j.tracker.actions.*;
import ru.job4j.tracker.input.ConsoleInput;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.input.ValidateInput;
import ru.job4j.tracker.output.ConsoleOutput;
import ru.job4j.tracker.output.Output;
import ru.job4j.tracker.persistence.HbmTracker;
import ru.job4j.tracker.persistence.Store;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StartUI {
    private final Output out;

    public StartUI(Output out) {
        this.out = out;
    }

    @KafkaListener(topics = {"tracker-stats"})
    public void listenStatistic(ConsumerRecord<Integer, String> input) {
        System.out.printf("\n%s%n", input.value());
    }

    public void init(Input input, Store tracker, List<UserAction> actions) {
        boolean run = true;
        while (run) {
            showMenu(actions);
            int select = input.askInt("Select: ");
            if (select < 0 || select >= actions.size()) {
                out.println("Wrong input, you can select: 0 .. " + (actions.size() - 1));
                continue;
            }
            UserAction action = actions.get(select);
            run = action.execute(input, tracker);
        }
    }

    private void showMenu(List<UserAction> actions) {
        out.println("Menu:");
        for (int index = 0; index < actions.size(); index++) {
            out.println(index + ". " + actions.get(index).name());
        }
    }

    public static void main(String[] args) {
        Output output = new ConsoleOutput();
        Input input = new ValidateInput(output, new ConsoleInput());
        Store tracker = new HbmTracker();
        List<UserAction> actions = new ArrayList<>(Arrays.asList(
                new CreateAction(output),
                new FindAllAction(output),
                new EditAction(output),
                new DeleteAction(output),
                new FindItemId(output),
                new FindItemName(output),
                new AddItemsAction(output),
                new DeleteMoreItemsActions(output),
                new ExitAction(output)));
        new StartUI(output).init(input, tracker, actions);
    }
}