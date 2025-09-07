package ru.netology;

import ru.netology.service.InMemoryTodoService;
import ru.netology.service.TodoService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TodoService service = new InMemoryTodoService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            System.out.print("Ваш выбор: ");
            String raw = scanner.nextLine().trim();

            int choice;
            try {
                choice = Integer.parseInt(raw);
            } catch (NumberFormatException e) {
                System.out.println("Введите номер пункта меню.");
                continue;
            }

            switch (choice) {
                case 0:
                    System.out.println("Выход из программы");
                    scanner.close();
                    return;
                case 1:
                    System.out.print("\nВведите название задачи: ");
                    String title = scanner.nextLine();
                    boolean added = service.add(title);
                    System.out.println(added ? "Добавлено!" : "Такое дело уже есть. Не добавлено.");
                    printList(service);
                    break;
                case 2:
                    printList(service);
                    break;
                case 3:
                    System.out.print("\nВведите номер для удаления: ");
                    String numRaw = scanner.nextLine().trim();
                    try {
                        int oneBased = Integer.parseInt(numRaw);
                        boolean ok = service.deleteByNumber(oneBased);
                        System.out.println(ok ? "Удалено!" : "Нет дела с таким номером.");
                    } catch (NumberFormatException e) {
                        System.out.println("Номер должен быть целым числом.");
                    }
                    printList(service);
                    break;
                case 4:
                    System.out.print("\nВведите задачу для удаления: ");
                    String task = scanner.nextLine();
                    boolean ok = service.deleteByExactText(task);
                    System.out.println(ok ? "Удалено!" : "Дело с таким текстом не найдено.");
                    printList(service);
                    break;
                case 5:
                    System.out.print("\nВведите ключевое слово для удаления: ");
                    String keyword = scanner.nextLine();
                    int removed = service.deleteByKeyword(keyword);
                    if (removed > 0) {
                        System.out.println("Удалено: " + removed);
                    } else {
                        System.out.println("Задач с таким словом не найдено.");
                    }
                    printList(service);
                    break;
                default:
                    System.out.println("Неизвестная операция.");
            }
        }
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("Выберите операцию:");
        System.out.println("0. Выход из программы");
        System.out.println("1. Добавить дело");
        System.out.println("2. Показать дела");
        System.out.println("3. Удалить дело по номеру");
        System.out.println("4. Удалить дело по названию");
        System.out.println("5. Удалить дела по ключевому слову");
    }

    private static void printList(TodoService service) {
        List<String> all = service.list();
        System.out.println("Ваш список дел:");
        if (all.isEmpty()) {
            System.out.println("(пусто)");
            return;
        }
        for (int i = 0; i < all.size(); i++) {
            System.out.println((i + 1) + ". " + all.get(i));
        }
    }
}
