import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Event(String name, LocalDateTime startDate, LocalDateTime endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters
    public String getName()            { return name; }
    public LocalDateTime getStartDate() { return startDate; }
    public LocalDateTime getEndDate()   { return endDate; }


    public String getStatus() {
        LocalDateTime now = LocalDateTime.now();
        if(now.isBefore(startDate)){
            System.out.println("Sắp diễn ra");
        }else if (now.isAfter(endDate)){
            System.out.println("Đã kết thúc");
        }else{
            System.out.println("Đang diễn ra");
        }
        return "";
    }

    @Override
    public String toString() {
        return String.format("Tên: %s%n" + "Bắt đầu: %s%n" + "Kết thúc: %s%n" + "Trạng thái: %s",
                name, startDate.format(FORMATTER), endDate.format(FORMATTER), getStatus());
    }
}