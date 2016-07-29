package ci.gouv.budget.solde.sigdcp.model.template.etat;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import ci.gouv.budget.solde.sigdcp.model.identification.AgentEtat;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EtatHelper implements Serializable {

	private static final long serialVersionUID = -5973562425199996115L;

	public static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
	
	public static String format(Object object){
		if(object == null)
			return "";
		if(object instanceof Date)
			return DATE_FORMAT.format((Date)object);
		if(object instanceof AgentEtat)
			return ((AgentEtat)object).getNomPrenoms();
		return object.toString();
	}

	public static void main(String[] args) {
		System.out.println("EtatHelper.main()");
	}
	
}
