package pt.tecnico.mydrive.service;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import pt.tecnico.mydrive.domain.*;

import java.io.IOException;
import java.util.List;


public class ReadXMLFile {
    public void read(String path) {

        SAXBuilder builder = new SAXBuilder();
        java.io.File xmlFile = new java.io.File(path);

        try {

            readUsers(builder, xmlFile);
            readFiles(builder, xmlFile);

        } catch (IOException io) {
            System.out.println(io.getMessage());
        } catch (JDOMException jdomex) {
            System.out.println(jdomex.getMessage());
        }
    }

    private void readFiles(SAXBuilder builder, java.io.File xmlFile) throws JDOMException, IOException {

        String id;
        String type;
        String name;
        String owner;
        String datamod;
        String parent;
        String children;
        String method;
        String path;

        Document document = (Document) builder.build(xmlFile);
        Element rootNode = document.getRootElement();
        List files = rootNode.getChildren("file");

        for (int i = 0; i < files.size(); i++) {
            System.out.println("====== found file ======");
            Element node = (Element) files.get(i);

            id = node.getAttribute("id").getValue();
            name = node.getChildText("name");
            type = node.getChildText("type");
            owner = node.getChildText("owner");
            datamod = node.getChildText("datamod");
            parent = node.getChildText("parent");
            children = node.getChildText("children");
            method = node.getChildText("method");
            path = node.getChildText("path");

            switch(type){
                case("plain") : {
                    pt.tecnico.mydrive.domain.PlainFile theFile = new PlainFile();
                    theFile.setId(Integer.parseInt(id));
                    theFile.setName(name);
                    //theFile.setUser(MyDrive.getUserByName(owner));
                    //theFile.setDatamod();
                    }
                case("dir") : {
                    pt.tecnico.mydrive.domain.Directory theFile = new Directory(path,name);
                    theFile.setId(Integer.parseInt(id));
                    //theFile.setParent(parent);
                    //theFile.setChildren(children);
                    //theFile.setDatamod();
                }
                case("link") : {
                    pt.tecnico.mydrive.domain.Link theFile = new Link();
                    theFile.setName(name);
                    theFile.setId(Integer.parseInt(id));
                    //theFile.setDatamod();
                }
                case("app") : {
                    pt.tecnico.mydrive.domain.Application theFile = new Application();
                    theFile.setName(name);
                    theFile.setId(Integer.parseInt(id));
                    theFile.setMethod(method);
                    //theFile.setDatamod();
                }
                default:
            }

            System.out.println("Id : " + id);
            System.out.println("Type : " + type);
            System.out.println("Owner : " + owner);
            System.out.println("Data Mod : " + datamod);
            System.out.println("Parent Dir : " + parent);
            System.out.println("Child dir : " + children);
            System.out.println("Method : " + method);
        }
    }

    private void readUsers(SAXBuilder builder, java.io.File xmlFile) throws JDOMException, IOException {

        String username;
        String password;
        String homedir;
        String mask;

        Document document = (Document) builder.build(xmlFile);
        Element rootNode = document.getRootElement();
        List users = rootNode.getChildren("user");

        for (int i = 0; i < users.size(); i++) {
            System.out.println("====== found user ======");
            Element node = (Element) users.get(i);

            username = node.getChildText("name");
            password = node.getChildText("password");
            homedir = node.getChildText("homedir");
            mask = node.getChildText("mask");

            User theUser = new User(); //User(username, username); TODO
            theUser.setPassword(password);
            theUser.setHomedir(homedir);
            theUser.setMask(mask);

            System.out.println("Name : " + username);
            System.out.println("Password : " + password);
            System.out.println("Homedir : " + homedir);
            System.out.println("Mask : " + mask);

        }
    }
}