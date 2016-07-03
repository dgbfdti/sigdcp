/**
*    Syst�me Int�gr� de Gestion des D�penses Communes de Personnel - SIGDCP
*
*    Mod�le M�tier
*
**/


package ci.gouv.budget.solde.sigdcp.model.prestation;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;
import ci.gouv.budget.solde.sigdcp.model.AbstractModel;
import ci.gouv.budget.solde.sigdcp.model.dossier.DossierMission;
import ci.gouv.budget.solde.sigdcp.model.fichier.Fichier;

@Getter @Setter 
@Entity
@Table(name="PRESTDEMCOTOBS")
public class PrestataireDemandeCotationTitreTransport extends AbstractModel<PrestataireDemandeCotationTitreTransportId> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private PrestataireDemandeCotationTitreTransportId id;
	
	@Transient private DossierMission dossier;
	@Transient private Prestataire prestataire;
	
	/**
	 * Réponse
	 */
	private BigDecimal montantTransport;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Fichier fichier;
	@Temporal(TemporalType.DATE)
	@Column(name="DAT")
	private Date date;
	
	public PrestataireDemandeCotationTitreTransport() {}

	public PrestataireDemandeCotationTitreTransport(PrestataireDemandeCotationTitreTransportId id, DossierMission dossier, Prestataire prestataire) {
		super();
		this.id = id;
		this.dossier = dossier;
		this.prestataire = prestataire;
	}

	public PrestataireDemandeCotationTitreTransport(PrestataireDemandeCotationTitreTransportId id, Fichier fichier, Date date) {
		super();
		this.id = id;
		this.fichier = fichier;
		this.date = date;
	}
	
	
	
}