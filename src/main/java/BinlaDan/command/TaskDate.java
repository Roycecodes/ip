package BinlaDan.command;

import BinlaDan.tasks.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

/**
 * represents date of a task
 * holds date as string and as LocalDate type
 * hold methods necessary to manipulate task Date
 */
public class TaskDate {
    public LocalDate dateLocalDate;
    public String dateString;

    /**
     * creates a new instance of TaskDate
     * saves string input as dateString
     * tries to parse String input into LocalDate type
     * else saves dateLocalDate as null
     *
     * @param date is the date that is to be instantiated
     */
    public TaskDate(String date) {
        this.dateString = date;
        try {
            dateLocalDate = parseDate(date);
            dateString = dateLocalDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            dateLocalDate = null;
        }
    }

    /**
     * sets a new date for taskDate
     *
     * @param date is the date that is to be instantiated
     */
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
