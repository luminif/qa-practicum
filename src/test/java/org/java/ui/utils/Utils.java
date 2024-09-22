package org.java.ui.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Utils {
    private Utils() {

    }

    /**
     * Генерирует имя на основе указанного кода.
     *
     * @param postCode Код, который будет использоваться для генерации имени.
     * @return Сгенерированное имя, состоящее из строчных букв.
     */
    public static String generateFirstName(String postCode) {
        return IntStream.range(0, postCode.length() / 2)
            .mapToObj(i -> postCode.substring(i * 2, i * 2 + 2))
            .map(number -> {
                int num = Integer.parseInt(number);
                int letterIndex = num % 26;
                return Character.toString((char) (letterIndex + 'a'));
            })
            .collect(Collectors.joining());
    }

    public static boolean isSorted(List<String> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).equalsIgnoreCase(list.get(i + 1)) && list.get(i).compareTo(list.get(i + 1)) < 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * Удаляет клиента из списка, имя которого наиболее близко к среднему значению длины имен в списке.
     *
     * @param list Список имен клиентов, которые нужно проверить.
     * @param customerRows Список элементов таблицы, представляющих строки клиентов.
     */
    public static void deleteByAverage(List<String> list, List<WebElement> customerRows) {
        double averageNameLength = list.stream()
            .mapToInt(String::length)
            .average()
            .orElse(0);

        list.stream()
            .min(Comparator.comparingDouble(name -> Math.abs(name.length() - averageNameLength)))
            .flatMap(closestName -> customerRows.stream()
                .filter(row -> row.findElement(By.tagName("td")).getText().equals(closestName))
                .findFirst())
            .ifPresent(row -> {
                WebElement deleteCustomer = row.findElement(By.xpath(".//button[contains(text(), 'Delete')]"));
                deleteCustomer.click();
            });
    }
}
