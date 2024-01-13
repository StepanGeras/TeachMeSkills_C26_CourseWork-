

import logger.Logger;
import session.Session;
import util_information.path_check.PathCheck;
import util_information.validation.ValidationCheck;

import java.util.Date;

public class Runner {

    /**
     * Создать программу обработки платежных документов и предоставления финансовой отчетности.
     * +/- Доступ в программу должен осуществляться по логину и паролю.
     * Программа должна получить путь к папке с финансовыми документами, считать информацию из документов и составить статистику.
     * Документы могут быть трех видов: инвойсы, ордеры, чеки.
     * Все документы тхт формата.
     * Каждый вид документа имеет свою собственную стурктуру и собственный шаблон названия.
     * Примеры документов будут предоставлены.
     * Необходимо обрабатывать файлы только за текущий год.
     * Сделать техническое документирование программы по модели C4.
     * Реализовать различные проверки.
     * Реализовать сохранение логов в отдельный файл.
     * Желательно сделать разделение логов: для хранения общей информации и для хранения информации об ошибках.
     * По завершению работы программы все невалидные файлы должны быть перемещены в отдельную папку.
     * Финальная статистика должна быть загружена в отдельный файл.
     * Статистика:
     * 	- общий суммарный оборот за год
     * 	- суммарный оборот по всем инвойсам
     * 	- суммарный оборот по всем ордерам
     * 	- суммарынй оборот по всем чекам
     *
     *
     * Криетрии приемки
     * 	+ Работающая программа.
     * 	+- Чистый и понятный код.
     * 	+- Соблюдение нейминг конвеншн для пакетов, классов, методов, переменных.
     * 	+- Java doc комментарии для сервисов обязательны. Комментарии на английском.
     * 	- Заполненный, краткий и ясный ReadMe файл. Файл должен быть заполен на английском.
     * 	- Весь рабочий код должные находится в ветке master. Количествой другие веток не ограничено.
     * 	- Репозиторий не должен содержать ненужных файлов и папкок (например, idea, target и другие).
     * 	- Присутствует диаграмма классов.
     * 	- Присутсвует диаграмма компонентов(сервисов).
     *
     * Сценарий проверки
     * 	1. Запуск программы
     * 	2. Программа запрашивает креды -> ввод логина и пароля
     * 	3. Программа запрашивает путь к папке -> ввод пути к папке
     * 	4. Программа выполняется и результаты работы программы сохраняются в отдельную папку
     *
     * Дополнительная техническая информация по структуре проекта
     * 	Сервисы:
     * 		- Сервис авторизации.
     * 		- Сервис считывания и обработки файлов.
     *
     * 	Пакеты:
     * 		- классы для описания файлов
     * 		+ классы для записи логов
     * 		+ классы для парсинга файлов
     * 		+ классы для описания сессии
     * 		+ классы с утилитарной информацией
     * 		+ исключения
     */

    public static void main(String[] args) {

        Logger.executionLogger(new Date(), "Start program");

        Session session = ValidationCheck.doValidationCheck();

        PathCheck.pathFileCheck(session);

        Logger.executionLogger(new Date(), "End program");

    }

}
