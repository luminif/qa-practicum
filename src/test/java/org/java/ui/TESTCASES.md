## Тесткейсы

### 1. TC1: Создание клиента

#### Предусловие:
1. Перейти на страницу входа: `https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager`.
#### Шаги воспроизведения: 
1. Ввести в поле `Post Code` 10-значное число: `0734567890`.
2. Разбить `Post Code` на двузначные числа: `0734567890` -> [`07`, `34`, `56`, `78`, `90`].
3. Преобразовать каждое двузначное число в букву английского алфавита в порядке от 0 до 25. Если число больше 25, начать с 0 снова:
`07` -> `h`, `34` -> `i`, `56` -> `e`, `78` -> `a`, `90` -> `m`.
4. Объединить буквы, чтобы сформировать `First Name`: `hieam`.
5. Ввести сгенерированный `Post Code` и `First Name` в систему. `Last Name` выбрать любое.
#### Ожидаемый результат: 
Система успешно создает нового клиента с `First Name` `hieam`, `Post Code` `0734567890` и выбранным `Last Name`.

### 2. TC2: Сортировка клиентов по имени (First Name)

#### Предусловие:
1. Перейти на страницу входа: `https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager`.
#### Шаги воспроизведения:
1. Получить список клиентов из системы. Для этого нужно перейти в `Customers`. Пример: [`Hermoine`, `Harry`, `Ron`, `Albus`, `Neville`]
2. Отсортировать список клиентов по `First Name` в алфавитном порядке: два раза нажать на `First Name`.
#### Ожидаемый результат: 
Система отображает список клиентов, отсортированный по `First Name` в алфавитном порядке: [`Albus`, `Harry`, `Hermoine`, `Neville`, `Ron`].

### 3. TC3: Удаление клиента

#### Предусловие:
1. Перейти на страницу входа: `https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager`.
#### Шаги воспроизведения:
1. Получить список клиентов из системы. Для этого нужно перейти в `Customers`: [`Hermoine`, `Harry`, `Ron`, `Albus`, `Neville`]
2. Рассчитать длину каждого имени клиента:
* `Hermoine` - 8 символов
* `Harry` - 5 символов
* `Ron` - 3 символа
* `Albus` - 5 символов
* `Neville` - 7 символов
3. Рассчитать среднюю длину имен: (8 + 5 + 3 + 5 + 7) / 5 = 5.6
4. Найти клиента с именем, длина которого ближе к средней длине: `Harry` (длина 5 символов).
5. Выбрать клиента `Harry` и удалить его.
#### Ожидаемый результат: 
Система удаляет клиента `Harry` из списка.