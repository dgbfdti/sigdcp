package ci.gouv.budget.solde.sigdcp.dao.identification;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import ci.gouv.budget.solde.sigdcp.dao.JpaDaoImpl;
import ci.gouv.budget.solde.sigdcp.model.identification.Section;

public class SectionDaoImpl extends JpaDaoImpl<Section, String> implements SectionDao , Serializable {

	private static final long serialVersionUID = -2609724288199083806L;
	
	@Override
	public Collection<Section> readBySectionTypeId(String sectionTypeId) {
		return entityManager.createQuery("SELECT section FROM Section section WHERE section.type.code = :sectionTypeId ORDER BY section.libelle ASC", clazz)
				.setParameter("sectionTypeId", sectionTypeId)
				.getResultList();
	}
	
	@Override
	public List<Section> readAll() {
		return entityManager.createQuery("SELECT section FROM Section section ORDER BY section.libelle ASC", clazz).getResultList();
	}
	
}