package ma.enset.delibrations.dtos.mappers;

import ma.enset.delibrations.dtos.responses.InscriptionpedagoqiqueResponseDTO;
import ma.enset.delibrations.entities.InscriptionPedagogique;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class InscriptionPedagogiqueMapper {
    public InscriptionpedagoqiqueResponseDTO fromInscriptionPedagogique(InscriptionPedagogique inscriptionPedagogique){
        InscriptionpedagoqiqueResponseDTO inscriptionpedagoqiqueResponseDTO = new InscriptionpedagoqiqueResponseDTO();
        BeanUtils.copyProperties(inscriptionPedagogique, inscriptionpedagoqiqueResponseDTO);
        return  inscriptionpedagoqiqueResponseDTO;
    }
}
