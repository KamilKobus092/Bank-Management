package pl.kkobusprogramming.bankmanagement.Account.validations;

import pl.kkobusprogramming.bankmanagement.Account.exceptions.InvalidPeselNumberException;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;

public class PeselValidator {

    private byte PESEL[] = new byte[11];

    public PeselValidator() {
    }

    public boolean isAdult(String pesel) {
        if (pesel.length() != 11) {
            throw new InvalidPeselNumberException("Pesel number should consist of 11 digits");
        } else {

            for (int i = 0; i < 11; i++) {
                try {
                    PESEL[i] = Byte.parseByte(pesel.substring(i, i + 1));
                } catch (NumberFormatException ex) {
                    throw new InvalidPeselNumberException("Invalid character. Pesel number should consist of 11 digits");
                }
            }
        }
        LocalDate today = LocalDate.now();
        LocalDate birthday = LocalDate.of(getBirthYear(), Month.of(getBirthMonth()), getBirthDay());

        Period p = Period.between(birthday, today);
        if (p.getYears() >= 18) {
            return true;
        } else {
            throw new InvalidPeselNumberException("Only adults can create an account.");
        }
    }

    private int getBirthYear() {
        int year;
        int month;
        year = 10 * PESEL[0];
        year += PESEL[1];
        month = 10 * PESEL[2];
        month += PESEL[3];
        if (month > 80 && month < 93) {
            year += 1800;
        } else if (month > 0 && month < 13) {
            year += 1900;
        } else if (month > 20 && month < 33) {
            year += 2000;
        } else if (month > 40 && month < 53) {
            year += 2100;
        } else if (month > 60 && month < 73) {
            year += 2200;
        }
        return year;
    }

    private int getBirthMonth() {
        int month;
        month = 10 * PESEL[2];
        month += PESEL[3];
        if (month > 80 && month < 93) {
            month -= 80;
        } else if (month > 20 && month < 33) {
            month -= 20;
        } else if (month > 40 && month < 53) {
            month -= 40;
        } else if (month > 60 && month < 73) {
            month -= 60;
        }
        return month;
    }

    private int getBirthDay() {
        int day;
        day = 10 * PESEL[4];
        day += PESEL[5];
        return day;
    }
}