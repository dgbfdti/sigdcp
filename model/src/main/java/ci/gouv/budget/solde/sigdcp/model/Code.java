package ci.gouv.budget.solde.sigdcp.model;


/**
 * Ensembles des constantes définies dans la base de données
 * @author christian
 *
 */
public interface Code {
	
	String NUMERO_PREFIX = "MEF/DGBF/DS/";
	
	String ROLE_AGENT_ETAT = "AGENTETAT";
	String ROLE_AGENT_MISSION = "AGENTMISSION";
	String ROLE_AYANT_DROIT_FO = "AYANTDROITFO";
	String ROLE_AGENT_SOLDE = "AGENTSOLDE";
	String ROLE_POINT_FOCAL = "POINTFOCAL";
	String ROLE_GESTIONNAIRE_CARTE_SOTRA = "GESTCARTESOTRA";
	String ROLE_DELEGUE_SOTRA = "DELEGUESOTRA";
	String ROLE_DIRECTEUR = "DIRECTEUR";
	String ROLE_LIQUIDATEUR = "LIQUIDATEUR";
	String ROLE_RESPONSABLE = "RESPONSABLE";
	String ROLE_PAYEUR = "PAYEUR";
	String ROLE_ADMINISTRATEUR = "ADMINISTRATEUR";
	
	String ROLE_PRESTATAIRE = "PRESTATAIRE";
	
	
	String OPERATER_SOLDE = "SOLDE";
	
	/*
	 * Catégorie de déplacement
	 * 
	 */
	
	String CATEGORIE_DEPLACEMENT_DEFINITIF = "DEFINITIF";
	String CATEGORIE_DEPLACEMENT_TRANSIT = "TRANSIT";
	String CATEGORIE_DEPLACEMENT_MISSION = "MISSION";
	String CATEGORIE_DEPLACEMENT_OBSEQUE = "OBSEQUES";
	//String CATEGORIE_DEPLACEMENT_TITRE_TRANSPORT = "TITRETRANSPORT";
	
	/*
	 * Nature de déplacement
	 */
	String NATURE_DEPLACEMENT_AFFECTATION = "AFF";
	String NATURE_DEPLACEMENT_MUTATION = "MUT";
	String NATURE_DEPLACEMENT_RETRAITE = "RET";
	String NATURE_DEPLACEMENT_MISSION_HCI = "MHCI";
	//String NATURE_DEPLACEMENT_MISSION_TITRE_TRANSPORT = "MTT";
	String NATURE_DEPLACEMENT_TRANSIT_BAGAGGES_MAE = "TB_MAE";
	String NATURE_DEPLACEMENT_TRANSIT_BAGAGGES_STAGIAIRE = "TB_STAGE";
	String NATURE_DEPLACEMENT_OBSEQUE_FRAIS = "FO";
	String NATURE_DEPLACEMENT_TITRE_TRANSPORT_CONGE = "TT_CONGE";
	String NATURE_DEPLACEMENT_TITRE_TRANSPORT_STAGE = "TT_STAGE";
	
	
	/*
	 * Type de pieces produites
	 */
	
	String TYPE_PIECE_PRODUITE_BL = "BL";
	String TYPE_PIECE_PRODUITE_BT = "BT";
	String TYPE_PIECE_PRODUITE_FD = "FD";
	String TYPE_PIECE_PRODUITE_BC = "BC";
	String TYPE_PIECE_PRODUITE_APEC = "APEC";
	String TYPE_PIECE_PRODUITE_DECISION_REMB = "DECISION_REMB";
	//String TYPE_PIECE_PRODUITE_BTF = "BTF";
	
	/*
	 * Type de pieces justificatives
	 */
	
	String TYPE_PIECE_CNI = "CNI";
	String TYPE_PIECE_PASSPORT = "PASSPORT";
	String TYPE_PIECE_ATTESTATION_SG = "ATTSG";
	String TYPE_PIECE_COMMUNICATION = "COMMUNICATION";
	String TYPE_PIECE_DECISION_AFFECTATION = "DAFF";
	String TYPE_PIECE_DEMANDE_OCTROI_BILLET = "DOB";
	String TYPE_PIECE_DECISION_CONGE = "DCONGE";
	String TYPE_PIECE_DECISION_MISE_STAGE = "DMSTAGE";
	String TYPE_PIECE_CERTIFICAT_PREMIERE_PRISE_SERVICE = "CPPS";
	String TYPE_PIECE_EXTRAIT_MARIAGE= "EXTMAR";
	String TYPE_PIECE_EXTRAIT_NAISSANCE = "EXTNAISS";
	String TYPE_PIECE_ATTESTATION_TRANSPORT = "ATTRANS";
	String TYPE_PIECE_ARRETE_MUTATION = "ARRMUT";
	String TYPE_PIECE_CARTE_PROFESSIONNELLE = "CARTPROF";
	String TYPE_PIECE_AVIS_MUTATION = "AVISMUT";
	String TYPE_PIECE_CERTIFICAT_PRESENCE_CORPS = "CPC";
	String TYPE_PIECE_ARRETE_RADIATION = "ARRADIATION";
	String TYPE_PIECE_ARRETE_MISE_RETRAITE = "ARRMR";
	String TYPE_PIECE_CERTIFICAT_CESSATION_SERVICE = "CCS";
	String TYPE_PIECE_ORDRE_MISSION = "ORDMISS";
	String TYPE_PIECE_ATTESTATION_MISE_STAGE = "ATTMS";
	String TYPE_PIECE_ATTESTATION_FIN_STAGE = "ATTFS";
	String TYPE_PIECE_ATTESTATION_MAE = "ATTMAE";
	String TYPE_PIECE_DECISION_RAPPEL = "DECISRAPPEL";
	String TYPE_PIECE_CERTIFICAT_PRISE_SERVICE = "CPS";
	String TYPE_PIECE_FACTURE_PROFORMA = "FACTPROF";
	String TYPE_PIECE_FACTURE_DEFINITIVE = "FACTDEF";
	String TYPE_PIECE_EXTRAIT_DECES = "EXTDECES";
	String TYPE_PIECE_CERTIFICAT_DECES = "CERTDECES";
	String TYPE_PIECE_BULLETIN_SALAIRE = "BULLSAL";
	String TYPE_PIECE_LETTRE_MINISTERIELLE = "LETTMIN";
	String TYPE_PIECE_ARRETE_PREFECTORAL ="ARRPREF";
	String TYPE_PIECE_FEUILLE_DEPLACEMENT ="FEUILLEDEP";
	String TYPE_PIECE_BON_TRANSPORT ="BONTRANS";
	String TYPE_PIECE_ACTE_DECES ="ACTDECES";
	String TYPE_PIECE_AUTORISATION_TRANSFERT ="AUTTRANS";
	String TYPE_PIECE_PERMIS_INHUMER ="PERINH";
	
	
	/*
	 * Type de agent de l'état
	 */
	String TYPE_AGENT_ETAT_FONCTIONNAIRE = "F";
	String TYPE_AGENT_ETAT_CONTRACTUEL = "C";
	String TYPE_AGENT_ETAT_POLICIER = "P";
	String TYPE_AGENT_ETAT_GENDARME = "G";
	String TYPE_AGENT_ETAT_MISSION = "AM";
	
	/*
	 * Type de personne
	 */
	String TYPE_PERSONNE_AYANT_DROIT = "AD";
	/*
	 * Type prestataire
	 */
	String TYPE_PRESTATAIRE = "PR";
	String TYPE_PRESTATAIRE_PF = "PF";
	String TYPE_PRESTATAIRE_AV = "AV";
	String TYPE_PRESTATAIRE_CG = "CG";
	String TYPE_PRESTATAIRE_TR = "TR";
	
	/*
	 * Type de localite
	 */
	String TYPE_LOCALITE_PAYS = "PAYS";
	String TYPE_LOCALITE_VILLE = "VILLE";
	String TYPE_LOCALITE_MAIRIE = "MAIRIE";
	String TYPE_LOCALITE_ZONE = "ZONE";
	String TYPE_LOCALITE_CONTINENT = "CONTINENT";
	
	/*
	 * Localite
	 */
	String LOCALITE_COTE_DIVOIRE = "CI";
	String LOCALITE_ABIDJAN = "ABJ";
	String LOCALITE_BOUAKE = "BK";
	String LOCALITE_SAN_PEDRO = "SPD";
	
	String LOCALITE_PARIS = "PAR";
	
	/*
	 * Situation Matrimoniale
	 */
	String SITUATION_MATRIMONIALE_MARIE = "MARIE";
	String SITUATION_MATRIMONIALE_CELIBATAIRE = "CELIB";
	String SITUATION_MATRIMONIALE_VEUF = "VEUF";
	
	/*
	 * Type de section
	 */
	String TYPE_SECTION_MINISTERE = "MIN";
	String TYPE_SECTION_SERVICE = "SERV";
	String TYPE_SECTION_DIRECTION = "DIR";
	
	/*
	 * section
	 */
	String SECTION_MIN_MEF = "MEF";
	String SECTION_MIN_MB = "MBUD";
	String SECTION_DIRECTION_DGBF = "DGBF";
	String SECTION_DIRECTION_TRESOR = "TRESOR";
	
	/*
	 * Type de dépense
	 */
	String TYPE_DEPENSE_PRISE_EN_CHARGE = "PEC";
	String TYPE_DEPENSE_REMBOURSEMENT = "REMB";
	
	/*
	 * Type de classe de voyage
	 */
	String TYPE_CLASSE_VOYAGE_1ERE = "1C";
	String TYPE_CLASSE_VOYAGE_2EME = "2C";
	String TYPE_CLASSE_VOYAGE_TOURISTE = "3C";
	
	/*
	 * Groupe mission
	 */
	String GROUPE_MISSION_HORS_GROUPE = "H";
	String GROUPE_MISSION_A = "A";
	String GROUPE_MISSION_B = "B";
	
	/*
	 * Groupe DD
	 */
	String GROUPE_DD_1 = "g1";
	String GROUPE_DD_2 = "g2";
	String GROUPE_DD_3 = "g3";
	String GROUPE_DD_4 = "g4";
	String GROUPE_DD_5 = "g5";
	
	/*
	 * Nature operation
	 */

	String NATURE_OPERATION_ANNULATION_DEPLACEMENT="ANNULE_DEPLACEMENT";
	String NATURE_OPERATION_ANNULATION_DEMANDE="ANNULE_DEMANDE";
	
	String NATURE_OPERATION_ORGANISATION_DEPLACEMENT="ORGANISATION_DEPLACEMENT";
	String NATURE_OPERATION_CREATION_DEMANDE="CREATION_DEMANDE";
	String NATURE_OPERATION_SAISIE="SAISIE";
	String NATURE_OPERATION_TRANSMISSION_SAISIE_A_BENEFICIAIRE="TRANS_SAISIE_BEN";
	String NATURE_OPERATION_TRANSMISSION_SAISIE_A_ORGANISATEUR="TRANS_SAISIE_ORG";
	String NATURE_OPERATION_SOUMISSION_DEPLACEMENT="SOUMISSION_DEPLACEMENT";
	String NATURE_OPERATION_SOUMISSION="SOUMISSION";
	String NATURE_OPERATION_RECEVABILITE="RECEVABILITE";
	String NATURE_OPERATION_DEPOT="DEPOT";
	String NATURE_OPERATION_CONFORMITE="CONFORMITE";
	String NATURE_OPERATION_RETOUR_FD="RET_FD";
	String NATURE_OPERATION_RECEPTION_FACTURE="REC_FACT";
	
	String NATURE_OPERATION_ETABLISSEMENT_BL="GEN_BL";
	String NATURE_OPERATION_VALIDATION_BL="VAL_BL";
	String NATURE_OPERATION_TRANSMISSION_BL_VISA="TRANS_BL_VISA";
	String NATURE_OPERATION_VISA_BL="VISA_BL";
	
	String NATURE_OPERATION_ETABLISSEMENT_PROJDEC="GEN_PROJDEC";
	String NATURE_OPERATION_VALIDATION_PROJDEC="VAL_PROJDEC";
	String NATURE_OPERATION_TRANSMISSION_PROJDEC_CABINET="TRANS_PROJDEC_CAB";
	String NATURE_OPERATION_RECEPTION_PROJDEC="REC_PROJDEC";
	
	String NATURE_OPERATION_ETABLISSEMENT_BTBL="GEN_BTBL";
	String NATURE_OPERATION_MODIFICATION_BTBL="MOD_BTBL";
	String NATURE_OPERATION_VALIDATION_BTBL="VAL_BTBL";
	String NATURE_OPERATION_TRANSMISSION_BTBL="TRANS_BTBL";
	
	// Paiement
	String NATURE_OPERATION_PRISE_EN_CHARGE="PEC";
	String NATURE_OPERATION_MISE_EN_REGLEMENT="MER";
	String NATURE_OPERATION_REGLER_BL="REG_BL";
	
	//Carte Sotra
	String NATURE_OPERATION_ANNULATION_CS="ANNULATION_CS";
	String NATURE_OPERATION_DEMANDE_CS="DEMANDE_CS";
	String NATURE_OPERATION_RETRAIT_CS="RETRAIT_CS";
	
	//Liste Carte Sotra
	String NATURE_OPERATION_ANNULATION_LCS="ANNULATION_LCS";
	String NATURE_OPERATION_OUVERTURE_LCS="OUVERTURE_LCS";
	String NATURE_OPERATION_CLOTURE_LCS="CLOTURE_LCS";
	String NATURE_OPERATION_VALIDATION_LCS="VALIDATION_LCS";
	String NATURE_OPERATION_RETRAIT_CARTE_LCS="RETRAIT_CARTE_LCS";
	
	// Commande Carte Sotra
	String NATURE_OPERATION_GENERATION_CCS="GENERATION_CCS";
	String NATURE_OPERATION_DISTRIBUTION_CCS_SOTRA="DISTRIBUTION_CCS_SOTRA";
	String NATURE_OPERATION_DISTRIBUTION_CCS_DELEGUE="DISTRIBUTION_CCS_DELEGUE";
	
	// Prestation
	String NATURE_OPERATION_DEMANDE_COTATION="DEM_COT";
	String NATURE_OPERATION_REPONDRE_COTATION="REP_COT";
	String NATURE_OPERATION_GENERATION_BCTT="GEN_BCTT";
	String NATURE_OPERATION_GENERATION_BCFO="GEN_BCFO";
	String NATURE_OPERATION_VISA_BCTT="VISA_BCTT";
	String NATURE_OPERATION_VISA_BCFO="VISA_BCFO";
	String NATURE_OPERATION_TRANSMISSION_BCTT="TRANS_BCTT";
	String NATURE_OPERATION_TRANSMISSION_BCFO="TRANS_BCFO";
	
	/*
	 * statut
	 */
	String STATUT_ACCEPTE = "ACCEPTE";
	String STATUT_REJETE = "REJETE";
	String STATUT_DIFFERE = "DIFFERE";	
	
	/*
	 * Type Indemnite Tranche
	 */
	String TYPE_INDEMNITE_TRANCHE_DISTANCE = "DISTANCE";
	
	String INDEMNITE_KILOMETRIQUE_PERSONNE = "IKP";
	String INDEMNITE_BAGGAGE_AGENT = "IBA";
	String INDEMNITE_BAGGAGE_CONJOINT = "IBC";
	String INDEMNITE_BAGGAGE_ENFANT = "IBE";
	String INDEMNITE_JOURNALIERE_AGENT = "IJA";
	String INDEMNITE_JOURNALIERE_CONJOINT = "IJC";
	String INDEMNITE_JOURNALIERE_ENFANT = "IJE";
	String INDEMNITE_MISSION_SEJOUR = "IMS";
	String INDEMNITE_MISSION_DIVERS = "IMD";
	String INDEMNITE_DISTANCE_OBSEQUE = "IDO";
	String INDEMNITE_CERCUEIL = "ICERC";
	
	String INDEMNITE_KILOMETRIQUE_PERSONNE_AFFECTATION = "IKP_AFF";
	String INDEMNITE_KILOMETRIQUE_PERSONNE_MUTATION = "IKP_MUT";
	String INDEMNITE_KILOMETRIQUE_PERSONNE_RETRAITE = "IKP_RET";
}
