package ci.gouv.budget.solde.sigdcp.service.fichier;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import ci.gouv.budget.solde.sigdcp.dao.dossier.DossierDao;
import ci.gouv.budget.solde.sigdcp.dao.dossier.DossierTransitDao;
import ci.gouv.budget.solde.sigdcp.dao.dossier.GroupeDDDao;
import ci.gouv.budget.solde.sigdcp.dao.dossier.PieceJustificativeDao;
import ci.gouv.budget.solde.sigdcp.dao.geographie.DistanceEntreLocaliteDao;
import ci.gouv.budget.solde.sigdcp.dao.indemnite.IndemniteTrancheDistanceDao;
import ci.gouv.budget.solde.sigdcp.model.Code;
import ci.gouv.budget.solde.sigdcp.model.dossier.BordereauTransmission;
import ci.gouv.budget.solde.sigdcp.model.dossier.BulletinLiquidation;
import ci.gouv.budget.solde.sigdcp.model.dossier.CategorieDeplacement;
import ci.gouv.budget.solde.sigdcp.model.dossier.Document;
import ci.gouv.budget.solde.sigdcp.model.dossier.Dossier;
import ci.gouv.budget.solde.sigdcp.model.dossier.DossierDD;
import ci.gouv.budget.solde.sigdcp.model.dossier.DossierMission;
import ci.gouv.budget.solde.sigdcp.model.dossier.DossierTransit;
import ci.gouv.budget.solde.sigdcp.model.dossier.PieceJustificative;
import ci.gouv.budget.solde.sigdcp.model.dossier.PieceProduite;
import ci.gouv.budget.solde.sigdcp.model.geographie.DistanceEntreLocalite;
import ci.gouv.budget.solde.sigdcp.model.identification.Sexe;
import ci.gouv.budget.solde.sigdcp.model.indemnite.GroupeDD;
import ci.gouv.budget.solde.sigdcp.model.indemnite.GroupeMission;
import ci.gouv.budget.solde.sigdcp.model.indemnite.IndemniteCalculee;
import ci.gouv.budget.solde.sigdcp.model.indemnite.IndemniteTrancheDistance;
import ci.gouv.budget.solde.sigdcp.model.template.etat.AttestationdePriseenChargeEtat;
import ci.gouv.budget.solde.sigdcp.model.template.etat.BondeTransportEtat;
import ci.gouv.budget.solde.sigdcp.model.template.etat.BulletinLiquidationDDEtat;
import ci.gouv.budget.solde.sigdcp.model.template.etat.EtatHelper;
import ci.gouv.budget.solde.sigdcp.model.template.etat.FeuilleDeplacementEtat;
import ci.gouv.budget.solde.sigdcp.model.template.etat.RemboursementEtat;
import ci.gouv.budget.solde.sigdcp.service.ServiceException;
import ci.gouv.budget.solde.sigdcp.service.ServiceExceptionNoRollBack;
import ci.gouv.budget.solde.sigdcp.service.dossier.BordereauTransmissionService;
import ci.gouv.budget.solde.sigdcp.service.dossier.DocumentService;
import ci.gouv.budget.solde.sigdcp.service.indemnite.GroupeMissionService;
import ci.gouv.budget.solde.sigdcp.service.indemnite.IndemniteCalculateurService;
import ci.gouv.budget.solde.sigdcp.service.indemnite.IndemniteOperandeService;
import ci.gouv.budget.solde.sigdcp.service.resources.report.Report;
import ci.gouv.budget.solde.sigdcp.service.utils.FrenchNumberToWords;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@Stateless
public class EtatServiceJasperImpl implements EtatService {

	//private @Inject BulletinLiquidationDao bulletinLiquidationDao;
	@Inject private BordereauTransmissionService bordereauTransmissionService;
	@Inject private DossierDao dossierDao;
	@Inject private DossierTransitDao dossierTransitDao;
	@Inject private IndemniteOperandeService indemniteOperandeService;
	@Inject private PieceJustificativeDao pieceJustificativeDao;
	@Inject private GroupeDDDao groupeDDDao;
	@Inject private GroupeMissionService groupeMissionService;
	@Inject private DocumentService documentService;
	@Inject private IndemniteTrancheDistanceDao indemniteTrancheDistanceDao;
	@Inject private DistanceEntreLocaliteDao distanceEntreLocaliteDao;
	@Inject private IndemniteCalculateurService indemniteCalculateurService;
	
	@Override @TransactionAttribute(TransactionAttributeType.NEVER)
	public <T> byte[] build(Class<T> aClass,InputStream templateInputStream,Collection<T> dataSource) {
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataSource);
		try {
			JasperDesign jasperDesign = JRXmlLoader.load(templateInputStream);
			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, beanColDataSource);	
			return JasperExportManager.exportReportToPdf(jasperPrint);
		} catch (JRException e) {
			throw new ServiceException(e);
		}
	}
	
	private Collection<PieceJustificative> pieceJustificatives(Collection<PieceJustificative> pieceJustificatives,String type){
		Collection<PieceJustificative> collection = new ArrayList<>();
		for(PieceJustificative p : pieceJustificatives)
			if(type.equals(p.getModel().getTypePieceJustificative().getCode()))
				collection.add(p);
		return collection;
	}
	
	@Override @TransactionAttribute(TransactionAttributeType.NEVER)
	public byte[] build(Document document) {
		if(document instanceof PieceJustificative){
			PieceJustificative pieceJustificative = (PieceJustificative) document;
			if(Code.TYPE_PIECE_FEUILLE_DEPLACEMENT.equals(pieceJustificative.getModel().getTypePieceJustificative().getCode()))
				if(pieceJustificative.getDossier() instanceof DossierDD)
					return feuilleDeplacementDD(pieceJustificative);
				else
					return feuilleDeplacementMhci(pieceJustificative);
			else if(Code.TYPE_PIECE_BON_TRANSPORT.equals(pieceJustificative.getModel().getTypePieceJustificative().getCode()))
				return bonTransportDD(pieceJustificative);
						
		}else if(document instanceof PieceProduite){
			PieceProduite pieceProduite = (PieceProduite) document;
			if(Code.TYPE_PIECE_PRODUITE_BL.equals(pieceProduite.getType().getCode())){
				Dossier dossier = dossierDao.readByBulletinLiquidation((BulletinLiquidation) pieceProduite);
				if(dossier instanceof DossierDD)
					return blDD((DossierDD) dossier, (BulletinLiquidation) document, "bulletinliquidationdd.jrxml");
				else
					;//return bl
				return null;
			}else if(Code.TYPE_PIECE_PRODUITE_APEC.equals(pieceProduite.getType().getCode())){
				DossierTransit dossier = dossierTransitDao.readByAttestationPriseEnCharge(pieceProduite);
				return apec(pieceProduite, dossier);
			}else if(Code.TYPE_PIECE_PRODUITE_DECISION_REMB.equals(pieceProduite.getType().getCode())){
				Dossier dossier = dossierDao.readByPieceProduite(pieceProduite);
				return decisionRemb(pieceProduite, dossier);
			}else if(Code.TYPE_PIECE_PRODUITE_BT.equals(pieceProduite.getType().getCode())){
				//DossierTransit dossier = dossierTransitDao.readByAttestationPriseEnCharge(pieceProduite);
				return bt((BordereauTransmission) pieceProduite);
			}
		}
		throw new ServiceException("Aucun Etat disponible pour <"+document.getId()+">");
	}
	
	@Override @TransactionAttribute(TransactionAttributeType.NEVER)
	public byte[] buildBulletinLiquidation(Long numeroDossier) {
		return null;
	}
	
	
	
	private byte[] blDD(DossierDD dossier,BulletinLiquidation bulletinLiquidation,String fichierEtat){
		InputStream inputStream = Report.class.getResourceAsStream(fichierEtat);
		//System.out.println(ToStringBuilder.reflectionToString(dossier, ToStringStyle.MULTI_LINE_STYLE));
		if(BigDecimal.ZERO.compareTo(dossier.getMontantIndemnisation()) == -1 && (dossier.getIndemniteCalculees()==null || dossier.getIndemniteCalculees().isEmpty())){
			indemniteCalculateurService.calculerDossier(dossier);//FIXME this a workaround to compute for incoherent data. compute is done at generer bulletin liquidation
			//System.out.println(ToStringBuilder.reflectionToString(dossier, ToStringStyle.MULTI_LINE_STYLE));
		}
		CategorieDeplacement categorieDeplacement = dossier.getDeplacement().getNature().getCategorie();
		String idemniteSuffix = "_"+dossier.getDeplacement().getNature().getCode();
		
		//if(idemniteKilometriqueCode == null) throw new ServiceExceptionNoRollBack("Le code de l'indemnité kilométrique de la catégorie "+dossier.getDeplacement().getNature().getCategorie()
		//		+" est introuvable");
		
		List<BulletinLiquidationDDEtat> dataSource = new LinkedList<>();
		dataSource.add(new BulletinLiquidationDDEtat(pieceJustificativeDao.readAdministrativeByDossier(dossier), 
				pieceJustificativeDao.readByDossierByTypeId(dossier, Code.TYPE_PIECE_FEUILLE_DEPLACEMENT).iterator().next(), 
				bulletinLiquidation, "", "", indemniteOperandeService.nombreEnfant(dossier),indemniteOperandeService.distance(dossier),dossier.getGroupe().getLibelle(), 
				2,categorieDeplacement.getNombreJourIndemniteJournaliere(),"FORMULE IKP",EtatHelper.format(indemniteCalculee(dossier, Code.INDEMNITE_KILOMETRIQUE_PERSONNE+idemniteSuffix).getMontant()), 
				"FORMULE AGENT BAGAGES", EtatHelper.format(indemniteCalculee(dossier, Code.INDEMNITE_BAGGAGE_AGENT+idemniteSuffix).getMontant()), 
				"FORMULE CONJOINT BAGAGES", EtatHelper.format(indemniteCalculee(dossier, Code.INDEMNITE_BAGGAGE_CONJOINT+idemniteSuffix).getMontant()), 
				"FORMULE ENFANT BAGAGES", EtatHelper.format(indemniteCalculee(dossier, Code.INDEMNITE_BAGGAGE_ENFANT+idemniteSuffix).getMontant()), 
				
				"FORMULE AGENT MONTANT", EtatHelper.format(indemniteCalculee(dossier, Code.INDEMNITE_JOURNALIERE_AGENT+idemniteSuffix).getMontant()), 
				"FORMULE CONJOINT MONTANT", EtatHelper.format(indemniteCalculee(dossier, Code.INDEMNITE_JOURNALIERE_CONJOINT+idemniteSuffix).getMontant()), 
				"FORMULE ENFANT MONTANT", EtatHelper.format(indemniteCalculee(dossier, Code.INDEMNITE_JOURNALIERE_ENFANT+idemniteSuffix).getMontant()), 
				
				EtatHelper.format(dossier.getMontantIndemnisation()), FrenchNumberToWords.convert(dossier.getMontantIndemnisation().longValue())+" FCFA"));
		
		return build(BulletinLiquidationDDEtat.class, inputStream, dataSource);
	}
	
	/*
	private byte[] blMhci(DossierMission dossier,BulletinLiquidation bulletinLiquidation,String fichierEtat){
		InputStream inputStream = Report.class.getResourceAsStream(fichierEtat);
		CategorieDeplacement categorieDeplacement = dossier.getDeplacement().getNature().getCategorie();
		dossierService.calculerMontantIndemnisation(dossier);
		
		List<BulletinLiquidation> dataSource = new LinkedList<>();
		dataSource.add(new BulletinLiquidationDDEtat(pj(dossier), pj(dossier), bulletinLiquidation, null, null, details.getNombreEnfant().intValue(), details.getDistance().intValue(), details.getGroupe().getLibelle(), 
				2,categorieDeplacement.getNombreJourIndemniteJournaliere(),details.getFormuleIkp(),details.getIndemniteKilometrique()+"", 
				details.getAgent().getFormuleBagages(), details.getAgent().getIndemniteBagages()+"", details.getConjoint().getFormuleBagages(), details.getConjoint().getIndemniteBagages()+"", 
				details.getEnfants().getFormuleBagages(), details.getEnfants().getIndemniteBagages()+"", details.getAgent().getFormuleJournaliere(), details.getAgent().getIndemniteJournaliere()+"",
				details.getConjoint().getFormuleJournaliere(), details.getConjoint().getIndemniteJournaliere()+"", details.getEnfants().getFormuleJournaliere(), details.getEnfants().getIndemniteJournaliere()+"",
				details.getTotal()+"", details.getTotalEnLettre()));
		return build(BulletinLiquidationDDEtat.class, inputStream, dataSource);
	}*/
	
	private BondeTransportEtat bonTransportEtat(BondeTransportEtat bonTransportEtat,String libdoc,String statutdoc){
		bonTransportEtat.setLibdoc(libdoc);
		bonTransportEtat.setStatutdoc(statutdoc);
		return bonTransportEtat;
	}
	
	private byte[] bonTransportDD(PieceJustificative pieceJustificative){
		InputStream inputStream = Report.class.getResourceAsStream("bondetransportdd.jrxml");
		List<BondeTransportEtat> dataSource = new LinkedList<>();
		PieceJustificative padmin = pieceJustificativeDao.readAdministrativeByDossier(pieceJustificative.getDossier());
		BondeTransportEtat bt = new BondeTransportEtat(pieceJustificative, "Original", null,EtatHelper.format(padmin.getModel().getTypePieceJustificative().getLibelle())
				, padmin.getNumero(), EtatHelper.format(padmin.getDateEtablissement()), pieceJustificative.getDossier().getService() ==null?"SERVICE INCONNU":pieceJustificative.getDossier().getService().getLibelle(), "Transporteur", "SDDCP", "RECPSERVICE", 
				EtatHelper.format(new Date()), EtatHelper.format(new Date()), EtatHelper.format(new Date()), "Lieu Signature", "Budget général", "", "", "", "", "000410"
				, EtatHelper.format(new Date()), "332", "4101", "016289", "Abidjan", 
				EtatHelper.format(new Date()), "", "Materiel transporte", EtatHelper.format(padmin.getDossier().getMontantIndemnisation())
				,documentService.genererCodeBarre(pieceJustificative));
		dataSource.add(bonTransportEtat(bt, "BON TRANSPORT", "ORIGINAL"));
		return build(BondeTransportEtat.class, inputStream, dataSource);
	}
	
	private byte[] feuilleDeplacementDD(PieceJustificative pieceJustificative){
		InputStream inputStream = Report.class.getResourceAsStream("feuilledeplacementdd.jrxml");
		List<FeuilleDeplacementEtat> dataSource = new LinkedList<>();
		Collection<PieceJustificative> pieceJustificatives = pieceJustificativeDao.readByDossier(pieceJustificative.getDossier());
		
		GroupeDD groupeDD = groupeDDDao.readByGrade(pieceJustificative.getDossier().getGrade());
		Boolean male = Sexe.MASCULIN.equals(pieceJustificative.getDossier().getBeneficiaire().getSexe());
		Boolean marie = !pieceJustificatives(pieceJustificatives, Code.TYPE_PIECE_EXTRAIT_MARIAGE).isEmpty();
		//PieceJustificative decision = pieceJustificatives(pieceJustificatives, Code.TYPE_PIECE_DECISION_AFFECTATION).iterator().next();
		PieceJustificative decision = pieceJustificativeDao.readAdministrativeByDossier(pieceJustificative.getDossier());
		int nbe = pieceJustificatives(pieceJustificatives, Code.TYPE_PIECE_EXTRAIT_NAISSANCE).size();
		
		StringBuilder accompaganteur = new StringBuilder();
		if(marie)
			accompaganteur.append("Epou"+(male?"x":"se"));
		if(nbe>0){
			accompaganteur.append(accompaganteur.length()==0?"":" et "+ (nbe+" enfant"+(nbe>1?"s":"")));
		}
		
		DistanceEntreLocalite distanceEntreLocalite =  distanceEntreLocaliteDao.readByLocalite1ByLocalite2(pieceJustificative.getDossier().getDeplacement().getLocaliteDepart(), 
				pieceJustificative.getDossier().getDeplacement().getLocaliteArrivee());
		
		if(distanceEntreLocalite == null) throw new ServiceExceptionNoRollBack("La distance entre "+pieceJustificative.getDossier().getDeplacement().getLocaliteDepart()+" et "
				+pieceJustificative.getDossier().getDeplacement().getLocaliteArrivee()+" est introuvable");
		
		IndemniteTrancheDistance indemniteTrancheDistance = indemniteTrancheDistanceDao.readByValeurByCategorieDeplacement(pieceJustificative.getDossier().getDeplacement().getNature().getCategorie()
				, distanceEntreLocalite.getDistanceKm());
		
		if(indemniteTrancheDistance == null) throw new ServiceExceptionNoRollBack("L'indemnité kilométrique de la catégorie "+pieceJustificative.getDossier().getDeplacement().getNature().getCategorie()
				+" et la distance "+distanceEntreLocalite.getDistanceKm()+" est introuvable");
		
		BigDecimal fvt=indemniteTrancheDistance.getMontant()
				,ija=groupeDD.getMontantAgent()
				,ijep=marie ? groupeDD.getMontantEpouse() : BigDecimal.ZERO
				,ijen = groupeDD.getMontantEnfant().multiply(new BigDecimal(nbe))
				,totChif=fvt.add(ija).add(ijep).add(ijen)
				,aPayer=totChif;
		String totLettre = FrenchNumberToWords.convert(aPayer.longValue());
		dataSource.add(new FeuilleDeplacementEtat(pieceJustificative, "Décision", EtatHelper.format(decision.getDateEtablissement()), groupeDD.getLibelle()
				, pieceJustificative.getDossier().getBeneficiaire().getIndice() == null ? "" : pieceJustificative.getDossier().getBeneficiaire().getIndice().toString()
				, accompaganteur.toString(),EtatHelper.format(fvt),EtatHelper.format(ija),EtatHelper.format(ijep)
				, EtatHelper.format(ijen),EtatHelper.format(totChif),EtatHelper.format(aPayer),totLettre,"Abidjan",EtatHelper.format(new Date()),documentService.genererCodeBarre(pieceJustificative)));
		return build(FeuilleDeplacementEtat.class, inputStream, dataSource);
	}
	
	private byte[] feuilleDeplacementMhci(PieceJustificative pieceJustificative){
		InputStream inputStream = Report.class.getResourceAsStream("feuilledeplacementmhci.jrxml");
		List<FeuilleDeplacementEtat> dataSource = new LinkedList<>();
		DossierMission dossier = (DossierMission) pieceJustificative.getDossier();
		GroupeMission groupe = groupeMissionService.findByFonctionOrGrade(dossier.getFonction(), dossier.getGrade());
		
		StringBuilder accompaganteur = new StringBuilder();		
		dataSource.add(new FeuilleDeplacementEtat(pieceJustificative, "???", "???", groupe.getLibelle(), 
				pieceJustificative.getDossier().getBeneficiaire().getIndice()+"",accompaganteur.toString(),"","","","","","","","","",null));
		return build(FeuilleDeplacementEtat.class, inputStream, dataSource);
	}
	
	private byte[] apec(PieceProduite piece,DossierTransit dossier){
		InputStream inputStream = Report.class.getResourceAsStream("attestationprisecharge.jrxml");
		List<AttestationdePriseenChargeEtat> dataSource = new LinkedList<>();
		dataSource.add(new AttestationdePriseenChargeEtat(piece,dossier, "TRANSITAIRE", "CHAPITRE IMPUTATION", "ARTICLE IMPUTATION", "PARAGRAPHE IMPUTATION"));
		return build(AttestationdePriseenChargeEtat.class, inputStream, dataSource);
	}
	
	private byte[] decisionRemb(PieceProduite piece,Dossier dossier){
		InputStream inputStream = Report.class.getResourceAsStream("decisionremboursement3.jrxml");
		List<RemboursementEtat> dataSource = new LinkedList<>();
		dataSource.add( new RemboursementEtat(dossier.getDeplacement().getNature().getLibelle(), dossier.getBeneficiaire().getNomPrenoms(),
				"A Calculer", "qu'il paye", "Chapitre à determiner", "Libelle Chapitre à determiner", dossier.getDateCreation()+"", new ArrayList<>(pieceJustificativeDao.readByDossier(dossier))));
		return build(RemboursementEtat.class, inputStream, dataSource);
	}
	
	private byte[] bt(BordereauTransmission piece){
		InputStream inputStream = Report.class.getResourceAsStream("bordereautest.jrxml");
		bordereauTransmissionService.init(piece, null);
		return build(BordereauTransmission.class, inputStream, Arrays.asList(piece));
	}
	
	private IndemniteCalculee indemniteCalculee(Dossier dossier,String codeIndemnite){
		for(IndemniteCalculee indemniteCalculee : dossier.getIndemniteCalculees())
			if(indemniteCalculee.getId().getIndeminiteId().equals(codeIndemnite))
				return indemniteCalculee;
		return null;
	}

}
