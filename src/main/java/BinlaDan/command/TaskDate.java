package BinlaDan.command;

import BinlaDan.tasks.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class TaskDate {
    public LocalDate dateLocalDate;
    public String dateString;

    public TaskDate(String date) {
        this.dateString = date;
        try {
            dateLocalDate = parseDate(date);
            dateString = dateLocalDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            dateLocalDate = null;
        }
    }

    public void setDate(String date) {
        this.dateString = date;
        try {
            dateLocalDate = parseDate(date);
        } catch (DateTimeParseException e) {
            dateLocalDate = null;
        }
    }

    public LocalDate getDateLocalDate() {
        return this.dateLocalDate;
    }

    @Override
    public String toString() {
        return dateString;
    }

    public static LocalDate parseDate(String receivedText) {
        return LocalDate.parse(receivedText);

    }


}
