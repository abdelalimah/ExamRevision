package mql;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.Vector;

/*
* The XMLNode class unlike DocumentParser doesn't care about operations to be performed on an xml document
* it only cares about the different operations that can be performed on xml **nodes**
* */
public class XMLNode {
    private Node node;
    private Document doc;

    public XMLNode(Node source){
        node = source;
    }

    public XMLNode(String source){

        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
    
            doc = builder.parse(source);
            node = doc.getFirstChild();
            while(!isElementNode(node)){
                node = node.getNextSibling();
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }


    public XMLNode[] extractChildren(){
        NodeList nodeList = node.getChildNodes();
        Vector<XMLNode> v = new Vector<>();

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node item = nodeList.item(i);
            System.out.println(item.getNodeName());
            if(isElementNode(item)) v.add(new XMLNode(item));
        }
        XMLNode[] t = new XMLNode[v.size()];
        return v.toArray(t);
    }

    public XMLNode extractChild(String name){
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node item = nodeList.item(i);
            if(item.getNodeName().equals(name)){
                return new XMLNode(item);
            }
        }
        return null;
    }

    public String extractAttribute(String name){
        NamedNodeMap attributes = node.getAttributes();
        Node attr = attributes.getNamedItem(name);
        if(attr != null){
            return attr.getNodeValue();
        }
        return null;
    }

    public int extractIntAttribute(String name){
        String value = extractAttribute(name);
        try{
            return Integer.parseInt(value);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return -1;
        }
    }

    public void appendChild(Element childNode){
        node.appendChild(childNode);
    }

    public void setNodeValue(String newValue){
        node.setTextContent(newValue);
    }

    public String textValue(){
        if(node.getNodeType() == Node.ELEMENT_NODE){
            return node.getFirstChild().getNodeValue();
        }

        return node.getNodeValue();
    }

    public boolean isElementNode(Node node){
        return node.getNodeType() == Node.ELEMENT_NODE;
    }

    public double doubleValue(){
        String value = textValue();
        try{
            return Double.parseDouble(value);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return -1;
        }
    }

    public Node getParent(){
        return this.node.getParentNode();
    }

    public Node getNode(){
        return node;
    }

    public void changeContent(String newContent){
        node.getFirstChild().setNodeValue(newContent);
    }

    public Document getDocument(){
        return this.doc;
    }

}
