/**
*    Syst�me Int�gr� de Gestion des D�penses Communes de Personnel - SIGDCP
*
*    Mod�le M�tier
*
**/


package ci.gouv.budget.solde.sigdcp.model.traitement;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;
import ci.gouv.budget.solde.sigdcp.model.prestation.DemandeCotationTitreTransport;

@Getter @Setter 
@Entity
@DiscriminatorValue("TDCO")
//@Table(name="TRAITDEMCOTOBS")
public class TraitementDemandeCotationTitreTransport  extends Traitement  implements Serializable{

	private static final long serialVersionUID = 583414085631495905L;
	
	@ManyToOne
	@JoinColumn(name="DEMCOT_TT")
	/*@NotNull*/
	private DemandeCotationTitreTransport demandeCotationTitreTransport;
	
	public TraitementDemandeCotationTitreTransport() {}

	public TraitementDemandeCotationTitreTransport(Operation operation, Statut statut, DemandeCotationTitreTransport demandeCotationTitreTransport) {
		super(operation, statut);
		this.demandeCotationTitreTransport = demandeCotationTitreTransport;
	}

	
	
}