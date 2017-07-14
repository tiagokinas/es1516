package pt.tecnico.mydrive.domain;

import java.util.List;

import org.jdom2.Element;
import org.joda.time.DateTime;

import pt.tecnico.mydrive.exception.ImportDocumentException;

public abstract class File extends File_Base {

	protected File() { 
	}

	/**
	 * Cria um ficheiro iniciando apenas o id, e adiciona-a a aplicacao
	 * @param rootObject
	 */
	public File(MyDrive md) {
		super();

		setId(md.incNumberOfFiles());
		setMydrive(md); //Adiciona este file a aplicacao
	}

	public File getFile(int id){ //TODO só aqui para compilar - nao faz sentido
		return this;
	}

	/**
	 * Cria um ficheiro inciando todos os atributos desta
	 * @param rootObject
	 * @param nome do ficheiro
	 * @param id
	 */
	public File(MyDrive root, int id, String name) {
		this(root);
		setName(name);
		//TODO something with id
		setDatamod(new DateTime());

	}

	public abstract void delete();

	//TODO
	public void xmlImpport(Element fileElement) throws ImportDocumentException {

	}

	//TODO
	public Element xmlExport() {
		Element element = new Element("file");

		return element;

	}
	public abstract void execute(User u, List<String> args);

	public abstract String getType();
}
