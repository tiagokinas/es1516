package pt.tecnico.mydrive.service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class CreateXMLFile {
    public void create(String path) {

        String thePath = path;

        try {

            Element mydrive = new Element("mydrive");
            Document doc = new Document(mydrive);

            createUser(doc, "Francisco", "/home/francisco", "a senha do francisco", "rwxd----");
            createUser(doc, "Maria", "/home/maria", "a senha da maria", "rwxd----");

            //createFile(doc, id, type, owner, name,  parent, children, path, method, datamod)
            createFile(doc, "1", "dir", "Francisco", "directory1",  "/home/francisco", null, "/home/francisco", null, null);
            createFile(doc, "2", "plain", "Francisco", "plainFile1",  null, null, null, null, null);
            createFile(doc, "3", "plain", "Maria", "plainFile1",  null, null, null, null, null);

            // output para o terminal
            //caso de testes 5
            new XMLOutputter().output(doc, System.out);

            // output para ficheiro
            XMLOutputter xmlOutput = new XMLOutputter();


            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(doc, new FileWriter(path));

            System.out.println("File Saved!");
        } catch (IOException io) {
            System.out.println(io.getMessage());
        }
    }

    private void createFile(Document doc, String id, String type, String owner, String name, String parent,
                            String children, String path, String method, String datamod) {
        Element file = new Element("file");
        file.setAttribute(new Attribute("id", id));
        file.addContent(new Element("type").setText(type));
        file.addContent(new Element("owner").setText(owner));
        file.addContent(new Element("name").setText(name));
        file.addContent(new Element("parent").setText(parent));
        file.addContent(new Element("children").setText(children));
        file.addContent(new Element("method").setText(method));
        file.addContent(new Element("path").setText(path));
        file.addContent(new Element("datamod").setText(datamod));

        doc.getRootElement().addContent(file);
    }

    private void createUser(Document doc, String name, String homedir, String password, String mask) {

        Element user = new Element("user");
        user.addContent(new Element("username").setText(name));
        user.addContent(new Element("homedir").setText(homedir));
        user.addContent(new Element("password").setText(password));
        user.addContent(new Element("mask").setText(mask));

        doc.getRootElement().addContent(user);
    }
}