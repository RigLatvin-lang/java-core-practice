import java.util.List;


public class WorkSheetAnalyzer {
    enum DayOfWeek {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY
    };

    static class WorkSheet {
        String name;
        List<DayOfWeek> workDays;

        public WorkSheet(String name, List<DayOfWeek> workDays) {
            this.name = name;
            this.workDays = workDays;
        }


        public String getName() {
            return name;
        }

        public List<DayOfWeek> getWorkDays() {
            return workDays;
        }
        
        @Override public String toString() {
            return "WorkSheet{" +
                    "name='" + name + '\'' +
                    ", workDays=" + workDays +
                    '}';
        }
    }

    void test() {
        var denSheet = new WorkSheet("Денис", List.of(DayOfWeek.TUESDAY, DayOfWeek.THURSDAY, DayOfWeek.SUNDAY));
        var benSheet = new WorkSheet("Бен", List.of(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.SATURDAY));
        var lisSheet = new WorkSheet("Лиза", List.of(DayOfWeek.THURSDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY));
        var sheets = List.of(denSheet, benSheet, lisSheet);
        // TODO: Необходимо вывести на экран дни в которые работало более 1 человека 
        sheets.stream()
            .flatMap(sheet -> sheet.getWorkDays().stream())
            .filter(day -> sheets.stream().filter(s -> s.getWorkDays().contains(day)).count() > 1)
            .distinct()
            .forEach(System.out::println);
    }

    public static void main(String[] args) {
        new WorkSheetAnalyzer().test();
    }
}