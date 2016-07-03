/**
*    Syst�me Int�gr� de Gestion des D�penses Communes de Personnel - SIGDCP
*
*    Mod�le M�tier
*
**/


package ci.gouv.budget.solde.sigdcp.model.prestation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;
import ci.gouv.budget.solde.sigdcp.model.dossier.DossierMission;
import ci.gouv.budget.solde.sigdcp.model.traitement.Traitable;
import ci.gouv.budget.solde.sigdcp.model.traitement.TraitementDemandeCotationTitreTransport;

@Getter @Setter 
@Entity
//@Table(name="DEMCOTTRANS")
public class DemandeCotationTitreTransport  extends DemandeCotation  implements Serializable{

	private static final long serialVersionUID = 2701256888036673361L;
	
	@ManyToOne
	private DossierMission dossier;
	
	@Transient private Collection<Prestataire> prestataires=new ArrayList<>() ;
	
	@Embedded 
	private Traitable<TraitementDemandeCotationTitreTransport> traitable = new Traitable<>();
	
	
	public DemandeCotationTitreTransport() {
		// TODO Auto-generated constructor stub
	}
	public DemandeCotationTitreTransport(DossierMission dossier) {
		super();
		this.dossier = dossier;
	}

	public DemandeCotationTitreTransport(DossierMission dossier,	Collection<Prestataire> prestataires) {
		super();
		this.dossier = dossier;
		this.prestataires = prestataires;
	}

	public Long getDossierId(){
		return dossier.getId();
	}

} 