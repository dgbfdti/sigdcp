/**
*    Syst�me Int�gr� de Gestion des D�penses Communes de Personnel - SIGDCP
*
*    Mod�le M�tier
*
**/


package ci.gouv.budget.solde.sigdcp.model.prestation;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter 
@Embeddable @EqualsAndHashCode @AllArgsConstructor @NoArgsConstructor
public class PrestataireDemandeCotationMissionId    implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long demandeCotationMissionId;

	private Long prestataireId;
}