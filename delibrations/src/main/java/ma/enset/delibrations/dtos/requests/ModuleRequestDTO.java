package ma.enset.delibrations.dtos.requests;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModuleRequestDTO {
    private Long id;
    private String code;
    private String intitule;
    private Long semestreId;
    private Long[] noteModules;

    /*Request form :
    {
      "id" : 1,
      "code":"Java",
      "intitule" : "Module 1",
      "semestreId":2,
      "noteModules":[
              1,
              2
          ]

     }
     */


}
