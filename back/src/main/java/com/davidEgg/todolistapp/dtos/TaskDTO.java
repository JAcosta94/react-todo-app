package com.davidEgg.todolistapp.dtos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.davidEgg.todolistapp.enumerations.Priority;
import com.davidEgg.todolistapp.enumerations.Recurring;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class TaskDTO {
    
    private String title;
    private List<LocalDate> dates;
    private Recurring recurring;
    private String description;
    private Boolean completed;
    private LocalDate deadline;
    private String dateMessage;
    private Priority priority;
    //private User user;        no deberia devolver info del usuario


    public TaskDTO() {
        this.dates = new ArrayList<>();
    }


    public TaskDTO(String title, List<LocalDate> dates, Recurring recurring, String description, Boolean completed,
            LocalDate deadline, String dateMessage, Priority priority) {
        this.title = title;
        this.dates = dates;
        this.recurring = recurring;
        this.description = description;
        this.completed = completed;
        this.deadline = deadline;
        this.dateMessage = dateMessage;
        this.priority = priority;
    }

    

    // 1. El método updateTask modificaría los valores de dates y recurring en el objeto 
    // TaskDTO que recibe como parámetro.
    // 2. Después de actualizar los valores, se llamaría al método setDateMessage con el 
    // resultado de la función generateRecurringMessage() como argumento.
    // 3. La función generateRecurringMessage() trabajaría con los valores actualizados 
    // del objeto TaskDTO para generar el mensaje correspondiente.
    // 4. Finalmente, el mensaje generado se establecería en el atributo dateMessage del 
    // objeto TaskDTO mediante el método setDateMessage.


    //tomar lo mejor de estas 2 y crear un buen metodo       --   DESCOMENTAR  --
    // public String generateRecurringMessage() {
    //     if (recurring == null || selectedDates == null || selectedDates.isEmpty()) {
    //         return "No recurring schedule selected";
    //     }

    //     StringBuilder message = new StringBuilder("Todos los ");
    //     for (int i = 0; i < selectedDates.size(); i++) {
    //         // Agregar el día de la semana correspondiente a cada fecha
    //         message.append(selectedDates.get(i).getDayOfWeek().toString());
    //         if (i < selectedDates.size() - 1) {
    //             message.append(", ");
    //         }
    //     }
    //     return message.toString();
    // }

    // public String getCustomMessage(List<DayOfWeek> daysOfWeek) {
    //     switch (this) {
    //         case DAILY:
    //             return "Todos los días";
    //         case WEEKLY:
    //             return "Todos los " + daysOfWeek.stream()
    //                     .map(DayOfWeek::toString)
    //                     .collect(Collectors.joining(", "));
    //         case MONTHLY:
    //             return "Cada mes";
    //         case YEARLY:
    //             return "Cada año";
    //         case NONE:
    //         default:
    //             return "Sin recurrencia";
    //     }
    // }

    // private Set<DayOfWeek> getUniqueDaysOfWeek(List<LocalDate> dates) {
    //     Set<DayOfWeek> uniqueDaysOfWeek = new HashSet<>();
    //     for (LocalDate date : dates) {
    //         uniqueDaysOfWeek.add(date.getDayOfWeek());
    //     }
    //     return uniqueDaysOfWeek;
    // }
}
