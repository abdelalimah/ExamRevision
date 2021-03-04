import java.lang.annotation.Annotation;
import java.util.Vector;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import annotation.Age;
import models.Etudiant;
import mql.TransformerHandle;
import mql.XMLNode;

/**
 * Example
 */
public class Example {

    public Example(){
        // exp01();
        // exp02();
        // exp03();
        exp04();

    }

    void exp01(){
        Etudiant ihsane = new Etudiant(10, "El jabiry", "Ihsane");
        Etudiant abdo = new Etudiant(21, "Khemisse", "Abdessamad");
        
        Vector<Etudiant> etudiants = new Vector<>();
        etudiants.add(ihsane);
        etudiants.add(abdo);

        Class<?> cls = ihsane.getClass();
        int age = cls.getDeclaredAnnotation(Age.class).value();

        for (Etudiant etudiant : etudiants) {
            if(etudiant.getAge() > age){
                System.out.println(etudiant.getName());
            }
        }
    }

    // addition
    void exp02(){
        XMLNode root = new XMLNode("resources/documents.xml");
        Document doc = root.getDocument();
        Element document = doc.createElement("document");
        Element title = doc.createElement("title");
        title.appendChild(doc.createTextNode("Oracle"));
        Element price = doc.createElement("price");
        price.appendChild(doc.createTextNode("300"));
        Element authors = doc.createElement("authors");
        
        Element author1 = doc.createElement("author");
        Element authorName = doc.createElement("name");
        authorName.appendChild(doc.createTextNode("Khemisse"));
        Element birthYear = doc.createElement("year-born");
        birthYear.appendChild(doc.createTextNode("1999"));
        Element country = doc.createElement("country");
        country.appendChild(doc.createTextNode("Morocco"));
        
        author1.appendChild(authorName);
        author1.appendChild(birthYear);
        author1.appendChild(country);

        authors.appendChild(author1);

        document.appendChild(title);
        document.appendChild(price);
        document.appendChild(authors);

        root.appendChild(document);

        TransformerHandle transformer = new TransformerHandle();
        transformer.transform(doc);
    }

    // deletion
    void exp03(){
        XMLNode root = new XMLNode("resources/documents.xml");
        Document doc = root.getDocument();

        try{

            for (XMLNode xmlNode : root.extractChildren()) {
                XMLNode title = xmlNode.extractChild("title");
                title.getParent().removeChild(title.getNode());
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        TransformerHandle transformer = new TransformerHandle();
        transformer.transform(doc);
    }
    
    // modification
    void exp04(){
        XMLNode root = new XMLNode("resources/documents.xml");
        Document doc = root.getDocument();

        try{

            for (XMLNode xmlNode : root.extractChildren()) {
                XMLNode title = xmlNode.extractChild("title");
                title.changeContent("This is the new title");
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        TransformerHandle transformer = new TransformerHandle();
        transformer.transform(doc);
    }
    public static void main(String[] args) {
        new Example();
    }
}


// "UI" | "Business" | "DAO" | "Module" | "util/Helper.java"