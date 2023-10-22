import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        final String fileLogName = "log.txt";
        String[] autoMarks  = new String[] {"Audi", "Mercedes", "BMW", "Toyota", "Lada", "JAC", "Tank",
                "Zeekr", "Mitsubishi", "Lamborghini"};
        ArrayList<String> autoMarksArray = new ArrayList<>(List.of(autoMarks));

        String[] autoModels  = new String[] {"A6", "A7", "M5", "M4", "Granta", "J7", "500",
                "001", "Lancer", "Urus"};
        ArrayList<String> autoModelsArray = new ArrayList<>(List.of(autoModels));

        String[] trailerMarks  = new String[] {"Креон", "Русич", "Swift", "Славич", "PRESTIGE", "Исток", "МЗСА",
                "Krone", "Koegel", "Wielton"};
        ArrayList<String> trailerMarksArray = new ArrayList<>(List.of(trailerMarks));

        String[] trailerModels  = new String[] {"1800", "203", "BaseCamp", "817701", "253Д", "81013", "T3-13/K",
                "B4-13V/VK", "SSL 35", "ТЦ-15"};
        ArrayList<String> trailerModelsArray = new ArrayList<>(List.of(trailerModels));

        CreateCollections maker = new CreateCollections(autoMarksArray, autoModelsArray,
                trailerMarksArray, trailerModelsArray);

        Data model = new Data(new ArrayList<IUseAuto>(), new LinkedList<IUseAuto>(),
                new HashMap<Integer, IUseAuto>(), maker);
        DataView view = new DataView();
        DataController controller = new DataController(model, view);

        ErrCntLog errorsAndLog = new ErrCntLog(new Log());

        Scanner sc = new Scanner(System.in);
        controller.println("Программа для анализа времени выполнения работы с ArrayList, LinkedList и HashMap.");
        controller.println("Для какого объёма данных выполнить тест?");
        controller.println("Доступны следующие варианты:");
        controller.println("\nID \t Кол-во элементов в коллекции");
        for(int i = 0; i < 5; i++)
            controller.println((i + 1) + "\t" + (int)(Math.pow(10, i+1)));
        controller.print("Введите ID: ");
        int length = (int) Math.pow(10, controller.inputLimitedInt(sc, 1, 5));
        controller.println("Создаём коллекции размером " + length + " элементов...");
        controller.createData(length);
        controller.println("Готово! Пишем логи...");

        long addTotalTimeArrayList = 0;
        long removeTotalTimeArrayList = 0;
        long addTotalTimeLinkedList = 0;
        long removeTotalTimeLinkedList = 0;
        long putTotalTimeHashMap = 0;
        long removeTotalTimeHashMap = 0;

        try(FileWriter file = new FileWriter(fileLogName, true)) {
            controller.startProgramLog(errorsAndLog, file);

            controller.collectionNameLog(errorsAndLog, file, "ArrayList");
            // ArrayList add
            for (int i = 0; i < length; i++) {
                long currentTime = controller.addVehicleToArrayList(controller.createAuto());
                controller.log(errorsAndLog, file, "add", i, currentTime);
                addTotalTimeArrayList += currentTime;
            }
            controller.InfoAddLog(errorsAndLog, file, length, addTotalTimeArrayList, addTotalTimeArrayList / length);
            // ArrayList remove
            for (int i = 0; i < length*0.1; i++) {
                long currentTime = controller.removeVehicleFromArrayList();
                controller.log(errorsAndLog, file, "remove", i, currentTime);
                removeTotalTimeArrayList += currentTime;
            }
            controller.InfoRemoveLog(errorsAndLog, file, (int)(length * 0.1), removeTotalTimeArrayList, removeTotalTimeArrayList / (int)(length*0.1));

            controller.collectionNameLog(errorsAndLog, file, "LinkedList");
            // LinkedList add
            for (int i = 0; i < length; i++) {
                long currentTime = controller.addVehicleToLinkedList(controller.createAuto());
                controller.log(errorsAndLog, file, "add", i, currentTime);
                addTotalTimeLinkedList += currentTime;
            }
            controller.InfoAddLog(errorsAndLog, file, length, addTotalTimeLinkedList, addTotalTimeLinkedList / length);
            // LinkedList remove
            for (int i = 0; i < length*0.1; i++) {
                long currentTime = controller.removeVehicleFromLinkedList();
                controller.log(errorsAndLog, file, "remove", i, currentTime);
                removeTotalTimeLinkedList += currentTime;
            }
            controller.InfoRemoveLog(errorsAndLog, file, (int)(length * 0.1), removeTotalTimeLinkedList, removeTotalTimeLinkedList / (int)(length*0.1));

            controller.collectionNameLog(errorsAndLog, file, "HashMap");
            // HashMap put
            for (int i = 0; i < length; i++) {
                long currentTime = controller.addVehicleToHashMap(controller.createAuto());
                controller.log(errorsAndLog, file, "put", i, currentTime);
                putTotalTimeHashMap += currentTime;
            }
            controller.InfoAddLog(errorsAndLog, file, length, putTotalTimeHashMap, putTotalTimeHashMap / length);
            // HashMap remove
            for (int i = 0; i < length*0.1; i++) {
                long currentTime = controller.removeVehicleFromHashMap();
                controller.log(errorsAndLog, file, "remove", i, currentTime);
                removeTotalTimeHashMap += currentTime;
            }
            controller.InfoRemoveLog(errorsAndLog, file, (int)(length * 0.1), removeTotalTimeHashMap, removeTotalTimeHashMap / (int)(length*0.1));

            controller.finishProgramLog(errorsAndLog, file);
            controller.println("Всё! Проверяйте лог файл.");

        }catch (IOException exc){
            controller.println("Ошибка открытия файла для логирования!");
            controller.println("Выход из программы.");
        }
        controller.println("Хотите провести исследование на изменение времени\nдобавления элемента в ArrayList " +
                "при увеличении его размера?");
        controller.print("Введите 1 (да) или 0 (нет): ");

        int research = controller.inputLimitedInt(sc, 0, 1);
        long time = 0;
        if (research == 1){
            int cntToAdd = 10;
            int currentCapacity = 10;
            for(int i = 0; i < cntToAdd; i++){
                time = controller.addVehicleToResearchArrayList(controller.createAuto());
            }
            controller.println("Добавление элемента в ArrayList без расширения памяти (" +
                    cntToAdd + "-й): " + time);

            int cnt = 0;
            while (cnt < 30){
                cntToAdd = (cntToAdd * 3) / 2 + 1 - cntToAdd;
                for(int i = 0; i < cntToAdd; i++){
                    time = controller.addVehicleToResearchArrayList(controller.createAuto());
                    if (i == 1) {
                        controller.println("Добавление элемента в ArrayList c расширением памяти (" +
                                (currentCapacity + 1) + "-й): " + time);
                        cnt += 1;
                    }
                }

                currentCapacity = (currentCapacity * 3) / 2 + 1;
                controller.println("Добавление элемента в ArrayList без расширения памяти (" +
                        currentCapacity + "-й): " + time);
            }

        }else{
            controller.println("Хорошо! Исследование выполнено не будет!");
        }
        controller.println("Выход из программы.");

    }
}