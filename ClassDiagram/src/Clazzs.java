import java.util.LinkedList;
import java.util.List;

public class Clazzs {
	//POJO类，描述一个类的UML图
	private String name = "";
	private String url = "";
	private List<String> fields = new LinkedList();
	private List<String> methods = new LinkedList();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<String> getFields() {
		return fields;
	}
	public void setFields(List<String> fields) {
		this.fields = fields;
	}
	public List<String> getMethods() {
		return methods;
	}
	public void setMethods(List<String> methods) {
		this.methods = methods;
	}
	@Override
	public String toString() {
		return "Clazzs [name=" + name + ", url=" + url + ", fields=" + fields + ", methods=" + methods + "]";
	}
	
	
}
