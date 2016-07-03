package ci.gouv.budget.solde.sigdcp.dao.prestation;

import java.io.Serializable;
import java.util.Collection;

import ci.gouv.budget.solde.sigdcp.dao.traitement.TraitableDaoImpl;
import ci.gouv.budget.solde.sigdcp.model.prestation.DemandeCotationObseque;
import ci.gouv.budget.solde.sigdcp.model.prestation.DemandeCotationTitreTransport;

public class DemandeCotationTitreTransportDaoImpl extends TraitableDaoImpl<DemandeCotationTitreTransport, Long> implements DemandeCotationTitreTransportDao, Serializable {

	private static final long serialVersionUID = -1425013722199012221L;

	@Override
	public Collection<DemandeCotationTitreTransport> readACommander() {
		return entityManager.createQuery("SELECT dct FROM DemandeCotationTitreTransport dct"                    
				+ " WHERE EXISTS ( SELECT pdct FROM PrestataireDemandeCotationObseque pdct WHERE pdct.id.demandeCotationObsequeId=dct.id AND pdct.montantTransport IS NOT NULL AND pdct.montantCercueil IS NOT NULL "
				+ " AND NOT EXISTS ( SELECT cmd FROM CommandeObseque cmd WHERE cmd.prestataireDemandeCotationObseque=pdct) )"
				, clazz)
				.getResultList();
	}

}
 