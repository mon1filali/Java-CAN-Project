import java.time.LocalDate;
import java.time.LocalTime;

public class main {
    public static void main(String[] args) {      
            System.out.println(LocalDate.now()); // 2025-12-26
            LocalDate var2 = LocalDate.now();
            LocalDate var1 = LocalDate.of(2025, 12, 26);
            System.out.println(var1.isEqual(var2));
            System.out.println(LocalTime.now()); // 11:44:21.670746800
    }
}
