
package municipales.tramite.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.json.bind.annotation.JsonbDateFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author Dios
 */

@Data
@AllArgsConstructor
@NoArgsConstructor 

public class VariacionesDTO {
    private Long id;
    private Integer grupo;
    private String descripcion;
    private boolean estado;
    @JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date fechaRegistro;
    private TramitesTiposDTO tramites;
    private List<RequisitosDTO> requisitos;
    
    @Override
    public String toString(){
        String est = "Inactivo";
        if(estado){
            est = "Artivo";
        }
        
        return "Id: "+String.valueOf(id)+"\n"+"Grupo de exclusión: "+String.valueOf(grupo)+"\n"+
               "Estado: "+est+"\n"+"Fecha de registro: "+fechaRegistro.toString();
    }
}
