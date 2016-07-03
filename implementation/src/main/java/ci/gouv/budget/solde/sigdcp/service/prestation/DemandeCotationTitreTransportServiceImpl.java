package ci.gouv.budget.solde.sigdcp.service.prestation;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import ci.gouv.budget.solde.sigdcp.dao.dossier.DossierMissionDao;
import ci.gouv.budget.solde.sigdcp.dao.dossier.PieceJustificativeDao;
import ci.gouv.budget.solde.sigdcp.dao.prestation.DemandeCotationTitreTransportDao;
import ci.gouv.budget.solde.sigdcp.dao.prestation.PrestataireDemandeCotationTitreTransportDao;
import ci.gouv.budget.solde.sigdcp.dao.traitement.OperationValidationConfigDao;
import ci.gouv.budget.solde.sigdcp.model.Code;
import ci.gouv.budget.solde.sigdcp.model.dossier.DossierMission;
import ci.gouv.budget.solde.sigdcp.model.prestation.DemandeCotationTitreTransport;
import ci.gouv.budget.solde.sigdcp.model.prestation.Prestataire;
import ci.gouv.budget.solde.sigdcp.model.prestation.PrestataireDemandeCotationTitreTransport;
import ci.gouv.budget.solde.sigdcp.model.prestation.PrestataireDemandeCotationTitreTransportId;
import ci.gouv.budget.solde.sigdcp.model.traitement.NatureOperation;
import ci.gouv.budget.solde.sigdcp.model.traitement.TraitementDemandeCotationTitreTransport;
import ci.gouv.budget.solde.sigdcp.service.ServiceException;
import ci.gouv.budget.solde.sigdcp.service.dossier.OperationService;
import ci.gouv.budget.solde.sigdcp.service.sotra.TraitableServiceImpl;

@Stateless
public class DemandeCotationTitreTransportServiceImpl extends TraitableServiceImpl<DemandeCotationTitreTransport, Long,TraitementDemandeCotationTitreTransport> implements DemandeCotationTitreTransportService {
	
	private static final long serialVersionUID = 8509906777706320841L;

	
	@Inject protected OperationService operationService;
	@Inject protected OperationValidationConfigDao operationValidationConfigDao;	
	@Inject protected PieceJustificativeDao pieceJustificativeDao;
	@Inject protected PrestataireDemandeCotationTitreTransportDao prestataireDemandeCotationTitreTransportDao;
	

	
	@Inject protected DossierMissionDao dossierMissionDao;
	
	
	
	@Inject
	public DemandeCotationTitreTransportServiceImpl(DemandeCotationTitreTransportDao dao) {
		super(dao); 
	}  


	@Override @TransactionAttribute(TransactionAttributeType.NEVER)
	public DemandeCotationTitreTransport findDemande(Long id,String natureOperationCode) throws ServiceException {
		return null;
	}
	
	@Override
	protected Collection<DemandeCotationTitreTransport> firstFlow(NatureOperation natureOperation) {
		
		Collection<DemandeCotationTitreTransport> list = new ArrayList<>();
		if(natureOperation.getCode().equals(Code.NATURE_OPERATION_DEMANDE_COTATION)){
		/*for(DossierMission dossier : dossierMissionDao.readACoter()){
			list.add(nouveau(dossier));
		}*/
		}
		else if(natureOperation.getCode().equals(Code.NATURE_OPERATION_GENERATION_BCFO)){
			list = ((DemandeCotationTitreTransportDao)dao).readACommander();
		}
		
		return list;
		
	} 
	
	@TransactionAttribute(TransactionAttributeType.NEVER)
	@Override
	public DemandeCotationTitreTransport nouveau(DossierMission dossierMission){
		DemandeCotationTitreTransport demandeTitreTransport = new DemandeCotationTitreTransport(dossierMission);
		
		init(demandeTitreTransport, Code.NATURE_OPERATION_DEMANDE_COTATION);
		return demandeTitreTransport;
		
	}
	
	@Override
	protected void associerTraitement(DemandeCotationTitreTransport demandeTitreTransport) {
		demandeTitreTransport.getTraitable().getTraitement().setDemandeCotationTitreTransport(demandeTitreTransport);
	}


	@Override
	protected void creation(DemandeCotationTitreTransport demandeTitreTransport) {
		super.creation(demandeTitreTransport);
		
		
	}

	
	@Override
	protected void afterCreation(DemandeCotationTitreTransport demandeTitreTransport) {
		super.afterCreation(demandeTitreTransport);
		
		for(Prestataire prestataire : demandeTitreTransport.getPrestataires()){
			PrestataireDemandeCotationTitreTransportId id = new PrestataireDemandeCotationTitreTransportId(demandeTitreTransport.getId(), prestataire.getId());			
			PrestataireDemandeCotationTitreTransport prestataireDemandeCotationTitreTransport=new PrestataireDemandeCotationTitreTransport(id,demandeTitreTransport.getDossier(), prestataire);			
			prestataireDemandeCotationTitreTransportDao.create(prestataireDemandeCotationTitreTransport);
			
			
		}
		
	}
	

	@Override
	public Collection<DemandeCotationTitreTransport> findDemandes() {
		return null;//((CarteSotraDao)dao).readByAgent((AgentEtat)utilisateur());
	}

	@Override
	protected Object idValue(DemandeCotationTitreTransport demandeTitreTransport) {
		return demandeTitreTransport.getId();
	}

	@Override
	protected TraitementDemandeCotationTitreTransport traitementInstance() {
		return new TraitementDemandeCotationTitreTransport();
	}

	@Override
	public DemandeCotationTitreTransport find(Long id, String natureOperationCode)throws ServiceException {
		return null;
	}

	@Override
	protected Collection<TraitementDemandeCotationTitreTransport> historiqueTraitements(DemandeCotationTitreTransport objetTraite) {
		return null;
	}


	@Override
	public Collection<DemandeCotationTitreTransport> findDemandesSolde() {
		// TODO Auto-generated method stub
		return null;
	}

	
	


}
