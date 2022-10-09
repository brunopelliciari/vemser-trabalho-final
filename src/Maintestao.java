import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Maintestao {

    public static void main(String[] args) {

        String data = "12/2024";

        int num = Integer.parseInt(data.substring(3));
        int num2 = Integer.parseInt(data.substring(0,2));

        System.out.println(num);
        System.out.println(num2);

        // https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/time/format/DateTimeFormatter.html

        // Calculos com Data e Hora

        System.out.println("\nCalculos com Data e Hora\n");

        LocalDate d04 = LocalDate.parse("2022-09-05");
        LocalDateTime d05 = LocalDateTime.parse("2022-09-05T09:10:36");
        Instant d06 = Instant.parse("2022-09-05T01:30:36Z");

        LocalDate pastWeekLocalDate = d04.minusDays(7);
        LocalDate nextWeekLocalDate = d04.plusDays(7);

        System.out.println("pastWeekLocalDate = " + pastWeekLocalDate);
        System.out.println("nextWeekLocalDate = " + nextWeekLocalDate);

        LocalDateTime pastWeekLocalDateTime = d05.minusDays(7);
        LocalDateTime nextWeekLocalDateTime = d05.plusDays(7);
        LocalDateTime pastHourLocalDateTime = d05.minusHours(7);
        LocalDateTime nextHourLocalDateTime = d05.plusHours(7);

        System.out.println("pastWeekLocalDateTime = " + pastWeekLocalDateTime);
        System.out.println("nextWeekLocalDateTime = " + nextWeekLocalDateTime);
        System.out.println("pastHourLocalDateTime = " + pastHourLocalDateTime);
        System.out.println("nextHourLocalDateTime = " + nextHourLocalDateTime);

        Instant pastWeekInstant = d06.minus(7, ChronoUnit.DAYS);
        Instant nextWeekInstant = d06.plus(7, ChronoUnit.DAYS);

        System.out.println("pastWeekInstant = " + pastWeekInstant);
        System.out.println("nextWeekInstant = " + nextWeekInstant);

        Duration d1 = Duration.between(pastWeekLocalDate.atStartOfDay(), nextWeekLocalDate.atStartOfDay());
        Duration d2 = Duration.between(pastWeekLocalDateTime, nextWeekLocalDateTime);
        Duration d3 = Duration.between(pastWeekInstant, d06);
        Duration d4 = Duration.between(d06, pastWeekInstant);

        LocalDate locacao = LocalDate.parse("2022-09-05");
        LocalDate devolucao = LocalDate.parse("2022-09-10");
        Duration duracao = Duration.between(locacao.atStartOfDay(), devolucao.atStartOfDay());

        System.out.println("Duration d1 = " + d1.toDaysPart());
        System.out.println("Duration d2 = " + d2.toDaysPart());
        System.out.println("Duration d3 = " + d3.toDaysPart());
        System.out.println("Duration d4 = " + d4.toDaysPart());
        System.out.println("Duração = " + duracao.toDays());
    }
}
