package zenit.data.model;

import java.io.Serializable;
import java.util.List;

/**
 * Основна апстракција која се односи на програме.
 * @author VM
 * @version 1.0
 */
public interface Program extends Serializable{
	public String getName();
	public void setName(String name);
	public List<String> getSubjects();
	public void setSubjects(List<String> subjects);
	public String getId();
	public void addSubject(String subject);
	public void removeSubject(String subject);
}
